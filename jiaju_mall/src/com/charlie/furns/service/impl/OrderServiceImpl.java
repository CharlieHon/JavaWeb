package com.charlie.furns.service.impl;

import com.charlie.furns.dao.FurnDAO;
import com.charlie.furns.dao.OrderDAO;
import com.charlie.furns.dao.OrderItemDAO;
import com.charlie.furns.dao.impl.FurnDAOImpl;
import com.charlie.furns.dao.impl.OrderDAOImpl;
import com.charlie.furns.dao.impl.OrderItemDAOImpl;
import com.charlie.furns.entity.*;
import com.charlie.furns.service.OrderService;

import java.util.Date;
import java.util.Map;
import java.util.Set;

public class OrderServiceImpl implements OrderService {

    // 这里体现javaee分层的优点，在service层通过组合多个dao的方法，完成某个业务
    private OrderDAO orderDAO = new OrderDAOImpl();
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();
    private FurnDAO furnDAO = new FurnDAOImpl();

    @Override
    public String saveOrder(Cart cart, int memberId) {
        // 这里的业务逻辑相对复杂
        // 完成的任务是将 cart 购物车中的数据，以order和orderItem形式保存到db
        /*
        TODO 因为生成订单会操作多表，因此会涉及到多表事务问题 ThreadLocal+Mysql事务机制+过滤器
        关于事务的处理，考虑的点比较多，后面专门处理
         */
        // 1. 通过cart对象，构建对应的Order对象
        // 先生成一个UUID，表示当前的订单号，订单号是唯一的
        String orderId = System.currentTimeMillis() + "" + memberId;
        Order order = new Order(orderId, new Date(), cart.getCartTotalPrice(), 0, memberId);
        // 保存order到数据表
        orderDAO.saveOrder(order);

        // 2. 通过cart对象，遍历出cartItem，保存到orderItem

        // 1> 通过keySet遍历cart
        //Map<Integer, CartItem> items = cart.getItems();
        //Set<Integer> keys = items.keySet();
        //for (Integer id : keys) {
        //    CartItem cartItem = items.get(id);
        //    // 保存
        //    orderItemDAO.saveOrderItem(new OrderItem(null, cartItem.getName(), cartItem.getPrice(),
        //            cartItem.getCount(), cartItem.getTotalPrice(), orderId));
        //    // 更新一下furn表的sales的销量，stock存量
        //    // 1) 获取到furn对象
        //    Fur n furn = furnDAO.queryFurnById(id);
        //    // 2) 更新一下这个furn的sales销量，stock库存
        //    furn.setSales(furn.getSales() + cartItem.getCount());
        //    furn.setStock(furn.getStock() - cartItem.getCount());
        //    // 3) 更新到数据库的furn表
        //    furnDAO.updateFurn(furn);
        //}

        // 2> 通过entrySet的方式遍历cart
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            CartItem cartItem = entry.getValue();
            // 保存
            orderItemDAO.saveOrderItem(new OrderItem(null, cartItem.getName(), cartItem.getPrice(),
                    cartItem.getCount(), cartItem.getTotalPrice(), orderId));
            // 更新一下furn表的sales的销量，stock存量
            // 1) 获取到furn对象
            Furn furn = furnDAO.queryFurnById(entry.getKey());
            // 2) 更新一下这个furn的sales销量，stock库存
            furn.setSales(furn.getSales() + cartItem.getCount());
            furn.setStock(furn.getStock() - cartItem.getCount());
            // 3) 更新到数据库的furn表
            furnDAO.updateFurn(furn);
        }

        // 清空购物车
        cart.clear();
        return orderId;
    }
}

package com.charlie.furns.service;

import com.charlie.furns.entity.Cart;
import com.charlie.furns.entity.Order;
import com.charlie.furns.entity.OrderItem;

import java.math.BigDecimal;
import java.util.List;

public interface OrderService {
    /*
    1. 生成订单
    2. 订单是根据购物车cart生成的，cart对象是在session，通过web层传入
    3. 订单适合一个会员关联
     */
    public String saveOrder(Cart cart, int memberId);

    // 显示memberId对应用户的所有订单信息
    public List<Order> showOrders(int memberId);

    // 根据订单id，查询其下属所有订单项
    public List<OrderItem> showOrderItemsByOrderId(String orderId);

    // 更具订单编号，查询其包含的商品个数
    public BigDecimal totalCount(String orderId);

    // 包含的商品总价
    public BigDecimal totalPrice(String orderId);
}

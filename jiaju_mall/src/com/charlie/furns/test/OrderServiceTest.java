package com.charlie.furns.test;

import com.charlie.furns.entity.Cart;
import com.charlie.furns.entity.CartItem;
import com.charlie.furns.entity.Order;
import com.charlie.furns.entity.OrderItem;
import com.charlie.furns.service.OrderService;
import com.charlie.furns.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class OrderServiceTest {

    private OrderService orderService = new OrderServiceImpl();

    @Test
    public void saveOrder() {
        // 构建一个cart对象
        Cart cart = new Cart();
        cart.addItem(new CartItem(9, "apple TV", 1, new BigDecimal("3699.00"), new BigDecimal("3699.00")));
        cart.addItem(new CartItem(11, "华为Mate 50 Pro", 1, new BigDecimal("7500"), new BigDecimal("7500")));
        System.out.println(orderService.saveOrder(cart, 3));
    }

    @Test
    public void list() {
        int memberId = 3;
        List<Order> orders = orderService.showOrders(memberId);
        for (Order order : orders) {
            System.out.println(order);
        }
    }

    @Test
    public void showOrderItemsByOrderId() {
        String orderId = "17057322770543";
        List<OrderItem> orderItems = orderService.showOrderItemsByOrderId(orderId);
        for (OrderItem orderItem : orderItems) {
            System.out.println(orderItem);
        }
    }
}

package com.charlie.furns.dao;

import com.charlie.furns.entity.OrderItem;

import java.util.List;

public interface OrderItemDAO {
    // 将拆入的订单项保存到数据库表order_item中，返回受影响的行数
    public int saveOrderItem(OrderItem orderItem);

    // 根据订单id，显示其对应的所有订单项
    public List<OrderItem> list(String orderId);
}

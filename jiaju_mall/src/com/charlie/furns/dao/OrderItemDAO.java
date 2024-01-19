package com.charlie.furns.dao;

import com.charlie.furns.entity.OrderItem;

public interface OrderItemDAO {
    // 将拆入的订单项保存到数据库表order_item中，返回受影响的行数
    public int saveOrderItem(OrderItem orderItem);
}

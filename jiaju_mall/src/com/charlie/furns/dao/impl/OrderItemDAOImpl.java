package com.charlie.furns.dao.impl;

import com.charlie.furns.dao.BasicDAO;
import com.charlie.furns.dao.OrderItemDAO;
import com.charlie.furns.entity.OrderItem;

public class OrderItemDAOImpl extends BasicDAO<OrderItem> implements OrderItemDAO {
    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "INSERT INTO `order_item`(`id`, `name`, `price`, `count`, `total_price`, `order_id`) " +
                "VALUES(NULL, ?, ?, ?, ?, ?)";
        return update(sql, orderItem.getName(), orderItem.getPrice(), orderItem.getCount(), orderItem.getTotalPrice(),
                orderItem.getOrderId());
    }
}

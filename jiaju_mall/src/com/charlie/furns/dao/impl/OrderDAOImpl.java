package com.charlie.furns.dao.impl;

import com.charlie.furns.dao.BasicDAO;
import com.charlie.furns.dao.OrderDAO;
import com.charlie.furns.entity.Order;

public class OrderDAOImpl extends BasicDAO<Order> implements OrderDAO {
    @Override
    public int saveOrder(Order order) {
        String sql = "INSERT INTO `order`(`id`, `create_time`, `price`, `status`, `member_id`) " +
                "VALUES(?, ?, ?, ?, ?);";
        return update(sql, order.getId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getMemberId());
    }
}

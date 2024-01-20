package com.charlie.furns.dao.impl;

import com.charlie.furns.dao.BasicDAO;
import com.charlie.furns.dao.OrderDAO;
import com.charlie.furns.entity.Order;

import java.math.BigDecimal;
import java.util.List;

public class OrderDAOImpl extends BasicDAO<Order> implements OrderDAO {
    @Override
    public int saveOrder(Order order) {
        String sql = "INSERT INTO `order`(`id`, `create_time`, `price`, `status`, `member_id`) " +
                "VALUES(?, ?, ?, ?, ?);";
        return update(sql, order.getId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getMemberId());
    }

    @Override
    public List<Order> queryOrderByMemberId(int memberId) {
        String sql = "SELECT `id`, `create_time` AS `createTime`, `price`, `status`, `member_id` AS `memberId` " +
                "FROM `order` WHERE `member_id`=?";
        return queryMulti(sql, Order.class, memberId);
    }

    @Override
    public BigDecimal totalCount(String orderId) {
        String sql = "SELECT SUM(`count`) FROM `order_item` WHERE `order_id`=?";
        return (BigDecimal) queryScalar(sql, orderId);
    }

    @Override
    public BigDecimal totalPrice(String orderId) {
        String sql = "SELECT SUM(`total_price`) FROM `order_item` WHERE `order_id`=?";
        return (BigDecimal) queryScalar(sql, orderId);
    }
}

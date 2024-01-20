package com.charlie.furns.dao;

import com.charlie.furns.entity.Order;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDAO {
    // 将传入的order对象保存到数据库order表中，返回受影响的行数
    public int saveOrder(Order order);

    // 根据用户id，查询其所有的订单
    public List<Order> queryOrderByMemberId(int id);

    // 根据订单编号orderId查询该订单的商品数量和总价
    public BigDecimal totalCount(String orderId);
    public BigDecimal totalPrice(String orderId);
}

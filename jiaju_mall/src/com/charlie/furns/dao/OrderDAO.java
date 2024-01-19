package com.charlie.furns.dao;

import com.charlie.furns.entity.Order;

public interface OrderDAO {
    // 将传入的order对象保存到数据库order表中，返回受影响的行数
    public int saveOrder(Order order);
}

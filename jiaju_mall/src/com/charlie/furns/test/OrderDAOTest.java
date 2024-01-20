package com.charlie.furns.test;

import com.charlie.furns.dao.OrderDAO;
import com.charlie.furns.dao.impl.OrderDAOImpl;
import com.charlie.furns.entity.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

public class OrderDAOTest {

    private OrderDAO orderDAO = new OrderDAOImpl();

    @Test
    public void saveOrder() {
        Order order = new Order("tju_00002", new Date(), new BigDecimal("266.8"),
                0, 3);
        System.out.println(orderDAO.saveOrder(order));
    }

    @Test
    public void totalCount() {
        String orderId = "17057322770543";
        System.out.println(orderDAO.totalCount(orderId));
    }
}

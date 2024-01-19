package com.charlie.furns.test;

import com.charlie.furns.dao.OrderItemDAO;
import com.charlie.furns.dao.impl.OrderItemDAOImpl;
import com.charlie.furns.entity.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

public class OrderItemTest {
    private OrderItemDAO orderItemDAO = new OrderItemDAOImpl();

    @Test
    public void saveOrderItem() {
        OrderItem orderItem = new OrderItem(null, "神光棒Pro", new BigDecimal(1260), 1, new BigDecimal(1260),
                "tju_00003");
        System.out.println(orderItemDAO.saveOrderItem(orderItem));
    }
}

package com.charlie.furns.test;

import com.charlie.furns.dao.FurnDAO;
import com.charlie.furns.dao.impl.FurnDAOImpl;
import com.charlie.furns.entity.Furn;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class FurnDAOTest {

    private FurnDAO furnDAO = new FurnDAOImpl();

    @Test
    public void queryFurns() {
        List<Furn> furns = furnDAO.queryFurns();
        for (Furn furn : furns) {
            System.out.println(furn);
        }
    }

    @Test
    public void addFurn() {
        Furn furn = new Furn(null, "小米电视TV", "小米", new BigDecimal("3666.6"), 200, 1000, "assets/images/product-image/8.jpg");
        if (furnDAO.addFurn(furn) == 1) {
            System.out.println("添加成功！");
        } else {
            System.out.println("添加失败~");
        }
    }
}

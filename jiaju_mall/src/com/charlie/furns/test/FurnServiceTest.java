package com.charlie.furns.test;

import com.charlie.furns.entity.Furn;
import com.charlie.furns.service.FurnService;
import com.charlie.furns.service.impl.FurnServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

public class FurnServiceTest {

    private FurnService furnService = new FurnServiceImpl();

    @Test
    public void queryFurns() {
        List<Furn> furns = furnService.queryFurns();
        for (Furn furn : furns) {
            System.out.println(furn);
        }
    }

    @Test
    public void add() {
        Furn furn = new Furn(null, "小米路由器", "小米", new BigDecimal("52.5"), 20, 300, "assets/images/product-image/7.jpg");
        if (furnService.addFurn(furn)) {
            System.out.println("添加成功！");
        } else {
            System.out.println("添加失败~");
        }
    }
}

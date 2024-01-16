package com.charlie.furns.test;

import com.charlie.furns.entity.Furn;
import com.charlie.furns.entity.Page;
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

    @Test
    public void deleteFurnById() {
        int id = 20;
        if (furnService.deleteFurnById(20)) {
            System.out.println("删除家具成功！");
        } else {
            System.out.println("删除家具失败~");
        }
    }

    @Test
    public void queryFurnById() {
        int id = 10;
        Furn furn = furnService.queryFurnById(id);
        System.out.println(furn);
    }

    @Test
    public void updateFurn() {
        Furn furn = new Furn(17, "美的饮水机", "美的", new BigDecimal("126.6"), 99, 500, "");
        System.out.println(furnService.updateFurn(furn) ? "修改成功！" : "修改失败~");
    }

    @Test
    public void page() {
        // 如果需要看一个对象(复杂)，可以debug看
        Page<Furn> page = furnService.page(2, 2);
        System.out.println("page=" + page);
    }

    @Test
    public void pageByName() {
        Page<Furn> page = furnService.pageByName("%椅子%", 1, 3);
        System.out.println("page=" + page);
    }
}

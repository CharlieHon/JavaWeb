package com.charlie.furns.entity;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Cart就是购物车，可以存放多个CartItem对象
public class Cart {
    // 家具id -> 购物车项
    private Map<Integer, CartItem> items = new HashMap<>();

    // 获取购物车商品的总数量
    public int getTotalCount() {
        int totalCount = 0;
        // 遍历items，统计totalCount
        Set<Integer> keys = items.keySet();
        for (Integer id : keys) {
            totalCount += items.get(id).getCount();
        }
        return totalCount;
    }

    // 添加家具到Cart
    public void addItem(CartItem cartItem) {
        CartItem item = items.get(cartItem.getId());
        if (item == null) { // 说明当前购物车还没有该cartItem
            items.put(cartItem.getId(), cartItem);
        } else {    // 购物车中已经存在该cartItem
            item.setCount(item.getCount() + cartItem.getCount()); // 数量增加
            // 修改总价
            // item.getPrice() -> Big Decimal 不能直接相加/减/乘/除
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "items=" + items +
                '}';
    }
}

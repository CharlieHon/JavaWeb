package com.charlie.furns.service;

import com.charlie.furns.entity.Cart;

public interface OrderService {
    /*
    1. 生成订单
    2. 订单是根据购物车cart生成的，cart对象是在session，通过web层传入
    3. 订单适合一个会员关联
     */
    public String saveOrder(Cart cart, int memberId);
}

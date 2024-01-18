package com.charlie.furns.test;

import com.charlie.furns.entity.Cart;
import com.charlie.furns.entity.CartItem;
import org.junit.Test;

import java.math.BigDecimal;

public class CartTest {

    private Cart cart = new Cart();

    @Test
    public void addItem() {
        cart.addItem(new CartItem(1, "小椅子", 2, new BigDecimal("16.8"), new BigDecimal("33.6")));
        cart.addItem(new CartItem(2, "沙发", 1, new BigDecimal("1688"), new BigDecimal("1688")));
        cart.addItem(new CartItem(2, "沙发", 2, new BigDecimal("1688"), new BigDecimal("1688")));
        System.out.println("cart=" + cart);
    }
}

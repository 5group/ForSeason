package com.shop.review;

import com.shop.dto.Cart;
import com.shop.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UpdateTests {
    @Autowired
    CartService service;

    @Test
    void contentLoads() {
        Cart cart = new Cart();
        cart.setCart_cnt(3);
        cart.setCart_no(1);
        try {
            service.modify(cart);
            System.out.println(cart.getItem_no()+"번 제품 수정 성공");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


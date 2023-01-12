package com.shop.cart;


import com.shop.dto.Cart;
import com.shop.dto.Item;
import com.shop.service.CartService;
import com.shop.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SelectTests {
    @Autowired
    CartService service;
    @Test
    void contextLoads() {
        Cart cart = null;
        try {
            cart = service.get(1);
            System.out.println(cart);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

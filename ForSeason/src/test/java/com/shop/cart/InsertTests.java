package com.shop.cart;

import com.shop.dto.Cart;
import com.shop.dto.Item;
import com.shop.service.CartService;
import com.shop.service.ItemService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InsertTests {
    @Autowired
    CartService service;

    @DisplayName("Insert")
    @Test
    void contentLoads(){
        Cart cart = new Cart(0, 8, 1, 10,null);
        try{
            service.register(cart);
            System.out.printf("OK");
        }catch (Exception e){
            e.printStackTrace();
            System.out.printf("Fail");
        }
    }
}

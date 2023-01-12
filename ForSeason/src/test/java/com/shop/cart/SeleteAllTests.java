package com.shop.cart;

import com.shop.dto.Cart;
import com.shop.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SeleteAllTests {
    @Autowired
    CartService service;
    @Test
    void contentLoads(){
        List<Cart> list = null;
        try{
            list = service.get();
            for(Cart cart : list) System.out.println(cart);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

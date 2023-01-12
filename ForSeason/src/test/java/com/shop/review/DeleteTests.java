package com.shop.review;

import com.shop.service.CartService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeleteTests {
    @Autowired
    CartService service;
    @Test
    void contentLoads(){
        try {
            service.remove(1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

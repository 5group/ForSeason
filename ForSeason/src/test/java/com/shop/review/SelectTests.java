package com.shop.review;


import com.shop.dto.Cart;
import com.shop.dto.Review;
import com.shop.service.CartService;
import com.shop.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SelectTests {
    @Autowired
    ReviewService service;
    @Test
    void contextLoads() {
        Review review = null;
        try {
            review = service.get(1);
            System.out.println(review);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

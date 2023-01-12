package com.shop.review;

import com.shop.dto.Cart;
import com.shop.dto.Review;
import com.shop.service.CartService;
import com.shop.service.ReviewService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InsertTests {
    @Autowired
    ReviewService service;

    @DisplayName("Insert")
    @Test
    void contentLoads(){
        Review review = new Review(0, 1, 1, "title_test", "content_test", 1, null,null );
        try{
            service.register(review);
            System.out.printf("OK");
        }catch (Exception e){
            e.printStackTrace();
            System.out.printf("Fail");
        }
    }
}

package com.shop.review;

import com.shop.dto.Review;
import com.shop.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SeleteAllTests {
    @Autowired
    ReviewService service;
    @Test
    void contentLoads(){
        List<Review> list = null;
        try{
            list = service.get();
            for(Review review : list) System.out.println(review);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

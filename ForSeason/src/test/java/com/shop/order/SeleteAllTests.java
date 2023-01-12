package com.shop.order;

import com.shop.dto.Order;
import com.shop.dto.Review;
import com.shop.service.OrderService;
import com.shop.service.ReviewService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SeleteAllTests {
    @Autowired
    OrderService service;
    @Test
    void contentLoads(){
        List<Order> list = null;
        try{
            list = service.get();
            for(Order order : list) System.out.println(order);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

package com.shop.order;


import com.shop.dto.Order;
import com.shop.service.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SelectTests {
    @Autowired
    OrderService service;
    @Test
    void contextLoads() {
        Order order = null;
        try {
            order = service.get(1);
            System.out.println(order);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

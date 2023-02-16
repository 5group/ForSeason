package com.shop.order;

import com.shop.dto.Order;
import com.shop.service.OrderService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InsertTests {
    @Autowired
    OrderService service;

    @DisplayName("Insert")
    @Test
    void contentLoads(){
        Order order = new Order(0, 2, 10000, "카카오페이","서울특별시", "무인", "배송중", "결제완료", 1000,null);
        try{
            service.register(order);
            //("OK");
        }catch (Exception e){
            e.printStackTrace();
            System.out.printf("Fail");
        }
    }
}
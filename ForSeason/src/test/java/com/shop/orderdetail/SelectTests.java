package com.shop.orderdetail;

import com.shop.dto.OrderDetail;
import com.shop.service.OrderDetailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SelectTests {
    @Autowired
    OrderDetailService service;
    @Test
    void contextLoads() {
        OrderDetail od = null;
        try {
            od = service.get(1);
            System.out.println(od);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

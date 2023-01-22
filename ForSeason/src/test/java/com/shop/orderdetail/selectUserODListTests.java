package com.shop.orderdetail;

import com.shop.dto.OrderDetail;
import com.shop.service.OrderDetailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class selectUserODListTests {
    @Autowired
    OrderDetailService service;
    @Test
    void contextLoads() {
        List<OrderDetail> list = null;
        try {
            list = service.getODList(1);
            for(OrderDetail od : list) System.out.println(od);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

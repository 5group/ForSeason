package com.shop.orderdetail;

import com.shop.dto.OrderDetail;
import com.shop.service.OrderDetailService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InsertTests {

    @Autowired
    OrderDetailService service;

    @DisplayName("Insert")
    @Test
    void contentLoads(){
        OrderDetail od = new OrderDetail(0, 1, 4, 1, 10000);
        try{
            service.register(od);
            System.out.printf("OK");
        }catch (Exception e){
            e.printStackTrace();
            System.out.printf("Fail");
        }
    }
}

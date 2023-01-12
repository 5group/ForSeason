package com.shop.orderdetail;

import com.shop.dto.OrderDetail;
import com.shop.service.OrderDetailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SeleteAllTests {
    @Autowired
    OrderDetailService service;
    @Test
    void contentLoads(){
        List<OrderDetail> list = null;
        try{
            list = service.get();
            for(OrderDetail od : list) System.out.println(od);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

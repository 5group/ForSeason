package com.shop.coupon;

import com.shop.dto.Coupon;
import com.shop.service.CouponService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InsertTests {
    @Autowired
    CouponService service;

    @DisplayName("Insert")
    @Test
    void contentLoads(){
        Coupon coupon = new Coupon(0, "test_coupon_name", "test_info" ,null,1);
        try{
            service.register(coupon);
            System.out.printf("OK");
        }catch (Exception e){
            e.printStackTrace();
            System.out.printf("Fail");
        }
    }
}

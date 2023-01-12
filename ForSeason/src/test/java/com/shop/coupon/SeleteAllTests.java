package com.shop.coupon;

import com.shop.dto.Coupon;
import com.shop.service.CouponService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SeleteAllTests {
    @Autowired
    CouponService service;
    @Test
    void contentLoads(){
        List<Coupon> list = null;
        try{
            list = service.get();
            for(Coupon coupon : list) System.out.println(coupon);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

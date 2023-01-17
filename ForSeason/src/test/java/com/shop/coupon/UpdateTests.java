package com.shop.coupon;

import com.shop.dto.Coupon;
import com.shop.service.CouponService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UpdateTests {
    @Autowired
    CouponService service;

    @Test
    void contentLoads() {
        Coupon coupon = new Coupon();
        coupon.setCou_no(6);
        try {
            service.modify(coupon);
            System.out.println(coupon.getCou_no()+"번 쿠폰 사용 완료");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}


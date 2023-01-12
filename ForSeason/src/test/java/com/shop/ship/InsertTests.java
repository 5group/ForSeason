package com.shop.ship;

import com.shop.dto.Review;
import com.shop.dto.Ship;
import com.shop.service.ReviewService;
import com.shop.service.ShipService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InsertTests {
    @Autowired
    ShipService service;

    @DisplayName("Insert")
    @Test
    void contentLoads(){
        Ship ship = new Ship(0, "서울특별시", "무인", "배송중");
        try{
            service.register(ship);
            System.out.printf("OK");
        }catch (Exception e){
            e.printStackTrace();
            System.out.printf("Fail");
        }
    }
}

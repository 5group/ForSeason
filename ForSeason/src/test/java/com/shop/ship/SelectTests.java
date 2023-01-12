package com.shop.ship;


import com.shop.dto.Ship;
import com.shop.service.ShipService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SelectTests {
    @Autowired
    ShipService service;
    @Test
    void contextLoads() {
        Ship ship = null;
        try {
            ship = service.get(1);
            System.out.println(ship);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.shop.ship;

import com.shop.dto.Ship;
import com.shop.service.ShipService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SeleteAllTests {
    @Autowired
    ShipService service;
    @Test
    void contentLoads(){
        List<Ship> list = null;
        try{
            list = service.get();
            for(Ship ship : list) System.out.println(ship);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

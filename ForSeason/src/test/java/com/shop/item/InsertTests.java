package com.shop.item;

import com.shop.dto.Item;
import com.shop.service.ItemService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InsertTests {
    @Autowired
    ItemService service;

    @DisplayName("Insert")
    @Test
    void contentLoads(){
        Item item = new Item(30, "청바지", 10000, 100,"청바지 사든가 말든가", 1, "jpg", null);
        try{
            service.register(item);
            System.out.printf("OK");
        }catch (Exception e){
            e.printStackTrace();
            System.out.printf("Fail");
        }
    }
}

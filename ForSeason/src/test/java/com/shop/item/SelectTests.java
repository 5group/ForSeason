package com.shop.item;


import com.shop.dto.Item;
import com.shop.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SelectTests {
    @Autowired
    ItemService service;
    @Test
    void contextLoads() {
        Item item = null;
        try {
            item = service.get(7);
            System.out.println(item);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

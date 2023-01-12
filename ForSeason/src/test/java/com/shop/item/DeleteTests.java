package com.shop.item;

import com.shop.dto.Item;
import com.shop.service.ItemService;
import com.shop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeleteTests {
    @Autowired
    ItemService service;
    @Test
    void contentLoads(){
        try {
            service.remove(7);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

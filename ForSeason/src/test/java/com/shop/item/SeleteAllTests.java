package com.shop.item;

import com.shop.dto.Item;
import com.shop.dto.User;
import com.shop.service.ItemService;
import com.shop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SeleteAllTests {
    @Autowired
    ItemService service;
    @Test
    void contentLoads(){
        List<Item> list = null;
        try{
            list = service.get();
            for(Item item : list) System.out.println(item);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

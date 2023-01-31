package com.admin.itemPut;


import com.admin.dto.Item;
import com.admin.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.inject.Inject;
import java.util.List;

@SpringBootTest
public class ItemSelectTest {

    @Autowired
    ItemService service;

    @Test
    void selectTest() throws Exception {
        List<Item> itemList = service.getItemList();
        for (Item item :itemList) System.out.println(item);
    }
}

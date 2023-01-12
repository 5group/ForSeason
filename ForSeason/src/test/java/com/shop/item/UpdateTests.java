package com.shop.item;

import com.shop.dto.Item;
import com.shop.dto.User;
import com.shop.service.ItemService;
import com.shop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UpdateTests {
    @Autowired
    ItemService service;

    @Test
    void contentLoads() {
        Item item = new Item();
        item.setItem_price(40000);
        item.setItem_discnt(20);
        item.setItem_info("난이제 모르겠다이게맞는지..");
        item.setItem_img("img");
        item.setItem_no(8);
        try {
            service.modify(item);
            System.out.println(item.getItem_no()+"번 제품 수정 성공");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // review, ship, order, order_detail, wishlist, coupon
}

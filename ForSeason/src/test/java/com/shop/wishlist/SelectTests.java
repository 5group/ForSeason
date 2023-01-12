package com.shop.wishlist;

import com.shop.dto.WishList;
import com.shop.service.WishListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SelectTests {
    @Autowired
    WishListService service;
    @Test
    void contextLoads() {
        WishList wishList = null;
        try {
            wishList = service.get(1);
            System.out.println(wishList);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

package com.shop.wishlist;

import com.shop.dto.WishList;
import com.shop.service.WishListService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InsertTests {
    @Autowired
    WishListService service;

    @DisplayName("Insert")
    @Test
    void contentLoads(){
        WishList wishList = new WishList(0, 1, 1);
        try{
            service.register(wishList);
            System.out.printf("OK");
        }catch (Exception e){
            e.printStackTrace();
            System.out.printf("Fail");
        }
    }
}

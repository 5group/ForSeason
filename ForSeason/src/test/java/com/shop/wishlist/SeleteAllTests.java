package com.shop.wishlist;

import com.shop.dto.WishList;
import com.shop.service.WishListService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SeleteAllTests {
    @Autowired
    WishListService service;
    @Test
    void contentLoads(){
        List<WishList> list = null;
        try{
            list = service.get();
            for(WishList wishList : list) System.out.println(wishList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

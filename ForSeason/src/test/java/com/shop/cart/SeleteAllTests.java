package com.shop.cart;

import com.shop.dto.Cart;
import com.shop.dto.Item;
import com.shop.service.CartService;
import com.shop.service.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SeleteAllTests {
    @Autowired
    CartService service;

    @Autowired
    ItemService itemService;

    @Test
    void contentLoads(){
        Item item = null;
        List<Cart> list = null;
        int tot_price = 0;
        int index = 0;
        String text ="";
        try{
            list = service.get();
            for(Cart cart : list){
//                item = itemService.get(cart.getItem_no());/**/
                if(index == 0) text += item.getItem_name();
                tot_price += item.getItem_price() * cart.getCart_cnt();
                index += 1;
            }
            if(index == 1){
                System.out.println(text);
                System.out.println(tot_price);
            }else {
                text = String.format(text + "외%d개 제품",index-1);
                System.out.println(text);
                System.out.println(tot_price);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

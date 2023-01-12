package com.shop.user;

import com.shop.dto.User;
import com.shop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class SeleteAllTests {
    @Autowired
    UserService service;
    @Test
    void contentLoads(){
        List<User> list = null;
        try{
            list = service.get();
            for(User user : list) System.out.println(user);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

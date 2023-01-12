package com.shop.user;


import com.shop.dto.User;
import com.shop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SelectIdTests {

    @Autowired
    UserService service;
    @Test
    void contentLoads(){
        User user = null;
        try {
            user = service.get_id("id1");
            System.out.println(user);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

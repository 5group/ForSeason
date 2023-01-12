package com.shop.user;


import com.shop.dto.User;
import com.shop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class SelectTests {
    @Autowired
    UserService service;
    @Test
    void contextLoads() {
        User user = null;

        try {
            user = service.get(1);
            System.out.println(user);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

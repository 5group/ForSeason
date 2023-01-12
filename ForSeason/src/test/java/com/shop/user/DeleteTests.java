package com.shop.user;

import com.shop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class DeleteTests {
    @Autowired
    UserService service;
    @Test
    void contentLoads(){
        try {
            service.remove("id1");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

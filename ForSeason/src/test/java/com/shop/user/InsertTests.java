package com.shop.user;

import com.shop.dto.User;
import com.shop.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class InsertTests {
    @Autowired
    UserService service;

    @DisplayName("Insert")
    @Test
    void contentLoads(){
        User user = new User(1, "id1", "123", "123@123.com","조민수", "010-1111-1111","경기도", "남", null, null);
        try{
            service.register(user);
            System.out.printf("OK");
        }catch (Exception e){
            e.printStackTrace();
            System.out.printf("Fail");
        }
    }
}

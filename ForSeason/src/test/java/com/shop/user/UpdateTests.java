package com.shop.user;

import com.shop.dto.User;
import com.shop.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UpdateTests {
    @Autowired
    UserService service;
    @Test
    void contentLoads(){
        User user = new User();
        user.setUser_id("sorry");
        user.setUser_pwd("1234");
        user.setUser_email("cho@gmail.com");
        user.setUser_name("주사맞음");
        try{
            service.modify(user);
            System.out.println(user.getUser_id()+"번 제품 수정 성공");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

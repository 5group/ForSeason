package com.shop;

import com.shop.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.security.SecureRandom;
import java.util.Random;

@SpringBootTest
public class MailPutTest {


    @Autowired
    MailService mailService;
    @Test
    void mailPutTest() throws Exception {
        // 이메일 전송
        mailService.sendMail("rofeorkwl151@gmail.com", "너는 비밀번호를 못찾았어.. 그래서 바꿔줄게", "password=12341234");
    }

    @Test
    void test(){

    }
}

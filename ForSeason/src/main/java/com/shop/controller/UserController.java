package com.shop.controller;

import com.shop.dto.User;
import com.shop.service.KakaoService;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;

@Controller
@RequestMapping("/user")
public class UserController {
    String dir = "user/";
    
    @Autowired
    UserService service;

    @RequestMapping("")
    public String main(Model model) {
        System.out.println("test");
        model.addAttribute("center", dir + "center");
        return "main";
    }

    @RequestMapping("/register")
    public String register(Model model, User user){
        User c = null;
        try {
            if(service.get_id(user.getUser_id()) == null || service.get_id(user.getUser_id()).equals("")){
                service.register(user);
                model.addAttribute("center", dir + "registerok");
                c = service.get_id(user.getUser_id());
                model.addAttribute("obj", c);
            }else {
                System.out.println("해당 계정은 이미 존재합니다.");
                model.addAttribute("center", dir + "registerfail");
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("center", dir + "registerfail");
        }

        return "main";
    }

    @RequestMapping("/add")
    public String html2(Model model){
        model.addAttribute("center", dir + "add");
        return "main";
    }
}
package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    // http://127.0.0.1/
    @RequestMapping("/")
    public String main() {
        return "main";
    }

    @RequestMapping("/loginimpl")
    public String loginimpl(String id, String pwd){
        System.out.println(id +""+pwd);
        return "main";
    }

}
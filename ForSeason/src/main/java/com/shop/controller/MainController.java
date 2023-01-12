package com.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.dto.User;
import com.shop.service.UserService;

@Controller
public class MainController {
	
	@Autowired
	UserService uservice;
	
	// http://127.0.0.1/
	@RequestMapping("/")
	public String main() {
		return "main";
	}
	
	@RequestMapping("/join")
	public String join() {
		return "join";
	}
	
	@RequestMapping("/register")
	public String loginimpl(User user) {
		try {
			uservice.register(user);
			System.out.println("회원가입성공");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "registerok";  //registerok로 이동
	}
}

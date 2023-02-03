package com.admin.controller;

import com.admin.dto.Admin;
import com.admin.dto.Stock;
import com.admin.service.AdminService;
import com.admin.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    StockService stockService;

    @Autowired
    AdminService adminService;

    @Autowired
    HttpSession session;

    @RequestMapping("/")
    public String main(Model model) throws Exception {
        if(session.getAttribute("adminLogin") == null){
            return "redirect:/login";
        }
        List<Stock> stockList = stockService.getAdminMainList();
        model.addAttribute("stockList", stockList);
        return "main";
    }

    @RequestMapping("/login")
    public String login() {
        return "admin/login";
    }

    @RequestMapping("/logout")
    public String logout(){
        session.invalidate();
        return "main";
    }

    @RequestMapping(value = "/checkLogin")
    public String checkLogin(String id, String pwd) throws Exception {
        Admin admin = adminService.get(id);
        System.out.println(admin);
        if (admin != null && admin.getAdmin_password().equals(pwd)) {
            session.setAttribute("adminLogin", admin);
            return "redirect:/";
        }
        return "redirect:/login";

    }
}
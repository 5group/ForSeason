package com.admin.controller;

import com.admin.dto.Stock;
import com.admin.dto.User;
import com.admin.service.StockService;
import com.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class MainController {

    @Autowired
    StockService stockService;

    @RequestMapping("/")
    public String main(Model model) throws Exception {
        List<Stock> stockList = stockService.getAdminMainList();
        model.addAttribute("stockList", stockList);
        return "main";
    }
}
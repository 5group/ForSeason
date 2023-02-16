package com.admin.controller;

import com.admin.dto.Stock;
import com.admin.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StockController {

    @Autowired
    StockService stockService;

    @RequestMapping("/stock_no={stock_no}")
    public String main(Model model, @PathVariable("stock_no") int stock_no) throws Exception {
        Stock stock = stockService.get(stock_no);
        model.addAttribute("stock", stock);
        return "/stock/amountPage";
    }


}

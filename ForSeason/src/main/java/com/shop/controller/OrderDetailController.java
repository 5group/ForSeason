package com.shop.controller;

import com.shop.dto.OrderDetail;
import com.shop.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("order/detail/")
public class OrderDetailController {
    String dir = "/order/detail";

    @Autowired
    OrderDetailService odtService;

    @RequestMapping("/")
    public String orderDetail() {
        return dir + "/mydetail";
    }

}

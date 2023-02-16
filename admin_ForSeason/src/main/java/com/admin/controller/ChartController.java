package com.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChartController {

    String dir = "chartList/";

    @RequestMapping("/chartList")
    public String chartList(Model model) {
        model.addAttribute("center", dir + "center");
        return "main";
    }


}

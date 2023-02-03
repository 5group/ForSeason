package com.admin.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChartController {

    //data[0] = [{"name":"chrome", "y": "74.77"}, {"name":"", "y": "dd"}

    String dir = "chartList/";

    @RequestMapping("/chartList")
    public String chartList(Model model){
        model.addAttribute("center", dir + "center");
        return "main";
    }


}

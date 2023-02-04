package com.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.shop.dto.Category;
import com.shop.service.CategoryService;

@ControllerAdvice
public class BaseController {
    @Autowired
    CategoryService categoryservice;

    @ModelAttribute
    public void categories(Model model) {
        //Map<Integer, List<Category>> subCateMap = new TreeMap<Integer, List<Category>>();
        List<Category> topCategory = null;
        List<Category> middleCategory = null;
        List<Category> subCategory = null;

        try {
            topCategory = categoryservice.getTopCategory();  // 1,2,3
            middleCategory = categoryservice.getMiddleCategory();  // 10,20,30 ....
            subCategory = categoryservice.getSubCategory();  // 11,12  21,22 ....

        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("topCategory", topCategory);
        model.addAttribute("middleCategory", middleCategory);
        model.addAttribute("subCategory", subCategory);
    }
}


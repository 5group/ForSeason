package com.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.shop.dto.Category;
import com.shop.service.CategoryService;

@ControllerAdvice
public class BaseController {
    @Autowired
    CategoryService categoryService;

    @ModelAttribute
    public void categories(Model model) throws Exception {
        Map<String, List<Category>> mapCateList = new HashMap<String, List<Category>>();
        List<Category> topCategory = topCategory = categoryService.getTopCategory();
        mapCateList.put("top", topCategory);
        List<Category> middleCategory = middleCategory = categoryService.getMiddleCategory();
        mapCateList.put("middle", middleCategory);
        List<Category> subCategory = subCategory = categoryService.getSubCategory();
        mapCateList.put("sub", subCategory);
        model.addAttribute("mapCateList", mapCateList);
    }
}


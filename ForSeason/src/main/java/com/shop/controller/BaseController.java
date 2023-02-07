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
    public void categories(Model model) throws Exception{
        Map<String, List<Category>> mapCateList = new HashMap<String, List<Category>>();
        List<Category> topCategory = null;
        List<Category> middleCategory = null;
        List<Category> subCategory = null;

        topCategory = categoryService.getTopCategory();  // 1,2,3
        mapCateList.put("top", topCategory);
        middleCategory = categoryService.getMiddleCategory();  // 10,20,30 ....
        mapCateList.put("middle", middleCategory);
        subCategory = categoryService.getSubCategory();  // 11,12 21,22 ....
        mapCateList.put("sub", subCategory);

        model.addAttribute("mapCateList", mapCateList);
    }
}


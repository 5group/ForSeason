package com.admin.controller;


import com.admin.dto.Category;
import com.admin.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

@ControllerAdvice
public class BaseController {

    @Autowired
    CategoryService categoryService;

    @ModelAttribute("categories")
    public void categories(Model model) {
        List<Category> topCategory = null;
        List<Category> middleCategory = null;
        List<Category> subCategory = null;
        try {
            topCategory = categoryService.getTopCategory();  //대분류 1,2,3
            middleCategory = categoryService.getMiddleCategory();  //중분류 10,20,30 ....
            subCategory = categoryService.getSubCategory();  //소분류 11,12  21,22 ....
        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("topCategory", topCategory);
        model.addAttribute("middleCategory", middleCategory);
        model.addAttribute("subCategory", subCategory);

    }
}
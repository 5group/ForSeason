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
    public void categories(Model model) throws Exception {
        List<Category> topCategory = categoryService.getTopCategory();
        List<Category> middleCategory = categoryService.getMiddleCategory();
        List<Category> subCategory = categoryService.getSubCategory();
        model.addAttribute("topCategory", topCategory);
        model.addAttribute("middleCategory", middleCategory);
        model.addAttribute("subCategory", subCategory);

    }
}
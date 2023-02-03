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
            topCategory = categoryservice.getTopCategory();  //대분류 1,2,3
            middleCategory = categoryservice.getMiddleCategory();  //중분류 10,20,30 ....
            subCategory = categoryservice.getSubCategory();  //소분류 11,12  21,22 ....

//			for(Category c: middleCategory) {
//				List<Category> subCategory = categoryservice.getSubCategory(c.getCate_no());  //중분류에 따른 소분류 map에 넣기
//				subCateMap.put(c.getCate_no(), subCategory);
//			}

        } catch (Exception e) {
            e.printStackTrace();
        }

        model.addAttribute("topCategory", topCategory);
        model.addAttribute("middleCategory", middleCategory);
        model.addAttribute("subCategory", subCategory);
//		System.out.println(topCategory);
//		System.out.println(middleCategory);
//		System.out.println(cateSubMap);
    }
}

//Map<Integer, List<Integer>> cateMap = new TreeMap<Integer, List<Integer>>();
//Map<Integer, String> cateNameMap = new HashMap<Integer, String>();
//
//try {
//	List<Category> cate = categoryservice.get();  //모든 카테고리 불러오기 1,2,3,10,11 ....
//	for(Category c: cate) {
//		cateNameMap.put(c.getCate_no(), c.getCate_name());
//	}
//	
//	List<Integer> top_catelist = categoryservice.getTopCategory(); //대분류카테고리 불러오기(man, woman, kids)
//	for(Integer i: top_catelist) {
//		List<Integer> sub_catelist = categoryservice.getSubCategory(i); //중분류카테고리 불러오기
//		cateMap.put(i,sub_catelist);
//	}
//	
//	
//
//} catch (Exception e) {
//	e.printStackTrace();
//}
//model.addAttribute("cateNameMap", cateNameMap); 
//model.addAttribute("cateMap", cateMap);
//model.addAttribute("cateMap", cateMap);
//



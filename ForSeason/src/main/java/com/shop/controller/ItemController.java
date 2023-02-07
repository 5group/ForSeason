package com.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.dto.Category;
import com.shop.dto.Color;
import com.shop.dto.Item;
import com.shop.dto.Paging;
import com.shop.dto.Size;
import com.shop.service.CategoryService;
import com.shop.service.FileService;
import com.shop.service.ItemService;
import com.shop.service.StockService;

@Controller
public class ItemController {

    @Value("${custdir}")
    String custdir;

    @Autowired
    ItemService itemservice;

    @Autowired
    StockService stockservice;

    @Autowired
    CategoryService categoryservice;

    @Autowired
    FileService fileService;


    // 대,중,소는 if문으로 확인, 검색&정렬, 페이징도 여기서
    @RequestMapping(value = "/getItemList", method = RequestMethod.GET)
    public String getitemlist(Model model, @RequestParam(value="cate_no", defaultValue="0") int cate_no, Paging paging) throws Exception { // 소분류 리스트 // @RequestParam(value="searchWord", defaultValue="") String searchWord, @RequestParam(value="orderBy", defaultValue="new") String orderBy, 
        Map<Integer, Category> allCateList = new HashMap<Integer, Category>();
        Map<Integer, String> titleImgList = new HashMap<Integer, String>();
        String titleImg = null;
        List<Item> itemList = null;
        Category curCatePath = null;
    
            
        // 현재 카테고리가 어떤 분류인지 확인 (대,중,소)
        Map<String, List<Category>> mapCateList = (Map)model.getAttribute("mapCateList");

        Category c = categoryservice.get(cate_no);  //현재 카테고리정보를 담은 객체

        //카테고리별로 위에 경로 보여지게(메인페이지에서 검색, 정렬은 카테고리 존재 x) & 현재 아이템 리스트에 따른 페이징 & 정렬, 검색
        HashMap<String, Object> curItemInfoMap = new HashMap<String, Object>();
        
        if(c!=null) {   //카테고리정보가 존재할 때 (메인페이지에서 검색 제외)
	        if (mapCateList.get("top").toString().contains(c.toString())) {  //대분류일때
	        	curItemInfoMap.put("top", cate_no);
	            
	        } else if (mapCateList.get("middle").toString().contains(c.toString())) {  //중분류일때
	        	curItemInfoMap.put("middle", cate_no);
	            
	        } else { //소분류일때
	        	curItemInfoMap.put("sub", cate_no);
	        }   
	        curCatePath = categoryservice.getCurCategory(curItemInfoMap);	
        }
       
        
        //초기화 값은 ("") 공백
        System.out.println("searchWord= "+paging.getSearchWord());
        //curItemInfoMap.put("searchWord", searchWord);
        
        //정렬은 항상 적용(초기화 값은 최신순)
        System.out.println("orderBy= "+paging.getOrderBy()); 
    	curItemInfoMap.put("paging", paging);
    	
    	System.out.println("pagingDTO:" + paging);
    	paging.setTotalRecord(itemservice.totalRecord(curItemInfoMap));  //totalRecord set
    	paging.setLimitStart(paging.getNowPage());
    	System.out.println("chage_pagingDTO:" + paging);
    	curItemInfoMap.put("paging", paging);
    	
        System.out.println(curItemInfoMap);
       
        
        itemList = itemservice.getItemList(curItemInfoMap);   //조건에 해당하는 아이템리스트 뽑아오기
        

        for (Item i : itemList) {
            Category category = itemservice.getCategorys(i.getItem_no());  //현재 아이템의 대,중,소 카테고리 모두 불러오기(이미지경로를 알기 위해)
            allCateList.put(i.getItem_no(), category);
            // 타이틀 이미지
            String[] imgnames = fileService.getFileList(custdir,  category.getTop_cate_name(), category.getMid_cate_name(),  category.getCate_name(),  i.getItem_name());
            if(imgnames != null){
                titleImg = imgnames[0];
                titleImgList.put(i.getItem_no(), titleImg);
            }
        }
        
        System.out.println();
        
        
        model.addAttribute("paging", paging);
        model.addAttribute("curItemInfoMap", curItemInfoMap); //현재 아이템이 검색된 결과인지, 어떤 정렬인지의 정보인지, 어느 카테고리번호에 있는지
        model.addAttribute("curCatePath", curCatePath);  //현재 카테고리 경로 표시
        model.addAttribute("cate_no", cate_no);  //현재 카테고리 번호(cate_no)
        model.addAttribute("titleImgList", titleImgList);  //타이틀 이미지 리스트(.jpg)
        model.addAttribute("allCateList", allCateList);  //아이템 하나의 대,중,소 카테고리(이미지 경로)
        model.addAttribute("itemList", itemList);  
        model.addAttribute("center", "item/itemlist");
        return "main";
    }


    @RequestMapping(value = "/itemdetail", method = RequestMethod.GET)
    public String itemdetail(Model model, @RequestParam("item_no") int item_no) throws Exception {
        // System.out.println(top_no+","+mid_no+","+sub_no);
        Item item = itemservice.get(item_no);
        List<Color> colorlist = stockservice.getStockColor(item_no);  //컬러  현재 재고에 있는 컬러
        List<Size> sizelist = stockservice.getStockSize(item_no);  //재고  현재 재고에 있는 사이즈
        Category category = itemservice.getCategorys(item_no); // 해당 아이템에 대중소 카테고리 이름불러옴 //item_no로 카테고리 다 알 수 있음

        String[] cateName = {category.getTop_cate_name(), category.getMid_cate_name(), category.getCate_name()};

        System.out.println("itemdetail : " + category.getTop_cate_name() + "," + category.getMid_cate_name() + "," + category.getCate_name());

        String[] imgnames = fileService.getFileList(custdir,  category.getTop_cate_name(), category.getMid_cate_name(),  category.getCate_name(), item.getItem_name());
        if(imgnames != null){
            System.out.println(imgnames[0]);
        }

        // 아이템 조회수 +1
        itemservice.updateItemhit(item.getItem_no());

        model.addAttribute("item", item);
        model.addAttribute("colorlist", colorlist); // 아이템이 가지고 있는 컬러
        model.addAttribute("sizelist", sizelist); // 아이템이 가지고 있는 사이즈
        model.addAttribute("imgnames", imgnames); // 아이템이 가지고 있는 사진이름들을 배열로
        model.addAttribute("cateName", cateName); // 카테고리 번호들

        model.addAttribute("center", "item/itemdetail");
        return "main";
    }
    
    @RequestMapping("/cartui")
    public String cartui(Model model){    	
    	model.addAttribute("center", "cartui");
    	return "main";
    }

}

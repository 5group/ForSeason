package com.shop.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.shop.service.FileService;
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
import com.shop.dto.Size;
import com.shop.service.CategoryService;
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


    // !!여기만 쓸거임(대,중,소는 if문으로)
    @RequestMapping(value = "/getItemList", method = RequestMethod.GET)
    public String getitemlist(Model model, @RequestParam("cate_no") int cate_no) throws Exception { // 소분류 리스트
        List<Item> list = null;
        Map<Integer, Category> allCateList = new HashMap<Integer, Category>();
        String titleImg = null;
        Map<Integer, String> titleImgList = new HashMap<Integer, String>();


        // 카테고리 확인
        //카테고리가 어떤 분류인지 확인 (대,중,소)
        List<Category> topList = (List) model.getAttribute("topCategory");
        List<Category> midList = (List) model.getAttribute("middleCategory");
        List<Category> subList = (List) model.getAttribute("subCategory");

        Category c = categoryservice.get(cate_no);

        //카테고리별로 위에 경로 보여지게 (현재카테고리경로)
        HashMap<String, Integer> curCateMap = new HashMap<String, Integer>();

        if (topList.toString().contains(c.toString())) {  //만약 대분류에 포함된다면
            list = itemservice.getTopItemList(cate_no);
            curCateMap.put("top", cate_no);
            Category cate = categoryservice.getCurCategory(curCateMap);
            System.out.println(cate);
        } else if (midList.toString().contains(c.toString())) {  //만약 중분류에 포함된다면
            list = itemservice.getMidItemList(cate_no); // 중분류 카테코리 아이템 전부 가져오기
            curCateMap.put("mid", cate_no);
        } else { //중분류 아니면 소분류
            list = itemservice.getSubItemList(cate_no); // 소분류 카테고리 아이템 리스트 가져오기
            curCateMap.put("sub", cate_no);
        }
        // 카테고리 확인

        for (Item i : list) {
            Category category = itemservice.getCategorys(i.getItem_no());
            allCateList.put(i.getItem_no(), category);
            // 타이틀 이미지
            String[] imgnames = fileService.getFileList(custdir,  category.getTop_cate_name(), category.getMid_cate_name(),  category.getCate_name(),  i.getItem_name());
            if(imgnames != null){
                titleImg = imgnames[0];
                System.out.println(imgnames[0]);
                titleImgList.put(i.getItem_no(), titleImg);
            }
        }
        model.addAttribute("curCate", cate_no);  //현재 카테고리
        model.addAttribute("titleImgList", titleImgList);
        model.addAttribute("allCateList", allCateList);
        model.addAttribute("itemlist", list);
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
        model.addAttribute("imgnames", imgnames); // 아이템이 가지고 있는 사진이름 //이렇게 넘기면 안되나...?
        model.addAttribute("cateName", cateName); // 카테고리 번호들

        model.addAttribute("center", "item/itemdetail");
        return "main";
    }

    //아이템 정렬기능
    @RequestMapping(value = "/sortItemList", method = RequestMethod.GET)
    public String sortItemList(Model model, @RequestParam("search_no") int search_no, @RequestParam("cate_no") int cate_no) throws Exception { // 상위리스트
        List<Item> list = null;
        Map<Integer, Category> allCateList = new HashMap<Integer, Category>();
        String titleImg = null;
        Map<Integer, String> titleImgList = new HashMap<Integer, String>();

        //카테고리가 어떤 분류인지 확인 (대,중,소)
        //List<Category> topList = (List)model.getAttribute("topCategory");
        List<Category> midList = (List) model.getAttribute("middleCategory");
        List<Category> subList = (List) model.getAttribute("subCategory");

        Category c = categoryservice.get(cate_no);

        if (midList.toString().contains(c.toString())) {  //만약 중분류에 포함된다면
            System.out.println("중분류");
        } else { //중분류 아니면 소분류
            System.out.println("소븐류");
        }
        return "main";
    }

}

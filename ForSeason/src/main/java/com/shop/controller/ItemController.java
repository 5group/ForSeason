package com.shop.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
import com.shop.dto.Review;
import com.shop.dto.Size;
import com.shop.dto.User;
import com.shop.service.CategoryService;
import com.shop.service.FileService;
import com.shop.service.ItemService;
import com.shop.service.ReviewService;
import com.shop.service.StockService;
import com.shop.service.WishListService;

@Controller
public class ItemController {

    @Value("${custdir}")
    String custdir;

    @Autowired
    ItemService itemservice;

    @Autowired
    StockService stockservice;

    @Autowired
    CategoryService categoryService;

    @Autowired
    WishListService wishListService;

    @Autowired
    FileService fileService;

    @Autowired
    ReviewService reviewservice;

    @RequestMapping(value = "/itemListBest", method = RequestMethod.GET)
    public String itemListBest(Model model) throws Exception {
        Map<Integer, Category> allCateList = new HashMap<Integer, Category>();
        Map<Integer, String> titleImgList = new HashMap<Integer, String>();

        List<Item> itemList = itemservice.getBestItemList();
        for (Item i : itemList) {
            Category category = itemservice.getCategorys(i.getItem_no());
            allCateList.put(i.getItem_no(), category);
            String[] imgnames = fileService.getFileList(custdir, category.getTop_cate_name(), category.getMid_cate_name(), category.getCate_name(), i.getItem_name());
            if (imgnames != null) {
                String titleImg = imgnames[0];
                titleImgList.put(i.getItem_no(), titleImg);
            }
        }
        model.addAttribute("titleImgList", titleImgList);
        model.addAttribute("allCateList", allCateList);
        model.addAttribute("itemList", itemList);
        model.addAttribute("center", "item/itemlistBest");
        return "main";
    }

    @RequestMapping(value = "/getItemList", method = RequestMethod.GET)
    public String getitemlist(Model model, @RequestParam(value = "cate_no", defaultValue = "0") int cate_no, Paging paging) throws Exception { // 소분류 리스트 // @RequestParam(value="searchWord", defaultValue="") String searchWord, @RequestParam(value="orderBy", defaultValue="new") String orderBy,
        Map<Integer, Category> allCateList = new HashMap<Integer, Category>();
        Map<Integer, String> titleImgList = new HashMap<Integer, String>();
        Category curCatePath = null;
        Map<String, List<Category>> mapCateList = (Map) model.getAttribute("mapCateList");
        Category c = categoryService.get(cate_no);
        HashMap<String, Object> curItemInfoMap = new HashMap<String, Object>();
        //
        if (c != null) {
            curItemInfoMap = categoryService.setMapCateListByObjectMap(mapCateList, c);
            curCatePath = categoryService.getCurCategory(curItemInfoMap);
        }
        curItemInfoMap.put("paging", paging);
        paging.setTotalRecord(itemservice.totalRecord(curItemInfoMap));
        paging.setLimitStart(paging.getNowPage());
        List<Item> itemList = itemservice.getItemList(curItemInfoMap);
        for (Item i : itemList) {
            Category category = itemservice.getCategorys(i.getItem_no());
            allCateList.put(i.getItem_no(), category);
            String[] imgnames = fileService.getFileList(custdir, category.getTop_cate_name(), category.getMid_cate_name(), category.getCate_name(), i.getItem_name());
            if (imgnames != null) {
                String titleImg = imgnames[0];
                titleImgList.put(i.getItem_no(), titleImg);
            }
        }
        model.addAttribute("paging", paging);
        model.addAttribute("curCatePath", curCatePath);
        model.addAttribute("cate_no", cate_no);
        model.addAttribute("titleImgList", titleImgList);
        model.addAttribute("allCateList", allCateList);
        model.addAttribute("itemList", itemList);
        model.addAttribute("center", "item/itemlist");
        return "main";
    }

    @RequestMapping(value = "/itemdetail", method = RequestMethod.GET)
    public String itemDetail(Model model, @RequestParam("item_no") int item_no, HttpSession session) throws Exception {
        Item item = itemservice.get(item_no);
        List<Review> reviewlist = reviewservice.SelectSortUdateTests(item_no);
        List<Color> colorlist = stockservice.getStockColor(item_no);
        List<Size> sizelist = stockservice.getStockSize(item_no);
        Category category = itemservice.getCategorys(item_no);
        String[] cateName = {category.getTop_cate_name(), category.getMid_cate_name(), category.getCate_name()};
        String[] imgnames = fileService.getFileList(custdir, category.getTop_cate_name(), category.getMid_cate_name(), category.getCate_name(), item.getItem_name());
        String isCheckWish = "No";
        itemservice.updateItemhit(item.getItem_no());
        User user = (User) session.getAttribute("loginUser");
        if (user != null) isCheckWish = wishListService.checkWishByString(item_no, user.getUser_no());
        model.addAttribute("checkWish", isCheckWish);
        model.addAttribute("item", item);
        model.addAttribute("colorlist", colorlist);
        model.addAttribute("sizelist", sizelist);
        model.addAttribute("imgnames", imgnames);
        model.addAttribute("cateName", cateName);
        model.addAttribute("center", "item/itemdetail");
        model.addAttribute("reviewlist", reviewlist);
        return "main";
    }

    @RequestMapping(value = "/reviewdetail", method = RequestMethod.GET)
    public String reviewdetail(Model model, @RequestParam("item_no") int item_no) throws Exception {
        Item item = itemservice.get(item_no);
        List<Review> reviewlist2 = reviewservice.SelectallSortUdateTests(item_no);
        List<Review> reviewlist3 = reviewservice.SelectallSortScoreDescTests(item_no);
        List<Review> reviewlist4 = reviewservice.SelectallSortScoreAscTests(item_no);
        List<Color> colorlist = stockservice.getStockColor(item_no);
        List<Size> sizelist = stockservice.getStockSize(item_no);
        Category category = itemservice.getCategorys(item_no);
        String[] cateName = {category.getTop_cate_name(), category.getMid_cate_name(), category.getCate_name()};
        String[] imgnames = fileService.getFileList(custdir, category.getTop_cate_name(), category.getMid_cate_name(), category.getCate_name(), item.getItem_name());
        itemservice.updateItemhit(item.getItem_no());
        model.addAttribute("item", item);
        model.addAttribute("colorlist", colorlist);
        model.addAttribute("sizelist", sizelist);
        model.addAttribute("imgnames", imgnames);
        model.addAttribute("cateName", cateName);
        model.addAttribute("reviewlist2", reviewlist2);
        model.addAttribute("reviewlist3", reviewlist3);
        model.addAttribute("reviewlist4", reviewlist4);
        model.addAttribute("center", "item/reviewdetail");
        return "main";
    }


}

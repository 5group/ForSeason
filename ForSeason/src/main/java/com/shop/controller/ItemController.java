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
import com.shop.dto.WishList;
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

    // 대,중,소는 if문으로 확인, 검색&정렬, 페이징도 여기서
    @RequestMapping(value = "/getItemList", method = RequestMethod.GET)
    public String getitemlist(Model model, @RequestParam(value = "cate_no", defaultValue = "0") int cate_no, Paging paging) throws Exception { // 소분류 리스트 // @RequestParam(value="searchWord", defaultValue="") String searchWord, @RequestParam(value="orderBy", defaultValue="new") String orderBy,
        Map<Integer, Category> allCateList = new HashMap<Integer, Category>();
        Map<Integer, String> titleImgList = new HashMap<Integer, String>();
        Category curCatePath = null;
        // 현재 카테고리가 어떤 분류인지 확인 (대,중,소)
        Map<String, List<Category>> mapCateList = (Map) model.getAttribute("mapCateList");
        Category c = categoryService.get(cate_no);  //현재 카테고리정보를 담은 객체
        //카테고리별로 위에 경로 보여지게(메인페이지에서 검색, 정렬은 카테고리 존재 x) & 현재 아이템 리스트에 따른 페이징 & 정렬, 검색
        HashMap<String, Object> curItemInfoMap = new HashMap<String, Object>();
        //
        if (c != null) {   //카테고리정보가 존재할 때 (메인페이지에서 검색 제외)
            curItemInfoMap = categoryService.setMapCateListByObjectMap(mapCateList, c);
            curCatePath = categoryService.getCurCategory(curItemInfoMap);
        }
        curItemInfoMap.put("paging", paging);
        paging.setTotalRecord(itemservice.totalRecord(curItemInfoMap));
        paging.setLimitStart(paging.getNowPage());
        List<Item> itemList = itemservice.getItemList(curItemInfoMap);   //조건에 해당하는 아이템리스트 뽑아오기
        for (Item i : itemList) {
            Category category = itemservice.getCategorys(i.getItem_no());  //현재 아이템의 대,중,소 카테고리 모두 불러오기(이미지경로를 알기 위해)
            allCateList.put(i.getItem_no(), category);
            // 타이틀 이미지
            String[] imgnames = fileService.getFileList(custdir, category.getTop_cate_name(), category.getMid_cate_name(), category.getCate_name(), i.getItem_name());
            if (imgnames != null) {
                String titleImg = imgnames[0];
                titleImgList.put(i.getItem_no(), titleImg);
            }
        }
        model.addAttribute("paging", paging);
        model.addAttribute("curCatePath", curCatePath);  //현재 카테고리 경로 표시
        model.addAttribute("cate_no", cate_no);  //현재 카테고리 번호(cate_no)
        model.addAttribute("titleImgList", titleImgList);  //타이틀 이미지 리스트(.jpg)
        model.addAttribute("allCateList", allCateList);  //아이템 하나의 대,중,소 카테고리(이미지 경로)
        model.addAttribute("itemList", itemList);
        model.addAttribute("center", "item/itemlist");
        return "main";
    }

    @RequestMapping(value = "/itemdetail", method = RequestMethod.GET)
    public String itemDetail(Model model, @RequestParam("item_no") int item_no, HttpSession session) throws Exception {
        Item item = itemservice.get(item_no);
        List<Review> reviewlist = reviewservice.SelectSortUdateTests(item_no); // 설재경 추가 : 리뷰 데이터
        List<Color> colorlist = stockservice.getStockColor(item_no);  //컬러  현재 재고에 있는 컬러
        List<Size> sizelist = stockservice.getStockSize(item_no);  //재고  현재 재고에 있는 사이즈
        Category category = itemservice.getCategorys(item_no); // 해당 아이템에 대중소 카테고리 이름불러옴 //item_no로 카테고리 다 알 수 있음
        String[] cateName = {category.getTop_cate_name(), category.getMid_cate_name(), category.getCate_name()};
        String[] imgnames = fileService.getFileList(custdir, category.getTop_cate_name(), category.getMid_cate_name(), category.getCate_name(), item.getItem_name());
        String isCheckWish = "No";
        // 아이템 조회수 +1
        itemservice.updateItemhit(item.getItem_no());
        User user = (User) session.getAttribute("loginUser");
        if (user != null) isCheckWish = wishListService.checkWishByString(item_no, user.getUser_no());// 로그인 했을 때만 위시리스트 정보 모델에 넣기
        model.addAttribute("checkWish", isCheckWish);
        model.addAttribute("item", item);
        model.addAttribute("colorlist", colorlist); // 아이템이 가지고 있는 컬러
        model.addAttribute("sizelist", sizelist); // 아이템이 가지고 있는 사이즈
        model.addAttribute("imgnames", imgnames); // 아이템이 가지고 있는 사진이름들을 배열로
        model.addAttribute("cateName", cateName); // 카테고리 번호들
        model.addAttribute("center", "item/itemdetail");
        model.addAttribute("reviewlist", reviewlist); // 리뷰를 집어넣었다.
        return "main";
    }

    @RequestMapping(value = "/reviewdetail", method = RequestMethod.GET)
    public String reviewdetail(Model model, @RequestParam("item_no") int item_no) throws Exception {
        Item item = itemservice.get(item_no);
        List<Review> reviewlist2 = reviewservice.SelectallSortUdateTests(item_no); // 설재경 추가 : 리뷰 데이터
        List<Review> reviewlist3 = reviewservice.SelectallSortScoreDescTests(item_no); // 설재경 추가 : 리뷰 데이터
        List<Review> reviewlist4 = reviewservice.SelectallSortScoreAscTests(item_no); // 설재경 추가 : 리뷰 데이터

        List<Color> colorlist = stockservice.getStockColor(item_no);  //컬러  현재 재고에 있는 컬러
        List<Size> sizelist = stockservice.getStockSize(item_no);  //재고  현재 재고에 있는 사이즈
        Category category = itemservice.getCategorys(item_no); // 해당 아이템에 대중소 카테고리 이름불러옴 //item_no로 카테고리 다 알 수 있음

        String[] cateName = {category.getTop_cate_name(), category.getMid_cate_name(), category.getCate_name()};
        String[] imgnames = fileService.getFileList(custdir, category.getTop_cate_name(), category.getMid_cate_name(), category.getCate_name(), item.getItem_name());
        // 아이템 조회수 +1
        itemservice.updateItemhit(item.getItem_no());

        model.addAttribute("item", item);
        model.addAttribute("colorlist", colorlist); // 아이템이 가지고 있는 컬러
        model.addAttribute("sizelist", sizelist); // 아이템이 가지고 있는 사이즈
        model.addAttribute("imgnames", imgnames); // 아이템이 가지고 있는 사진이름 //이렇게 넘기면 안되나...?
        model.addAttribute("cateName", cateName); // 카테고리 번호들
        model.addAttribute("reviewlist2", reviewlist2); // 리뷰를 집어넣었다.
        model.addAttribute("reviewlist3", reviewlist3); // 리뷰를 집어넣었다.
        model.addAttribute("reviewlist4", reviewlist4); // 리뷰를 집어넣었다.
        model.addAttribute("center", "item/reviewdetail");
        return "main";
    }


}

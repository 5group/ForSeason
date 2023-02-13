package com.admin.controller;

import com.admin.dto.Category;
import com.admin.dto.Item;
import com.admin.dto.Stock;
import com.admin.frame.ImgUtil;
import com.admin.service.CategoryService;
import com.admin.service.ItemService;
import com.admin.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/itemList")
public class ItemController {

    @Value("${custdir}")
    String custdir;

    @Value("${admindir}")
    String admindir;

    String dir = "itemList/";

    @Autowired
    ItemService itemService;

    @Autowired
    StockService stockService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    HttpSession session;
    // WOMEN, MEN, KIDS 클릭시
    @RequestMapping(value = "/cate_no={cate_no}")
    public String topCateList(Model model, @PathVariable("cate_no") int cate_no) throws Exception {
        List<Category> categoryList = categoryService.getTopBySubCategory(cate_no);
        List<Item> itemList = itemService.cateListByItemList(categoryList);
        List<Stock> stockList = new ArrayList<>();
        session.setAttribute("itemList", itemList);
        for(Item item :itemList) stockList.addAll(stockService.getItemTotal(item.getItem_name()));
        //System.out.println("topstockList:" + stockList);    
        model.addAttribute("stockList", stockList);
        model.addAttribute("itemList", itemList);
        //model.addAttribute("center", dir + "center");
        model.addAttribute("center", dir+"itemList");
        return "index";
    }

    // 아우터, 상의, 하의 클릭시
    @RequestMapping("/midCate_no={midCate_no}")
    public String midCateItem(Model model, @PathVariable("midCate_no") int midCate_no) throws Exception{
        List<Category> categoryList = categoryService.getMidBySubCategory(midCate_no);
        List<Item> itemList = itemService.cateListByItemList(categoryList);
        List<Stock> stockList = new ArrayList<>();
        session.setAttribute("itemList", itemList);
        for(Item item :itemList) stockList.addAll(stockService.getItemTotal(item.getItem_name()));
        System.out.println("stockList"+stockList);
        model.addAttribute("stockList", stockList);
        model.addAttribute("itemList", itemList);
        //model.addAttribute("center", dir + "center");
        model.addAttribute("center", dir+"itemList");
        //model.addAttribute("center2", "/chartList/cateCenter");
        //return "main";
        return "index";
    }

    @RequestMapping("/itemCate_no={itemCate_no}")
    public String botItem(Model model, @PathVariable("itemCate_no") int cate_no) throws Exception {
        List<Item> itemList = itemService.getCateList(cate_no);
        List<Stock> stockList = new ArrayList<Stock>();
        session.setAttribute("itemList", itemList);
        for(Item item :itemList) stockList.addAll(stockService.getItemTotal(item.getItem_name()));
        model.addAttribute("stockList", stockList);
        model.addAttribute("itemList", itemList);
        //model.addAttribute("center", dir + "center");
        model.addAttribute("center", dir+"itemList");
        //model.addAttribute("center2", "/chartList/cateCenter");
        //return "main";
        return "index";
        //return "table-datatable-basic";
    }

    // Item Detail (아이템 수정)
    @RequestMapping("/itemNo={item_no}")
    public String itemDetail(@PathVariable int item_no, Model model) throws Exception {
        Item item = itemService.get(item_no);
        model.addAttribute("item", item);
        model.addAttribute("center", dir + "itemDetail");
        return "index";
    }

    // python 크롤링 써서 추출한 사진디렉토리를 넘겨주면 자동 생성
    @RequestMapping("/register")
    public String register(@RequestParam("cateNo") int cateNo,
                           @RequestParam("itemName") String itemName,
                           @RequestParam("itemInfo") String itemInfo,
                           @RequestParam("itemPrice") String itemPrice,
                           @RequestParam("img") List<MultipartFile> mpfs,
                           @RequestParam("colorValue") List<Integer> colorValue) throws Exception {
        if (itemService.getName(itemName) != null) {
            System.out.println("이미있는 파일입니다.");
            return "main";
        }
        Item item = new Item(cateNo, itemName, Integer.parseInt(itemPrice), 0, itemInfo, 0, null);
        itemService.register(item);
        int itemNo = item.getItem_no();
        System.out.println("itemNo:" + itemNo);
        for (Integer color_no : colorValue) {
            for (int i = 1; i <= 4; i++) {
                Stock stock = new Stock(itemNo, color_no, i, 100);
                System.out.println("stock:" + stock);
                stockService.register(stock);
            }
        }
        for (MultipartFile mpf : mpfs) ImgUtil.saveFile(mpf, admindir, custdir);
        return "index";
    }

    // Item ADD
    @RequestMapping("/add")
    public String add(Model model){
        model.addAttribute("center", dir+"add");
        return "main";
    }

    // Update -- 진행완료
    @RequestMapping(value = "/itemUpdate", method = RequestMethod.POST)
    public String itemUpdate(Item item) throws Exception {
        itemService.modify(item);
        return "redirect:/itemList/itemNo="+item.getItem_no();
    }

    // Delete -- 나중에 할예정
    @RequestMapping(value = "/itemDelete", method = RequestMethod.POST)
    public String itemDelete(){

        return "redirect:/itemList";
    }
}


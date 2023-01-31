package com.admin.controller;

import com.admin.dto.Item;
import com.admin.dto.Stock;
import com.admin.dto.User;
import com.admin.frame.ImgUtil;
import com.admin.service.ItemService;
import com.admin.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

    @RequestMapping("")
    public String main(Model model) throws Exception {
//        List<Item> itemList = itemService.getItemList();
//        model.addAttribute("itemList", itemList);
        model.addAttribute("center", dir + "center");
        return "main";
    }

    @RequestMapping("/add")
    public String add() {
        return "main";
    }

    @RequestMapping("/register")
    public String register(@RequestParam("cateNo") int cateNo,
                           @RequestParam("itemName") String itemName,
                           @RequestParam("itemInfo") String itemInfo,
                           @RequestParam("itemPrice") String itemPrice,
                           @RequestParam("img") List<MultipartFile> mpfs,
                           @RequestParam("colorValue") List<Integer> colorValue) throws Exception {
        if(itemService.getName(itemName) != null){
            System.out.println("이미있는 파일입니다.");
            return "main";
        }
        Item item = new Item(cateNo, itemName, Integer.parseInt(itemPrice), 0, itemInfo, 0, null);
        itemService.register(item);
        int itemNo = item.getItem_no();
        System.out.println("itemNo:"+ itemNo);
        for(Integer color_no:colorValue){
            for(int i = 1; i <=4; i++){
                Stock stock = new Stock(itemNo, color_no, i, 100);
                System.out.println("stock:"+stock);
                stockService.register(stock);
            }
        }
        for(MultipartFile mpf:mpfs) ImgUtil.saveFile(mpf, admindir, custdir);
        return "main";
    }


}

package com.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.dto.Color;
import com.shop.dto.Item;
import com.shop.dto.Size;
import com.shop.service.ItemService;
import com.shop.service.StockService;

@Controller
public class ItemController {

	@Autowired
	ItemService itemservice;
	
	@Autowired
	StockService stockservice;

	@RequestMapping(value="/getAllItemList", method= RequestMethod.GET) 
    public String allItemList(Model model, @RequestParam("cate_no") int cate_no) {  //상위리스트
		List<Item> itemlist = null;
		try {
			itemlist = itemservice.getAllItemList(cate_no); //상위 카테코리 아이템 전부 가져오기
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("itemlist", itemlist);
		model.addAttribute("center", "item/itemlist"); 
		return "main";
	}
	
	
	@RequestMapping(value="/getItemList", method= RequestMethod.GET)
    public String getitemlist(Model model, @RequestParam("cate_no") int cate_no) {  //서브리스트
		List<Item> list = null;
		//System.out.println("subcate_no"+cate_no);
		try {
			list = itemservice.getItemList(cate_no); //서브 카테고리 아이템 리스트 가져오기
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("itemlist", list);
		model.addAttribute("center", "item/itemlist"); 
		return "main";
	}
	
	
	@RequestMapping(value="/itemdetail", method= RequestMethod.GET)
    public String itemdetail(Model model, @RequestParam("item_no") int item_no) {
		//System.out.println("itemdetail:" + item_no);
		try {
			Item obj = itemservice.get(item_no);
			List<Color> colorlist = stockservice.getStockColor(item_no);   
			List<Size> sizelist = stockservice.getStockSize(item_no);  
			System.out.println(sizelist);
			//아이템 조회수 +1
			itemservice.updateItemhit(obj.getItem_no());
			
			model.addAttribute("item", obj);
			model.addAttribute("colorlist", colorlist); //현재 아이템이 가지고 있는 색깔
			model.addAttribute("sizelist", sizelist);  //현재 아이템이 가지고 있는 사이즈
		} catch (Exception e) {
			e.printStackTrace();
		}

		model.addAttribute("center", "item/itemdetail");
		return "main";
	}
	
	
	// 아이템 검색기능
	@RequestMapping(value="/searchItem", method= RequestMethod.GET) 
    public String searchItem(Model model, @RequestParam("searchitem") String searchitem) {  //상위리스트
		List<Item> list = null;
		try {
			list = itemservice.searchItemList(searchitem); //서브 카테고리 아이템 리스트 가져오기
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		model.addAttribute("itemlist", list);
		model.addAttribute("center", "item/itemlist"); 
		return "main";
	}
}

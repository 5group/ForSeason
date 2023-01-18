package com.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.dto.Item;
import com.shop.service.ItemService;

@Controller
public class ItemController {

	@Autowired
	ItemService itemservice;

	@RequestMapping("/woman")
	public String womanlist(Model model) {
		List<Item> list = null;
		try {
			list = itemservice.getCateList(11); //woman
			model.addAttribute("itemlist", list);
			model.addAttribute("cate", "woman");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "item/itemlist";
	}
	
	@RequestMapping("/man")
	public String manlist(Model model) {
		List<Item> list = null;
		try {
			list = itemservice.getCateList(21);  //man
			model.addAttribute("itemlist", list);
			model.addAttribute("cate", "man");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "item/itemlist";
	}
	
	@RequestMapping("/itemdetail")
	public String itemdetail(Model model, int item_no, String cate) {
		System.out.println("itemdetail:" + item_no);
		Item obj = null;
		try {
			obj = itemservice.get(item_no);
			model.addAttribute("item", obj);
			System.out.println(cate);
			model.addAttribute("cate", cate);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "item/itemdetail";
	}
}

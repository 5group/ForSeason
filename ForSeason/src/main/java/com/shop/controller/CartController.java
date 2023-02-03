package com.shop.controller;

import com.shop.dto.Cart;
import com.shop.dto.Item;
import com.shop.dto.Stock;
import com.shop.dto.User;
import com.shop.service.CartService;
import com.shop.service.ItemService;
import com.shop.service.StockService;
import com.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/cart")
public class CartController {
    String dir = "cart/";

    @Autowired
    UserService userService;

    @Autowired
    StockService stockService;

    @Autowired
    CartService cartService;

    @Autowired
    HttpSession session;

    @Autowired
    ItemService itemService;

    @RequestMapping("")
    public String cart(Model model) throws Exception {
        List<Cart> cartList = (List<Cart>) session.getAttribute("cartList");
        List<Item> itemList = new ArrayList<Item>();
        List<Stock> stockList = new ArrayList<>();
        for(Cart cart:cartList){
            Stock stock = stockService.getColorSizeName(cart.getStock_no());
            Item item = itemService.get(stock.getItem_no());
            itemList.add(item);
            stockList.add(stock);
        }
        model.addAttribute("stock", stockList);
        model.addAttribute("item", itemList);
        model.addAttribute("cart", cartList);
        model.addAttribute("center", dir +"cart");
        return "main";
    }

    @RequestMapping(value = "/deleteCart")
    public String deleteCart(@RequestParam("cart_no") int cart_no) throws Exception {
        User user = (User)session.getAttribute("loginUser");
        cartService.remove(cart_no);
        List<Cart> cartList = cartService.get_list(user.getUser_no());
        session.setAttribute("cartList", cartList);
        return "main";
    }
}


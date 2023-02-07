package com.admin.controller;

import com.admin.dto.Item;
import com.admin.dto.Stock;
import com.admin.service.OrderService;
import com.admin.service.StockService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class DataController {

    @Autowired
    StockService stockService;

    @Autowired
    OrderService orderService;

    @Autowired
    HttpSession session;

    DateFormat sbFormat = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping("/stockList")
    public Object stockList(String sm, String em, int index, String name) throws Exception {
        List<Stock> stockList = stockService.getChartList(index, name, sm, em);
        JSONObject jObject = new JSONObject();
        List<Integer> totalList = new ArrayList<Integer>();
        JSONArray dateList = new JSONArray();
        JSONArray itemNameList = new JSONArray();
        for (Stock st : stockList) {
            dateList.add(sbFormat.format(st.getOrder_udate()));
            totalList.add(st.getOrder_tot());
            itemNameList.add(st.getItem_name());
        }
        jObject.put("dayDate", dateList);
        jObject.put("dayTotal", totalList);
        System.out.println(jObject);
        return jObject;
    }

    @RequestMapping("/cateStockList")
    public Object cateStockList(String sm, String em) throws Exception {
        List<Item> itemList = (List<Item>) session.getAttribute("itemList");
        List<Stock> stockList = new ArrayList<Stock>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        List<Integer> itemNoList = new ArrayList<Integer>();
        for (Item item :itemList) itemNoList.add(item.getItem_no());
        map.put("itemNoList", itemNoList);
        map.put("sm", sm);
        map.put("em", em);
        stockList.addAll(stockService.getCateTotal(map));
        JSONObject jObject = new JSONObject();
        List<Integer> totalList = new ArrayList<Integer>();
        JSONArray dateList = new JSONArray();
        for (Stock st : stockList) {
            dateList.add(sbFormat.format(st.getOrder_udate()));
            totalList.add(st.getOrder_tot());
        }
        jObject.put("dayDate", dateList);
        jObject.put("dayTotal", totalList);
        System.out.println(jObject);
        return jObject;
    }


    @RequestMapping("/itemPiChart")
    public Object itemPiChart() {

        return null;
    }
}

package com.admin.controller;

import com.admin.dto.Order;
import com.admin.dto.Stock;
import com.admin.service.OrderService;
import com.admin.service.StockService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

@RestController
public class DataController {

    @Autowired
    StockService stockService;

    @Autowired
    OrderService orderService;

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
}

package com.admin.controller;

import com.admin.dto.Item;
import com.admin.dto.OrderDetail;
import com.admin.dto.Stock;
import com.admin.service.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    OrderDetailService orderDetailService;

    @Autowired
    JsonService jsonService;

    @Autowired
    ItemService itemService;

    @Autowired
    HttpSession session;

    DateFormat sbFormat = new SimpleDateFormat("yyyy-MM-dd");

    @RequestMapping("/stockList")
    public Object stockList(String sm, String em) throws Exception {
        List<Stock> stockList = stockService.getChartList(sm, em);
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

    @RequestMapping(value = "/itemList/disCntUpdate", method = RequestMethod.POST)
    public void disCntUpdate(@RequestParam(value = "noList[]") List<Integer> noList,  @RequestParam int item_discnt) throws Exception {
        Item item = new Item();
        for(Integer no:noList){
            item.setItem_no(no);
            item.setItem_discnt(item_discnt);
            itemService.modifyDiscnt(item);
        }
    }

    @RequestMapping("/pieChart")
    public JSONObject pieChart(){
        JSONObject jObject = new JSONObject();
        List<Integer> allTotalList = new ArrayList<Integer>();
        List<OrderDetail> kidsTotalList = orderDetailService.getTopCateNameByTotalList("KIDS");
        List<OrderDetail> menTotalList = orderDetailService.getTopCateNameByTotalList("MEN");
        List<OrderDetail> womenTotalList = orderDetailService.getTopCateNameByTotalList("WOMEN");
        totalListAdd(allTotalList, kidsTotalList);
        totalListAdd(allTotalList, menTotalList);
        totalListAdd(allTotalList, womenTotalList);
        jObject.put("total", allTotalList);
        System.out.println("piechart:"+jObject);
        return jObject;
    }

    @RequestMapping("/bubbleChart")
    public JSONArray bubbleChart(){
        String kids = "KIDS";
        String men = "MEN";
        String women = "WOMEN";
        JSONArray jsonArray = new JSONArray();
        jsonArray.add(jsonService.totalListByJSONObject(orderDetailService.getTopCateNameByTotalList(kids), kids, "#FF0000"));
        jsonArray.add(jsonService.totalListByJSONObject(orderDetailService.getTopCateNameByTotalList(men), men, "#00FF00"));
        jsonArray.add(jsonService.totalListByJSONObject(orderDetailService.getTopCateNameByTotalList(women), women, "#0000FF"));
        System.out.println("bubblechart:"+jsonArray);
        return jsonArray;
    }

    public List<Integer> totalListAdd(List<Integer> allTotalList, List<OrderDetail> cateTotalList){
        int index = 0;
        for (OrderDetail orderDetail :cateTotalList){
            index += orderDetail.getOrder_tot();
        }
        allTotalList.add(index);
        return allTotalList;
    }


    @RequestMapping("/updateAmount")
    public void updateAmount(@ModelAttribute Stock stock) throws Exception {
        stockService.modify(stock);
    }
}
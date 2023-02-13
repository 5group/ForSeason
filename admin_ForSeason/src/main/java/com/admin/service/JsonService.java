package com.admin.service;

import com.admin.dto.OrderDetail;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class JsonService {
    public JSONObject totalListByJSONObject(List<OrderDetail> cateTotalList, String gender, String color){
        Map<String, Integer> result = new HashMap<>();
        JSONObject jsonResult = new JSONObject();
        List<Object> resultDataList= new ArrayList<Object>();
        for (OrderDetail orderDetail : cateTotalList) {
            String cateName = orderDetail.getCate_name();
            int orderTotal = orderDetail.getOrder_tot();
            if (result.containsKey(cateName)) {
                result.put(cateName, result.get(cateName) + orderTotal);
            } else {
                result.put(cateName, orderTotal);
            }
        }
        for(String key :result.keySet()){
            Map<String, Object> nameAndDataByMap = new HashMap<>();
            nameAndDataByMap.put("name", key);
            nameAndDataByMap.put("value", result.get(key));
            nameAndDataByMap.put("color", "#FFFFFF");
            resultDataList.add(nameAndDataByMap);
        }
        jsonResult.put("name", gender);
        jsonResult.put("color", color);
        jsonResult.put("data", resultDataList);
        return jsonResult;
    }
}
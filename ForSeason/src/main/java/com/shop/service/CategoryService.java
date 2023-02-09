package com.shop.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.dto.Category;
import com.shop.frame.MyService;
import com.shop.mapper.CategoryMapper;

@Service
public class CategoryService implements MyService<Integer, Category> {

    @Autowired
    CategoryMapper mapper;

    @Override
    public void register(Category category) throws Exception {
        mapper.insert(category);
    }

    @Override
    public void remove(Integer no) throws Exception {
        mapper.delete(no);
    }

    @Override
    public void modify(Category category) throws Exception {
        mapper.update(category);
    }

    @Override
    public Category get(Integer no) throws Exception {
        return mapper.select(no);
    }

    @Override
    public List<Category> get() throws Exception {
        return mapper.selectAll();
    }
    
    public List<Category> getTopCategory() throws Exception{
    	return mapper.getTopCategory();
    }
    
    public List<Category> getMiddleCategory() throws Exception{
    	return mapper.getMiddleCategory();
    }
    
    public List<Category> getSubCategory() throws Exception{
    	return mapper.getSubCategory();
    }
    
    public Category getCurCategory(HashMap<String, Object> curCateMap) throws Exception{
    	return mapper.selectCurCategory(curCateMap);
    }

    public HashMap<String, Object> setMapCateListByObjectMap(Map<String, List<Category>> mapCateList, Category category) {
        HashMap<String, Object> curItemInfoMap = new HashMap<String, Object>();
        //카테고리정보가 존재할 때 (메인페이지에서 검색 제외)
        if (mapCateList.get("top").toString().contains(category.toString())) {  //대분류일때
            curItemInfoMap.put("top", category.getCate_no());

        } else if (mapCateList.get("middle").toString().contains(category.toString())) {  //중분류일때
            curItemInfoMap.put("middle", category.getCate_no());

        } else { //소분류일때
            curItemInfoMap.put("sub", category.getCate_no());
        }
        return curItemInfoMap;
    }
    
}

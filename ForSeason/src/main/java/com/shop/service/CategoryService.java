package com.shop.service;

import java.util.HashMap;
import java.util.List;

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
    
    public Category getCurCategory(HashMap<String, Integer> curCateMap) throws Exception{
    	return mapper.selectCurCategory(curCateMap);
    }
}

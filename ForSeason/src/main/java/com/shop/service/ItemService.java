package com.shop.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.dto.Category;
import com.shop.dto.Item;
import com.shop.frame.MyService;
import com.shop.mapper.ItemMapper;

@Service
public class ItemService implements MyService<Integer, Item> {

    @Autowired
    ItemMapper mapper;

    @Override
    public void register(Item item) throws Exception {
        mapper.insert(item);
    }

    @Override
    public void remove(Integer no) throws Exception {
        mapper.delete(no);
    }

    @Override
    public void modify(Item item) throws Exception {
        mapper.update(item);
    }

    @Override
    public Item get(Integer no) throws Exception {
        return mapper.select(no);
    }

    @Override
    public List<Item> get() throws Exception {
        return mapper.selectAll();
    }
    
   
    public List<Item> getItemList(HashMap<String, Object> curCateMap) throws Exception {
        return mapper.selectItemList(curCateMap);
    } 
    public Category getCategorys(Integer item_no) throws Exception{
    	return mapper.selectCategorys(item_no);
    }
    public void updateItemhit(Integer item_no) throws Exception{
    	mapper.updateItemhit(item_no);
    }
    public Integer totalRecord(HashMap<String, Object> curCateMap) throws Exception{
    	return mapper.totalRecord(curCateMap);
    }
    
    public List<Item> getBestItemList() throws Exception {
        return mapper.selectBestItemList();
    }

    public List<Item> midCateByItemList(int cate_no) throws Exception {
        return mapper.selectMiddleCateNoByItem(cate_no);
    }

}

package com.shop.service;

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
    


    public List<Item> getSubItemList(Integer cate_no) throws Exception{
    	return mapper.selectSubItemList(cate_no);
    }
    public List<Item> getMidItemList(Integer cate_no) throws Exception {
        return mapper.selectMidItemList(cate_no);
    }
    public List<Item> getTopItemList(Integer cate_no) throws Exception {
        return mapper.selectTopItemList(cate_no);
    }


    
    public List<Item> searchItemList(String search) throws Exception{	
    	return mapper.searchItemList(search);
    }
    
    public void updateItemhit(Integer item_no) throws Exception{
    	mapper.updateItemhit(item_no);
    }
    
    public Category getCategorys(Integer item_no) throws Exception{
    	return mapper.selectCategorys(item_no);
    }

}

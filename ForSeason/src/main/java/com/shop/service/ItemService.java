package com.shop.service;

import com.shop.dto.Item;
import com.shop.frame.MyService;
import com.shop.mapper.ItemMapper;
import com.shop.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return mapper.selectall();
    }
    
    public List<Item> getItemList(Integer cate_no) throws Exception{	
    	return mapper.getItemList(cate_no);
    }
    
    public List<Item> getAllItemList(Integer cate_no) throws Exception {
        return mapper.getAllItemList(cate_no);
    }  
    
    public List<Item> searchItemList(String search) throws Exception{	
    	return mapper.searchItemList(search);
    }
    
    public void updateItemhit(Integer item_no) throws Exception{
    	mapper.updateItemhit(item_no);
    }
    
}

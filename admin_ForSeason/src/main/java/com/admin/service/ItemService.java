package com.admin.service;

import com.admin.dto.Item;
import com.admin.frame.MyService;
import com.admin.mapper.ItemMapper;
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

    public Item getName(String name) throws Exception {
        return mapper.selectName(name);
    }

    @Override
    public List<Item> get() throws Exception {
        return mapper.selectall();
    }
    
    public List<Item> getCateList(Integer cate_no) throws Exception {
        return mapper.getCateList(cate_no);
    }

    public List<Item> getItemList() throws Exception{
        return mapper.selectItemList();
    }
}

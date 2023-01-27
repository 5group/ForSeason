package com.admin.service;

import com.admin.dto.WishList;
import com.admin.frame.MyService;
import com.admin.mapper.WishListMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishListService implements MyService<Integer, WishList> {
    @Autowired
    WishListMapper mapper;

    @Override
    public void register(WishList wishList) throws Exception {
        mapper.insert(wishList);
    }

    @Override
    public void remove(Integer id) throws Exception {
mapper.delete(id);
    }

    @Override
    public void modify(WishList wishList) throws Exception {
mapper.update(wishList);
    }

    @Override
    public WishList get(Integer id) throws Exception {
        return mapper.select(id);
    }

    @Override
    public List<WishList> get() throws Exception {
        return mapper.selectall();
    }
}

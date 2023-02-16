package com.shop.service;

import com.shop.dto.WishList;
import com.shop.frame.MyService;
import com.shop.mapper.WishListMapper;
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
        return mapper.selectAll();
    }

    public void deleteUserWish(WishList wishList) throws Exception {
        mapper.deleteUserWish(wishList);
    }

    public WishList checkUserWish(WishList wishList) throws Exception {
        return mapper.checkUserWish(wishList);
    }

    public List<WishList> get_list(int user_no) throws Exception {
        return mapper.selectList(user_no);
    }

    public String checkWishByString(int item_no, int user_no) throws Exception {
        WishList wishList = new WishList();
        wishList.setUser_no(user_no);
        wishList.setItem_no(item_no);
        WishList checkWish = checkUserWish(wishList);
        if (checkWish != null) return "Yes";
        return "No";
    }
}

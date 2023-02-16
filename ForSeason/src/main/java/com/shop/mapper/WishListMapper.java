package com.shop.mapper;

import com.shop.dto.WishList;
import com.shop.frame.MyMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface WishListMapper extends MyMapper<Integer, WishList> {
    public void deleteUserWish(WishList wishList) throws Exception;

    public WishList checkUserWish(WishList wishList) throws Exception;

    public List<WishList> selectList(int user_no);
}

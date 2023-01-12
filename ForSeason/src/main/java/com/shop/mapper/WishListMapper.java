package com.shop.mapper;

import com.shop.dto.WishList;
import com.shop.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface WishListMapper extends MyMapper<Integer, WishList> {

}

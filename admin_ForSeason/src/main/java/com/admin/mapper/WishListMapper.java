package com.admin.mapper;

import com.admin.dto.WishList;
import com.admin.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface WishListMapper extends MyMapper<Integer, WishList> {

}

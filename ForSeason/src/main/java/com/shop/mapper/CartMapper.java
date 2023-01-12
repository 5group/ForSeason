package com.shop.mapper;

import com.shop.dto.Cart;
import com.shop.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface CartMapper extends MyMapper<Integer, Cart> {

}

package com.shop.mapper;

import com.shop.dto.Cart;
import com.shop.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CartMapper extends MyMapper<Integer, Cart> {
    public List<Cart> selectList(int user_no);
}

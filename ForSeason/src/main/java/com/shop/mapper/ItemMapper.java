package com.shop.mapper;

import com.shop.dto.Item;
import com.shop.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ItemMapper extends MyMapper<Integer, Item> {

}

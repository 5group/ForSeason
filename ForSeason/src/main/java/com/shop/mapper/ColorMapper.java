package com.shop.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.shop.dto.Color;
import com.shop.frame.MyMapper;

@Repository
@Mapper
public interface ColorMapper extends MyMapper<Integer, Color> {
}

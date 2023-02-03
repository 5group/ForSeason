package com.shop.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.shop.dto.Color;
import com.shop.dto.Size;
import com.shop.dto.Stock;
import com.shop.frame.MyMapper;

@Repository
@Mapper
public interface ColorMapper extends MyMapper<Integer, Color> {
}

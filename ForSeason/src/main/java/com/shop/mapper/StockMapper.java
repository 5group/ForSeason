package com.shop.mapper;

import com.shop.dto.Stock;
import com.shop.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
@Mapper
public interface StockMapper extends MyMapper<Integer, Stock> {
}

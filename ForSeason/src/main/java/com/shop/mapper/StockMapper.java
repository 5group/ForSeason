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
public interface StockMapper extends MyMapper<Integer, Stock> {
    public List<Color> getStockColor(Integer item_no) throws Exception;

    public List<Size> getStockSize(Integer item_no) throws Exception;

    public int getStockNo(HashMap<String, Integer> map) throws Exception;

    public Stock selectSizeColor(Integer stock_no) throws Exception;

    public List<Size> selectStock(Integer item_no, Integer color_no) throws Exception;

}

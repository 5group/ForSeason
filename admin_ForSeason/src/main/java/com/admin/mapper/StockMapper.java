package com.admin.mapper;

import com.admin.dto.Stock;
import com.admin.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface StockMapper extends MyMapper<Integer, Stock> {

    public List<Stock> selectAdminMain();
    public List<Stock> selectChart(@Param("start_date") String sm, @Param("end_date") String em);
    public List<Stock> selectItemChart(String item_name);
    public List<Stock> selectCateChart(String cate_name);



}

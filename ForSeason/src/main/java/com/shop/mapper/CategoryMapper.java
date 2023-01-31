package com.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.shop.dto.Category;
import com.shop.frame.MyMapper;

@Repository
@Mapper
public interface CategoryMapper extends MyMapper<Integer, Category> {
	public List<Integer> getTopCategory() throws Exception;
	public List<Integer> getSubCategory(Integer cate_no2) throws Exception;
}

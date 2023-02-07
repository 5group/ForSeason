package com.shop.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.shop.dto.Category;
import com.shop.frame.MyMapper;

@Repository
@Mapper
public interface CategoryMapper extends MyMapper<Integer, Category> {
	public List<Category> getTopCategory() throws Exception;
	public List<Category> getMiddleCategory() throws Exception;
	public List<Category> getSubCategory() throws Exception;
	public Category selectCurCategory(HashMap<String, Object> curCateMap) throws Exception;
}

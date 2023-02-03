package com.shop.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.shop.dto.Category;
import com.shop.dto.Item;
import com.shop.frame.MyMapper;

@Repository
@Mapper
public interface ItemMapper extends MyMapper<Integer, Item> {
	public List<Item> selectSubItemList(Integer cate_no) throws Exception;
	public List<Item> selectMidItemList(Integer cate_no) throws Exception;
	public List<Item> selectTopItemList(Integer cate_no) throws Exception;
	
	public List<Item> searchItemList(String search) throws Exception;
	public void updateItemhit(Integer item_no) throws Exception;
	public Category selectCategorys(Integer item_no) throws Exception;
}

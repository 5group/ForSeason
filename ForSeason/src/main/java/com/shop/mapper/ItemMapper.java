package com.shop.mapper;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.shop.dto.Category;
import com.shop.dto.Item;
import com.shop.frame.MyMapper;

@Repository
@Mapper
public interface ItemMapper extends MyMapper<Integer, Item> {
	public List<Item> selectItemList(HashMap<String, Object> curCateMap) throws Exception; 
	public Category selectCategorys(Integer item_no) throws Exception;
	public void updateItemhit(Integer item_no) throws Exception;
	public Integer totalRecord(HashMap<String, Object> curCateMap) throws Exception;
	public List<Item> selectBestItemList() throws Exception;

	public List<Item> selectMiddleCateNoByItem(int cate_no) throws Exception;


}

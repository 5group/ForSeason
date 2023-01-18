package com.shop.mapper;

import com.shop.dto.Item;
import com.shop.frame.MyMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ItemMapper extends MyMapper<Integer, Item> {
	public List<Item> getCateList(Integer cate_no) throws Exception;
}

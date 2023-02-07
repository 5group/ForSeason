package com.admin.mapper;

import com.admin.dto.Item;
import com.admin.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface ItemMapper extends MyMapper<Integer, Item> {
	public List<Item> getCateList(Integer cate_no) throws Exception;
	public List<Item> selectItemList() throws Exception;
    public Item selectName(String name) throws Exception;
    public void updateDiscnt(Item item) throws Exception;
}

package com.admin.mapper;

import com.admin.dto.Category;
import com.admin.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
@Mapper
public interface CategoryMapper extends MyMapper<Integer, Category> {
    public List<Category> getTopCategory() throws Exception;

    public List<Category> getMiddleCategory() throws Exception;

    public List<Category> getSubCategory() throws Exception;

    public Category selectCurCategory(HashMap<String, Integer> curCateMap) throws Exception;

    public List<Category> selectTopBySubCate(Integer no) throws Exception;

    public List<Category> selectMidBySubCate(Integer no) throws Exception;
}

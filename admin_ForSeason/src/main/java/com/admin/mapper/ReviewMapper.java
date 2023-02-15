package com.admin.mapper;

import com.admin.dto.Review;
import com.admin.frame.MyMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReviewMapper extends MyMapper<Integer, Review> {

	List<Review> revselect();

	Review revnoselect(int id);

}

package com.shop.mapper;

import com.shop.dto.Review;
import com.shop.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReviewMapper extends MyMapper<Integer, Review> {

}

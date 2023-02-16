package com.shop.mapper;

import com.shop.dto.Review;
import com.shop.frame.MyMapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ReviewMapper extends MyMapper<Integer, Review> {


    List<Review> SelectSortUdateTests(int item_no);

    List<Review> SelectallSortUdateTests(int item_no);

    List<Review> SelectallSortScoreDescTests(int item_no);

    List<Review> SelectallSortScoreAscTests(int item_no);

    List<Review> userselect(int id);

    List<Review> selectlist(int id);

    Review revnoselect(Integer id);

}

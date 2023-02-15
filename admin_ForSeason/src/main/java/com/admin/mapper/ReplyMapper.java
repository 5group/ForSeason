package com.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.admin.dto.Reply;
import com.admin.frame.MyMapper;

@Mapper
@Repository
public interface ReplyMapper extends MyMapper<Integer, Reply> {

}

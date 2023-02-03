package com.admin.mapper;

import com.admin.dto.Admin;
import com.admin.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface AdminMapper extends MyMapper<String, Admin> {

}

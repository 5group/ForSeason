package com.shop.mapper;

import com.shop.dto.Ship;
import com.shop.frame.MyMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ShipMapper extends MyMapper<Integer, Ship> {

}

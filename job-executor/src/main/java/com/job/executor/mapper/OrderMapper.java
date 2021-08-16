package com.job.executor.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface OrderMapper {

	@Select("SELECT SUM(amount) addMoney,MAX(paid_time) orderTime FROM `order` WHERE STATUS = '2' AND paid_time > #{orderTime} ")
	Map getNewRecharge(@Param("orderTime") long orderTime);
}

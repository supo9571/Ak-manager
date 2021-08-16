package com.job.executor.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.Map;

@Mapper
public interface OrderMapper {

	@Select("SELECT SUM(amount) addOrderMoney,MAX(paid_time) orderTime FROM `order` WHERE STATUS = '2' AND paid_time > #{orderTime} ")
	Map getNewRecharge(@Param("orderTime") String orderTime);

	@Select("SELECT SUM(amount) addWithdrawMoney,MAX(UNIX_TIMESTAMP(UpdateAt)) withdrawTime FROM `withdraw` WHERE STATUS = '3' AND UNIX_TIMESTAMP(updateAt) > #{withdrawTime} ")
    Map getNewWithdraw(@Param("withdrawTime")String withdrawTime);
}

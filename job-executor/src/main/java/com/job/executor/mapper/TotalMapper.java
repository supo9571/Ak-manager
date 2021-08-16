package com.job.executor.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

@Mapper
public interface TotalMapper {

	@Select("select order_time from sys_total limit 0,1")
	int getOrderTime();

	@Update("update sys_total set total_recharge = total_recharge + #{addMoney} ,order_time = #{time}")
	void updateTotalRecharge(@Param("addMoney") BigDecimal addMoney, @Param("time") long time);
}

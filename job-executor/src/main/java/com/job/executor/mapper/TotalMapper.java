package com.job.executor.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;
import java.util.Map;

@Mapper
public interface TotalMapper {

	@Select("select order_time orderTime,withdraw_time withdrawTime from sys_total limit 0,1")
	Map getTimes();

	@Update("update sys_total set total_recharge = total_recharge + #{addOrderMoney} ,order_time = #{orderTime} " +
			",total_withdraw = total_withdraw + #{addWithdrawMoney} ,withdraw_time = #{withdrawTime}")
	void updateTotalRecharge(@Param("addOrderMoney") BigDecimal addOrderMoney, @Param("orderTime") long orderTime
			,@Param("addWithdrawMoney") BigDecimal addWithdrawMoney, @Param("withdrawTime") long withdrawTime);
}

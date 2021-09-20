package com.job.executor.mapper;

import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface TotalMapper {

	@Delete("delete from data_online")
	void cleanOnline();

	void insertOnline(List list);

	@Select("select COUNT(DISTINCT(uid)) from data_login where mstime >= #{time}")
    int selectTodayLogins(Long time);

	void saveTodayLogins(@Param("date") String date, @Param("num") int num);

	@Update("update data_register set today_add = '0',today_red = '0',today_give = '0',today_water = '0',today_win = '0'")
    void updateRegister();

	@Delete("delete from data_water where time = #{time} ")
	void deleteWater(@Param("time") Long time);
}

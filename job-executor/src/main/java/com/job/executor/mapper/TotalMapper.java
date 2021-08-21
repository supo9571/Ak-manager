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

	@Select("select count(1) from data_login where mstime >= #{time}")
    int selectTodayLogins(Long time);

	void saveTodayLogins(@Param("date") String date, @Param("num") int num);
}

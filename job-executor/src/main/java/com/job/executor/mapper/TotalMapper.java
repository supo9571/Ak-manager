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
}

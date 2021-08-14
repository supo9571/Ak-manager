package com.job.executor.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TestMapper {

	Integer queryCount();

	@Update("update sys_test set name = #{name} where name = '999'")
	Integer updateAdminByName(@Param("name") String name);

}

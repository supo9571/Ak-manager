package com.data.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author marvin 2021/8/28
 */
@Mapper
public interface UserMapper {
    @Select("select * from data_user where phone = #{phone}")
    Integer findByphone(@Param("phone") String phone);
}

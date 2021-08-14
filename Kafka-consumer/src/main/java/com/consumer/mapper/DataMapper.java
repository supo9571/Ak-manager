package com.consumer.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DataMapper {

    void updateUserOnline(@Param("value")String value,@Param("time") String time);

    void insertMsg(@Param("key")String key, @Param("op")String op, @Param("msg")String msg);
}

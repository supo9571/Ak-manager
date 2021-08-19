package com.consumer.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface DataMapper {

    void insertMsg(@Param("key")String key, @Param("op")String op, @Param("msg")String msg);
}

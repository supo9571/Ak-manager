package com.data.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author marvin 2021/9/29
 */
@Mapper
public interface AgentMapper {

    List getCommissionList(@Param("tid") Integer tid,@Param("uid") String uid,@Param("date")String date);
}

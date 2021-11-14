package com.job.executor.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 子游戏实时数据
 * @author sieGuang 2021/10/01
 */
@Mapper
public interface CountSubGameMapper {

    List getGameCardInfo(@Param("beginTime") String beginTime,@Param("endTime") String endTime);

    void saveGameCardInfo(@Param("date")String date,@Param("list") List list);

}

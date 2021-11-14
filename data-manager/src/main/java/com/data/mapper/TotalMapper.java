package com.data.mapper;

import com.manager.common.core.domain.model.Summarize;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/10/6
 */
@Mapper
public interface TotalMapper {

    List getTotals(Summarize summarize);

    Map getLeft(@Param("tid") String tid, @Param("date") String date);

    BigDecimal getYesterdayBalance(@Param("tid")String tid,@Param("date") String yesterday);

    Integer getOnlinePlayer(@Param("tid")String tid);

    Map getRight(@Param("tid") String tid,@Param("beginTime") String beginTime,@Param("endTime") String endTime);
}

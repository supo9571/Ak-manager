package com.manager.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author marvin 2021/10/15
 */
@Mapper
public interface ResultMapper {

    List getGameResult(@Param("tid") int tid,@Param("strategyId") int strategyId,@Param("day") String day);

    List getPersonResult(@Param("tid") int tid, @Param("strategyId") int strategyId, @Param("uid") int uid, @Param("day") String day, @Param("cardName") String cardName, @Param("cardUserName") String cardUserName);

    List getPersonResultInfo(@Param("uid")int uid, @Param("strategyFlag")String strategyFlag,@Param("cardName")String cardName);
}

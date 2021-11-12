package com.manager.system.mapper;

import com.manager.system.domain.vo.GameResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/10/15
 */
@Mapper
public interface ResultMapper {

    List getGameResult(@Param("tid") int tid,@Param("strategyId") int strategyId,@Param("day") String day);

    List getPersonResult(@Param("tid") int tid, @Param("strategyId") int strategyId, @Param("uid") int uid, @Param("day") String day, @Param("cardName") String cardName, @Param("cardUserName") String cardUserName);

    Map getPersonResultCount(@Param("tid") int tid, @Param("strategyId") int strategyId, @Param("uid") int uid, @Param("day") String day, @Param("cardName") String cardName, @Param("cardUserName") String cardUserName);

    List getPersonResultInfo(@Param("uid")int uid, @Param("strategyFlag")String strategyFlag,@Param("cardName")String cardName);

    @Select("select endtime from data_game_result order by endtime desc limit 0,1")
    String getEndTime();

    List selectGameResult(@Param("beginTime") String beginTime,@Param("endTime") String endTime,String cardName);

    Long getGameCount(@Param("beginTime") String beginTime,@Param("endTime") String endTime);

    void saveGameResult(List<GameResult> list);
}

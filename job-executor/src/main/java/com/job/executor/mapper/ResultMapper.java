package com.job.executor.mapper;

import com.job.executor.domain.GameResult;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author marvin 2021/10/15
 */
@Mapper
public interface ResultMapper {

    List selectGameResult(@Param("beginTime") String beginTime,@Param("endTime") String endTime);

    Long getGameCount(@Param("beginTime") String beginTime,@Param("endTime") String endTime);

    void saveGameResult(List<GameResult> list);

    @Select("select endtime from data_game_result order by endtime desc limit 0,1")
    String getEndTime();
}

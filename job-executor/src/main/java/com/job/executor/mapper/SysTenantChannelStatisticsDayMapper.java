package com.job.executor.mapper;

import com.job.executor.domain.SysTenantChannelStatisticsDay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author jason
 * @date 2021-09-30
 */
@Mapper
public interface SysTenantChannelStatisticsDayMapper {

    int saveChannelStatisticsDay(List<SysTenantChannelStatisticsDay> statisticsDay);

    @Select("select date_hour_key from sys_config limit 0,1")
    String getLastDate();

    @Update("update sys_config set date_hour_key = #{nextDateTime}")
    void setLastDate(@Param("nextDateTime") String nextDateTime);
}

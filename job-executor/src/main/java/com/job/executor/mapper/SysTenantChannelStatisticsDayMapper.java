package com.job.executor.mapper;

import com.job.executor.domain.SysTenantChannelStatisticsDay;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author jason
 * @date 2021-09-30
 */
@Mapper
public interface SysTenantChannelStatisticsDayMapper {

    int saveChannelStatisticsDay(List<SysTenantChannelStatisticsDay> statisticsDay);
}

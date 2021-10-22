package com.job.executor.mapper;

import com.job.executor.domain.OnlineDataDay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 实时在线数据
 * @author sieGuang 2021/10/21
 */
@Mapper
public interface OnlineDataDayMapper {

    /**
     * 获取当前在线数据
     */
    List<OnlineDataDay> getOnlineDataDay();

    /**
     *  当前在线数据插入表
     */
    void insertOnlineDataDay(List<OnlineDataDay> list);

}

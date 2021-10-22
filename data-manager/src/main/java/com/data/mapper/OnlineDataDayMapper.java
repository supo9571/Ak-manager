package com.data.mapper;

import com.manager.common.core.domain.model.OnlineDataDay;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 实时在线数据
 * @author sieGuang 2021/10/21
 */
@Mapper
public interface OnlineDataDayMapper {

    /**
     * 查询
     * @param onlineDataDay 过滤条件
     */
    List<OnlineDataDay> getOnlineUserData(OnlineDataDay onlineDataDay);

    Integer getAvgNum(OnlineDataDay onlineDataDay);

    List<OnlineDataDay> getPcuData(OnlineDataDay onlineDataDay);

    //

    /**
     * @param day : 1.当天 2.昨天 空：历史
     */
    Map<String,String> getMaxAndAvg(@Param("odd") OnlineDataDay odd, @Param("day") Integer day);

}

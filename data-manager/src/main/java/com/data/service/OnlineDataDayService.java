package com.data.service;

import com.manager.common.core.domain.model.OnlineDataDay;

import java.util.List;
import java.util.Map;

/**
 * 实时在线数据
 * @author sieGuang 2021/10/21
 */
public interface OnlineDataDayService {

    /**
     * 查询
     */
    Map getOnlineUserData(OnlineDataDay onlineDataDay);

    List<OnlineDataDay> getPcuData(OnlineDataDay onlineDataDay);

    Map getOnlineUserNum(OnlineDataDay onlineDataDay);

}

package com.data.service.impl;

import com.data.mapper.OnlineDataDayMapper;
import com.data.service.OnlineDataDayService;
import com.manager.common.core.domain.model.OnlineDataDay;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实时在线数据
 * @author sieGuang 2021/10/21
 */
@Service
public class OnlineDataDayServiceImpl implements OnlineDataDayService {

    @Autowired
    private OnlineDataDayMapper onlineDataDayMapper;

    @Override
    public Map getOnlineUserData(OnlineDataDay onlineDataDay) {
        // 放回参数
        Map result = new HashMap();
        List<OnlineDataDay> list = onlineDataDayMapper.getOnlineUserData(onlineDataDay);
        Integer avgNum = onlineDataDayMapper.getAvgNum(onlineDataDay);
        result.put("data",list);
        result.put("avgNum",avgNum);
        return result;
    }

    @Override
    public List getPcuData(OnlineDataDay onlineDataDay) {
        List<OnlineDataDay> list = onlineDataDayMapper.getPcuData(onlineDataDay);
        return list;
    }

    @Override
    public Map getOnlineUserNum(OnlineDataDay onlineDataDay) {
        // 放回参数
        Map result = new HashMap();

        String todayMaxNum = "0";
        String todayAvgNum = "0";
        String yesterdayMaxNum = "0";
        String yesterdayAvgNum = "0";
        String historyMaxNum = "0";
        String historyAvgNum = "0";

        // 当天最大值 和 平均值
        Map<String,String> today = onlineDataDayMapper.getMaxAndAvg(onlineDataDay,1);
        if(today != null){
            todayMaxNum = today.get("maxNum");
            todayAvgNum = today.get("avgNum");
        }
        // 昨天最大值 和 平均值
        Map<String,String> yesterday = onlineDataDayMapper.getMaxAndAvg(onlineDataDay,2);
        if(yesterday != null){
            yesterdayMaxNum = yesterday.get("maxNum");
            yesterdayAvgNum = yesterday.get("avgNum");
        }
        // 历史最大值 和 平均值
        Map<String,String> history = onlineDataDayMapper.getMaxAndAvg(onlineDataDay,3);
        if(history != null){
            historyMaxNum = history.get("maxNum");
            historyAvgNum = history.get("avgNum");
        }

        result.put("todayMaxNum",todayMaxNum);
        result.put("todayAvgNum",todayAvgNum);
        result.put("todayMaxNum",yesterdayMaxNum);
        result.put("yesterdayAvgNum",yesterdayAvgNum);
        result.put("historyMaxNum",historyMaxNum);
        result.put("historyAvgNum",historyAvgNum);
        return result;
    }

}


package com.job.executor.handler;

import com.job.core.handler.annotation.XxlJob;
import com.job.core.util.DateUtil;
import com.job.executor.domain.OnlineDataDay;
import com.job.executor.mapper.CountSubGameMapper;
import com.job.executor.mapper.OnlineDataDayMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;


/**
 * 实时在线数据
 * @author sieGuang 2021/10/21
 */
@Component
@Slf4j
public class OnlineDataDayHandle {

    @Autowired
    private OnlineDataDayMapper onlineDataDayMapper;


    @XxlJob("online_data_day")
    //@PostConstruct
    public void onlineDataDay() {
        String day = DateUtil.formatDate(new Date());// 当天日期

        String date = DateUtil.formatDateTime(new Date()); // 当前时间 yyyy-MM-dd HH:mm:ss

        // 获取当前在线数据
        List<OnlineDataDay> onlineDataDay = onlineDataDayMapper.getOnlineDataDay();

        if(onlineDataDay != null && onlineDataDay.size()>0){
            onlineDataDay.forEach(odd -> {
                odd.setDay(day);
                odd.setCreateTime(date);
            });
            onlineDataDayMapper.insertOnlineDataDay(onlineDataDay);
        }
    }
}

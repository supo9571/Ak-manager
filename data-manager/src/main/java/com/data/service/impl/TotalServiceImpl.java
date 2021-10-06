package com.data.service.impl;

import com.data.mapper.TotalMapper;
import com.data.service.TotalService;
import com.manager.common.core.domain.model.Summarize;
import com.manager.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/10/6
 */
@Service
public class TotalServiceImpl implements TotalService {
    @Autowired
    private TotalMapper totalMapper;
    @Override
    public List getTotals(Summarize summarize) {
        return totalMapper.getTotals(summarize);
    }

    @Override
    public Map getLeft(int tid) {
        Map map = totalMapper.getLeft(tid,DateUtils.getDate());
        map.put("yesterdayBalance",totalMapper.getYesterdayBalance(tid,DateUtils.getYesterday()));
        map.put("onlinePlayer",totalMapper.getOnlinePlayer(tid));
        return map;
    }

    @Override
    public Map getRight(int tid, String beginTime, String endTime) {
        return totalMapper.getRight(tid,beginTime,endTime);
    }
}

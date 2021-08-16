package com.job.executor.service.impl;

import com.job.executor.datasource.DataSourceType;
import com.job.executor.datasource.MyDataSource;
import com.job.executor.mapper.TotalMapper;
import com.job.executor.service.TotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TotalServiceImpl implements TotalService {
    @Autowired
    TotalMapper totalMapper;

    @Override
    @MyDataSource(DataSourceType.Master)
    public int getOrderTime() {
        return totalMapper.getOrderTime();
    }

    @Override
    @MyDataSource(DataSourceType.Master)
    public void updateTotalRecharge(BigDecimal addMoney, long time) {
        totalMapper.updateTotalRecharge(addMoney,time);
    }
}

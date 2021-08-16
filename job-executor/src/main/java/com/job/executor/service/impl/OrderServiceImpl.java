package com.job.executor.service.impl;

import com.job.executor.datasource.DataSourceType;
import com.job.executor.datasource.DataSource;
import com.job.executor.mapper.OrderMapper;
import com.job.executor.mapper.TotalMapper;
import com.job.executor.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TotalMapper totalMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    @DataSource(DataSourceType.Slave1)
    public Map getNewRecharge(String orderTime) {
        return orderMapper.getNewRecharge(orderTime);
    }

    @Override
    @DataSource(DataSourceType.Slave2)
    public Map getNewWithdraw(String withdrawTime) {
        return orderMapper.getNewWithdraw(withdrawTime);
    }
}

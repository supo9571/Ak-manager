package com.job.executor.service.impl;

import com.job.executor.datasource.DataSourceType;
import com.job.executor.datasource.MyDataSource;
import com.job.executor.mapper.OrderMapper;
import com.job.executor.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Override
    @MyDataSource(DataSourceType.Slave)
    public Map getNewRecharge(long orderTime) {
        return orderMapper.getNewRecharge(orderTime);
    }
}

package com.consumer.sharding.service.impl;

import com.consumer.sharding.domain.Order;
import com.consumer.sharding.mapper.OrderMapper;
import com.consumer.sharding.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author marvin 2021/8/18
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public List getAll(String sysTime) {
//        orderMapper.insert();
        List list = orderMapper.findAll2();
        return orderMapper.findAll(sysTime);
    }
}

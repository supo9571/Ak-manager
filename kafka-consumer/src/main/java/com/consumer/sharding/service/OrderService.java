package com.consumer.sharding.service;

import com.consumer.sharding.domain.Order;

import java.util.List;

/**
 * @author marvin 2021/8/18
 */
public interface OrderService {
    List getAll(String sysTime);
}

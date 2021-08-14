package com.consumer.service.impl;

import com.consumer.mapper.DataMapper;
import com.consumer.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataServiceImpl implements DataService {
    @Autowired
    DataMapper dataMapper;
    @Override
    public void updateUserOnline(String value,String time) {
        dataMapper.updateUserOnline(value,time);
    }

    @Override
    public void insertMsg(String key, String op, String msg) {
        dataMapper.insertMsg(key,op,msg);
    }
}

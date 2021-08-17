package com.consumer.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.consumer.domain.Coins;
import com.consumer.domain.Register;
import com.consumer.mapper.InsertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class InsertHandler {

    @Autowired
    private InsertMapper insertMapper;

    public void insertRegister(JSONObject result){
        Register register = JSON.toJavaObject(result, Register.class);
        insertMapper.insertReg(register);
    }

    public void insertAddcoins(JSONObject result) {
        Coins addCoins = JSON.toJavaObject(result, Coins.class);
        insertMapper.insertAddcoins(addCoins);
    }

    public void insertReducecoins(JSONObject result) {
        Coins reduceCoins = JSON.toJavaObject(result, Coins.class);
        insertMapper.insertReduceCoins(reduceCoins);
    }
}

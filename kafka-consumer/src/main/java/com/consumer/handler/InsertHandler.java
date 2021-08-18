package com.consumer.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.consumer.domain.Coins;
import com.consumer.domain.Register;
import com.consumer.mapper.InsertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Service
public class InsertHandler {

    @Autowired
    private InsertMapper insertMapper;

    public void insertRegister(JSONObject result){
        Register register = JSON.toJavaObject(result, Register.class);
        insertMapper.insertReg(register);
    }

    @Transactional
    public void insertAddcoins(JSONObject result) {
        Coins addCoins = JSON.toJavaObject(result, Coins.class);
        insertMapper.insertAddcoins(addCoins);
        insertMapper.updateCurr(addCoins.getUid(),addCoins.getCurr());
    }

    @Transactional
    public void insertReducecoins(JSONObject result) {
        Coins reduceCoins = JSON.toJavaObject(result, Coins.class);
        insertMapper.insertReduceCoins(reduceCoins);
        insertMapper.updateCurr(reduceCoins.getUid(),reduceCoins.getCurr());
    }
}

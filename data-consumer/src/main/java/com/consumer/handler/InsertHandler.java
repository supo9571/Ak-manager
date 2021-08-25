package com.consumer.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.consumer.domain.Card;
import com.consumer.domain.Coins;
import com.consumer.domain.Login;
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
        insertMapper.updateCurrByAdd(addCoins.getUid(),addCoins.getCurr());
    }

    @Transactional
    public void insertReducecoins(JSONObject result) {
        Coins reduceCoins = JSON.toJavaObject(result, Coins.class);
        insertMapper.insertReduceCoins(reduceCoins);
        insertMapper.updateCurrByRed(reduceCoins.getUid(),reduceCoins.getCurr(),reduceCoins.getSafeBox());
    }

    @Transactional
    public void insertLogin(JSONObject result) {
        Login login = JSON.toJavaObject(result, Login.class);
        login.setIpAddress(longIp(login.getIp()));
        insertMapper.insertLogin(login);
        insertMapper.updateUser(login.getUid(),longIp(login.getIp()),login.getDeviceId(),login.getDeviceBrand(),login.getVipLevel(),login.getTime());
    }

    public void insertLogout(JSONObject result) {
        Login login = JSON.toJavaObject(result, Login.class);
        insertMapper.insertLogout(login);
    }

    public void insertCard(JSONObject result) {
        Card card = JSON.toJavaObject(result, Card.class);
        if("{}".equals(card.getLoserList()) && "{}".equals(card.getWinnerList())){
            return;
        }
        insertMapper.insertCard(card);
    }

//    @PostConstruct
//    public void test() {
//        JSONObject result = JSONObject.parseObject("{\"r\": 200002, \"ip\": 170328366, \"op\": \"logout\", \"key\": \"hallsvr_1_1628959455_12\", \"uid\": 113188, \"time\": 1628959455, \"mstime\": 1628959455765, \"channel\": \"guanwang\", \"device_id\": \"707BC84934541EF08AE008C3F225E593\", \"cur_channel\": \"guanwang\", \"device_brand\": \"mi-4c\", \"client_version\": \"1.0.0\"}");
//        Login login = JSON.toJavaObject(result, Login.class);
//        insertMapper.insertLogout(login);
//    }



    private String longIp(final long ip) {
        final long[] mask = { 0x000000FF, 0x0000FF00, 0x00FF0000, 0xFF000000 };
        final StringBuilder ipAddress = new StringBuilder();
        for (int i = 0; i < mask.length; i++) {
            ipAddress.insert(0, (ip & mask[i]) >> (i * 8));
            if (i < mask.length - 1) {
                ipAddress.insert(0, ".");
            }
        }
        return ipAddress.toString();
    }
}

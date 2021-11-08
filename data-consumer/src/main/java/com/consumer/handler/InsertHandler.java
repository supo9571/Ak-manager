package com.consumer.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.consumer.domain.*;
import com.consumer.mapper.InsertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class InsertHandler {

    @Autowired
    private InsertMapper insertMapper;

    public void insertRegister(JSONObject result) {
        Register register = JSON.toJavaObject(result, Register.class);
        //判断 是否有新注册邮件
        List list = insertMapper.getMailConfig(register.getChannel());
        if(list.size()>0){
            insertMapper.saveMailRecord(list, register.getUid());
        }
        insertMapper.insertReg(register);
    }

    @Transactional
    public void insertAddcoins(JSONObject result) {
        Coins addCoins = JSON.toJavaObject(result, Coins.class);
        insertMapper.insertAddcoins(addCoins);
        insertMapper.updateCurrByAdd(addCoins.getUid(), addCoins.getCurr());
    }

    @Transactional
    public void insertReducecoins(JSONObject result) {
        Coins reduceCoins = JSON.toJavaObject(result, Coins.class);
        insertMapper.insertReduceCoins(reduceCoins);
        insertMapper.updateCurrByRed(reduceCoins.getUid(), reduceCoins.getCurr(), reduceCoins.getSafeBox());
    }

    @Transactional
    public void insertLogin(JSONObject result) {
        Login login = JSON.toJavaObject(result, Login.class);
        login.setIpAddress(longIp(login.getIp()));
        insertMapper.insertLogin(login);
        insertMapper.updateUser(login.getUid(), longIp(login.getIp()), login.getDeviceId(), login.getDeviceBrand(), login.getVipLevel(), login.getTime(), login.getPhone());
    }

    public void insertLogout(JSONObject result) {
        Login login = JSON.toJavaObject(result, Login.class);
        insertMapper.insertLogout(login);
    }


    /**
     * 修改 流水数据
     *
     * @param result
     */
    @Transactional
    public void updateWater(JSONObject result) {
        WaterHistory waterHistory = JSON.toJavaObject(result, WaterHistory.class);
        insertMapper.insertWater(waterHistory);
        insertMapper.updateUserWater(waterHistory.getUid(), waterHistory.getValue());
    }

    //添加牌局记录
    @Transactional
    public void insertCard(JSONObject result) {
        Card card = JSON.toJavaObject(result, Card.class);
        if ("{}".equals(card.getLoserList()) && "{}".equals(card.getWinnerList())) return;
        if (card.getLoserList() == null && card.getWinnerList() == null) return;
        List<CardUser> list = new ArrayList<>();
        setCardUser(card, list);
        insertMapper.insertCard(card);
        insertMapper.insertCardUser(list);
    }

//    @PostConstruct
//    public void test() {
//        JSONObject result = JSONObject.parseObject("{\"agent_id\":0,\"account_id\":397,\"ip\":96505136,\"op\":\"register\",\"time\":1636350251,\"device_brand\":\"MuMu\",\"platform\":100,\"phone\":\"\",\"mstime\":1636350251367,\"register_ip\":\"5.192.141.48\",\"client_version\":\"1.0.0-1.0.18\",\"uid\":110396,\"name\":\"会员110396\",\"channel\":\"10001\",\"device_id\":\"D03975BDE58CA0E67FA71771E0EE4BB9\",\"key\":\"hallsvr_1_1636350251_11793\",\"cur_channel\":\"10001\"}");
//        Register register = JSON.toJavaObject(result, Register.class);
//        //判断 是否有新注册邮件
//        List list = insertMapper.getMailConfig(register.getChannel());
//        if(list.size()>0){
//            insertMapper.saveMailRecord(list, register.getUid());
//        }
//        insertMapper.insertReg(register);
//    }


    private String longIp(final long ip) {
        final long[] mask = {0x000000FF, 0x0000FF00, 0x00FF0000, 0xFF000000};
        final StringBuilder ipAddress = new StringBuilder();
        for (int i = 0; i < mask.length; i++) {
            ipAddress.insert(0, (ip & mask[i]) >> (i * 8));
            if (i < mask.length - 1) {
                ipAddress.insert(0, ".");
            }
        }
        return ipAddress.toString();
    }

    private void setCardUser(Card card, List<CardUser> list) {
        int aiNum = card.getTotalNum();
        Long addScore = 0l;
        Long payFee = 0l;
        Long betCoins = 0l;
        Long waterReward = 0l;
        String channel = "";
        StringBuilder uid = new StringBuilder();
        if (!"{}".equals(card.getLoserList())) {
            JSONArray lose = JSONArray.parseArray(card.getLoserList());
            for (Object l : lose) {
                JSONObject jsonObject = (JSONObject) l;
                CardUser cardUser = jsonObject.toJavaObject(CardUser.class);
                cardUser.setCardInfo(card);
                list.add(cardUser);
                if (!"true".equals(cardUser.getIsRobot())) {
                    uid.append(cardUser.getUid() + ",");
                    cardUser.setIsRobot("false");
                    aiNum -= 1;
                    addScore += cardUser.getAddScore();
                    payFee += cardUser.getPayFee() == null ? 0 : cardUser.getPayFee();
                    betCoins += cardUser.getWaterCoins();
                    waterReward += cardUser.getWaterReward();
                    if(cardUser.getChannel()!=null && !"0".equals(cardUser.getChannel()) && !channel.contains(cardUser.getChannel())){
                        channel = channel.concat(cardUser.getChannel()+",");
                    }
                }
            }
        }
        if (!"{}".equals(card.getWinnerList())) {
            JSONArray win = JSONArray.parseArray(card.getWinnerList());
            for (Object l : win) {
                JSONObject jsonObject = (JSONObject) l;
                CardUser cardUser = jsonObject.toJavaObject(CardUser.class);
                cardUser.setCardInfo(card);
                list.add(cardUser);
                if (!"true".equals(cardUser.getIsRobot())) {
                    uid.append(cardUser.getUid() + ",");
                    cardUser.setIsRobot("false");
                    aiNum -= 1;
                    addScore += cardUser.getAddScore();
                    payFee += cardUser.getPayFee() == null ? 0 : cardUser.getPayFee();
                    betCoins += cardUser.getWaterCoins();
                    waterReward += cardUser.getWaterReward();
                    if(cardUser.getChannel()!=null && !"0".equals(cardUser.getChannel()) && !channel.contains(cardUser.getChannel())){
                        channel = channel.concat(cardUser.getChannel()+",");
                    }
                }
            }
        }
        card.setAiNum(aiNum);
        card.setAddScore(addScore);
        card.setPayFee(payFee);
        card.setBetCoins(betCoins);
        card.setWaterReward(waterReward);
        card.setUid(uid.substring(0, uid.length() - 1));
        if(!StringUtils.isEmpty(channel)){
            card.setChannel(channel.substring(0, channel.length() - 1));
        }

    }
}

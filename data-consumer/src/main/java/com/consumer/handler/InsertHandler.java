package com.consumer.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.consumer.domain.*;
import com.consumer.mapper.InsertMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

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
        insertMapper.updateUser(login.getUid(),longIp(login.getIp()),login.getDeviceId(),login.getDeviceBrand(),login.getVipLevel(),login.getTime(),login.getPhone());
    }

    public void insertLogout(JSONObject result) {
        Login login = JSON.toJavaObject(result, Login.class);
        insertMapper.insertLogout(login);
    }

    //添加牌局记录
    @Transactional
    public void insertCard(JSONObject result) {
        Card card = JSON.toJavaObject(result, Card.class);
        if("{}".equals(card.getLoserList()) && "{}".equals(card.getWinnerList()))return;
        if(card.getLoserList()==null && card.getWinnerList()==null)return;
        List<CardUser> list = new ArrayList<>();
        setCardUser(card,list);
        insertMapper.insertCard(card);
        insertMapper.insertCardUser(list);
    }

//    @PostConstruct
//    public void test() {
//        JSONObject result = JSONObject.parseObject("{\"r\":1,\"key\":\"basesvr_1_1629531306_125\",\"safe_box\":9644497533,\"curr\":1022106168,\"is_robot\":false,\"before\":1022206168,\"uid\":113188,\"time\":1629531306,\"op\":\"reducecoins\",\"table_type\":200800,\"mstime\":1629531306274,\"value\":100000,\"game_type\":2008}");
//        Coins reduceCoins = JSON.toJavaObject(result, Coins.class);
//        insertMapper.insertReduceCoins(reduceCoins);
//        insertMapper.updateCurrByRed(reduceCoins.getUid(),reduceCoins.getCurr(),reduceCoins.getSafeBox());
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

    private void setCardUser(Card card,List<CardUser> list){
        int aiNum = 0;
        Long addScore = 0l;
        Long payFee = 0l;
        Long betCoins = 0l;
        StringBuilder uid = new StringBuilder();
        if(!"{}".equals(card.getLoserList())){
            JSONArray lose = JSONArray.parseArray(card.getLoserList());
            for (Object l : lose) {
                JSONObject jsonObject = (JSONObject) l;
                CardUser cardUser = jsonObject.toJavaObject(CardUser.class);
                cardUser.setCardInfo(card);
                list.add(cardUser);
                if("true".equals(cardUser.getIsRobot())){
                    aiNum+=1;
                }else {
                    cardUser.setIsRobot("false");
                }
                addScore+=cardUser.getAddScore();
                payFee+=cardUser.getPayFee();
                if(cardUser.getBetCoins() == null){
                    if(cardUser.getWaterCoins() == null){
                        cardUser.setBetCoins(cardUser.getAddScore());
                    }else {
                        cardUser.setBetCoins(cardUser.getWaterCoins());
                    }
                }
                betCoins+=cardUser.getBetCoins();
                uid.append(cardUser.getUid()+",");
            }
        }
        if(!"{}".equals(card.getWinnerList())){
            JSONArray win = JSONArray.parseArray(card.getWinnerList());
            for (Object l : win) {
                JSONObject jsonObject = (JSONObject) l;
                CardUser cardUser = jsonObject.toJavaObject(CardUser.class);
                cardUser.setCardInfo(card);
                list.add(cardUser);
                if("true".equals(cardUser.getIsRobot())){
                    aiNum+=1;
                }else {
                    cardUser.setIsRobot("false");
                }
                addScore+=cardUser.getAddScore();
                payFee+=cardUser.getPayFee();
                if(cardUser.getBetCoins() == null){
                    cardUser.setBetCoins(cardUser.getWaterCoins());
                }
                if(cardUser.getBetCoins() == null){
                    if(cardUser.getWaterCoins() == null){
                        cardUser.setBetCoins(cardUser.getAddScore());
                    }else {
                        cardUser.setBetCoins(cardUser.getWaterCoins());
                    }
                }
                betCoins+=cardUser.getBetCoins();
                uid.append(cardUser.getUid()+",");
            }
        }
        card.setAiNum(aiNum);
        card.setAddScore(addScore);
        card.setPayFee(payFee);
        card.setBetCoins(betCoins);
        card.setUid(uid.substring(0,uid.length()-1));

    }
}

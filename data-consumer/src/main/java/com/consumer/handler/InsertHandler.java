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

    public void insertRegister(JSONObject result) {
        Register register = JSON.toJavaObject(result, Register.class);
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
//        JSONObject result = JSONObject.parseObject("{\"exinfo\":{\"control_info1\":{\"game_times\":16},\"control_info2\":{}},\"end_time\":1633333947,\"total_num\":3,\"winner_list\":{},\"key\":\"tablesvr_1_1633333947_38\",\"mstime\":1633333947130,\"table_gid\":\"20211004155227130368_32\",\"begin_time\":1633333943,\"loser_list\":[{\"uid\":100095,\"left_score\":15947691,\"add_score\":-9000,\"pay_fee\":0,\"bet_coins\":16000,\"is_robot\":false,\"channel\":\"10001\"}],\"game_type\":7,\"time\":1633333947,\"address\":\":0000003b\",\"table_type\":701,\"op\":\"card_record\",\"side_list\":[[18,1000,0,1],[18,1000,0,1],[18,1000,0,1],[18,1000,0,1],[7,1000,0,1],[7,1000,0,1],[7,1000,0,1],[7,1000,0,1],[18,1000,0,1],[18,1000,0,1],[18,1000,0,1],[7,1000,5000,1],[1,1000,0,1],[1,1000,2000,1],[33,1000,0,1],[3,1000,0,1]],\"system_win\":9000}");
//        Card card = JSON.toJavaObject(result, Card.class);
//        if ("{}".equals(card.getLoserList()) && "{}".equals(card.getWinnerList())) return;
//        if (card.getLoserList() == null && card.getWinnerList() == null) return;
//        List<CardUser> list = new ArrayList<>();
//        setCardUser(card, list);
//        insertMapper.insertCard(card);
//        insertMapper.insertCardUser(list);
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
                    cardUser.setIsRobot("false");
                    aiNum -= 1;
                }
                addScore += cardUser.getAddScore();
                payFee += cardUser.getPayFee() == null ? 0 : cardUser.getPayFee();
                if (cardUser.getBetCoins() == null) {
                    if (cardUser.getWaterCoins() == null) {
                        cardUser.setBetCoins(cardUser.getAddScore());
                    } else {
                        cardUser.setBetCoins(cardUser.getWaterCoins());
                    }
                }
                betCoins += cardUser.getBetCoins();
                uid.append(cardUser.getUid() + ",");
                if(!channel.contains(cardUser.getChannel())){
                    channel = channel.concat(cardUser.getChannel()+",");
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
                    cardUser.setIsRobot("false");
                    aiNum -= 1;
                }
                addScore += cardUser.getAddScore();
                payFee += cardUser.getPayFee() == null ? 0 : cardUser.getPayFee();
                if (cardUser.getBetCoins() == null) {
                    if (cardUser.getWaterCoins() == null) {
                        cardUser.setBetCoins(cardUser.getAddScore());
                    } else {
                        cardUser.setBetCoins(cardUser.getWaterCoins());
                    }
                }
                betCoins += cardUser.getBetCoins();
                uid.append(cardUser.getUid() + ",");
                if(!channel.contains(cardUser.getChannel())){
                    channel = channel.concat(cardUser.getChannel()+",");
                }
            }
        }
        card.setAiNum(aiNum);
        card.setAddScore(addScore);
        card.setPayFee(payFee);
        card.setBetCoins(betCoins);
        card.setUid(uid.substring(0, uid.length() - 1));
        card.setChannel(channel.substring(0, channel.length() - 1));

    }
}

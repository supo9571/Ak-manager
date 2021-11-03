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
//        JSONObject result = JSONObject.parseObject("{\"total_num\":4,\"loser_list\":[{\"water_coins\":0,\"uid\":101891,\"left_score\":5218589,\"add_score\":-6601265,\"pay_fee\":0,\"is_robot\":true,\"is_banker\":false},{\"water_coins\":0,\"uid\":102001,\"left_score\":4726774,\"add_score\":-5026406,\"pay_fee\":0,\"is_robot\":true,\"is_banker\":false},{\"water_coins\":0,\"uid\":104099,\"left_score\":13126965,\"add_score\":-6701874,\"pay_fee\":0,\"is_robot\":true,\"is_banker\":false}],\"curr_round\":10,\"begin_time\":1633935085,\"time\":1633935092,\"table_type\":202,\"system_win\":0,\"exinfo\":{\"system_result\":0,\"control_info2\":{},\"control_uid\":0,\"control_info1\":{\"game_times\":0}},\"end_time\":1633935092,\"winner_list\":[{\"water_coins\":0,\"uid\":101119,\"left_score\":35742688,\"add_score\":17413143,\"pay_fee\":916400,\"is_robot\":true,\"is_banker\":true}],\"table_gid\":\"20211011145132_12\",\"game_type\":2,\"address\":\":00000022\",\"key\":\"tablesvr_1_1633935092_32\",\"op\":\"card_record\",\"mstime\":1633935092476,\"side_list\":[{\"cards\":[312,309,407,404,102],\"card_times\":-1,\"side\":5,\"add_score\":-6601265,\"bet_times\":20,\"card_type\":2,\"is_banker\":false,\"banker_times\":1,\"uid\":101891},{\"cards\":[212,209,208,307,305],\"card_times\":-2,\"side\":2,\"add_score\":-5026406,\"bet_times\":15,\"card_type\":9,\"is_banker\":false,\"banker_times\":1,\"uid\":102001},{\"cards\":[412,311,106,303,101],\"card_times\":3,\"side\":4,\"add_score\":17413143,\"bet_times\":0,\"card_type\":10,\"is_banker\":true,\"banker_times\":4,\"uid\":101119},{\"cards\":[413,313,409,306,301],\"card_times\":-1,\"side\":3,\"add_score\":-6701874,\"bet_times\":20,\"card_type\":6,\"is_banker\":false,\"banker_times\":2,\"uid\":104099}]}");
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
                    uid.append(cardUser.getUid() + ",");
                    cardUser.setIsRobot("false");
                    aiNum -= 1;
                    addScore += cardUser.getAddScore();
                    payFee += cardUser.getPayFee() == null ? 0 : cardUser.getPayFee();
                    betCoins += cardUser.getWaterCoins();
                    if(cardUser.getChannel()!=null && "0".equals(cardUser.getChannel()) && !channel.contains(cardUser.getChannel())){
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
                    if(cardUser.getChannel()!=null && "0".equals(cardUser.getChannel()) && !channel.contains(cardUser.getChannel())){
                        channel = channel.concat(cardUser.getChannel()+",");
                    }
                }
            }
        }
        card.setAiNum(aiNum);
        card.setAddScore(addScore);
        card.setPayFee(payFee);
        card.setBetCoins(betCoins);
        card.setUid(uid.substring(0, uid.length() - 1));
        if(!StringUtils.isEmpty(channel)){
            card.setChannel(channel.substring(0, channel.length() - 1));
        }

    }
}

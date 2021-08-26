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
    public void insertCard(JSONObject result) {
        Card card = JSON.toJavaObject(result, Card.class);
        if("{}".equals(card.getLoserList()) && "{}".equals(card.getWinnerList()))return;
        List<CardUser> list = new ArrayList<>();
        setCardUser(card,list);
        insertMapper.insertCard(card);
        insertMapper.insertCardUser(list);
    }

//    @PostConstruct
//    public void test() {
//        JSONObject result = JSONObject.parseObject("{\"total_num\":5,\"loser_list\":[{\"uid\":108922,\"left_score\":3745947,\"add_score\":-100000,\"pay_fee\":0,\"is_robot\":true,\"is_banker\":false},{\"water_coins\":50000,\"uid\":113188,\"left_score\":1514473688,\"add_score\":-50000,\"pay_fee\":0,\"is_banker\":false},{\"uid\":108973,\"left_score\":5361925,\"add_score\":-100000,\"pay_fee\":0,\"is_robot\":true,\"is_banker\":false},{\"uid\":112198,\"left_score\":4087479,\"add_score\":-350000,\"pay_fee\":0,\"is_robot\":true,\"is_banker\":false}],\"curr_round\":1,\"begin_time\":1629914166,\"time\":1629914232,\"table_type\":101,\"system_win\":50000,\"exinfo\":{\"total_score\":1900000,\"end_type\":1,\"control_uid\":0,\"round\":2,\"system_result\":0,\"control_info2\":{},\"control_info1\":{\"strategy_id\":8,\"game_times\":5,\"control_times\":1,\"strategy_result\":2},\"dizhu\":50000},\"end_time\":1629914232,\"winner_list\":[{\"card_type\":1,\"uid\":110903,\"left_score\":2730335,\"add_score\":570000,\"pay_fee\":30000,\"is_robot\":true,\"is_banker\":true}],\"side_list\":[{\"card_type\":1,\"uid\":113188,\"side\":1,\"add_score\":-50000,\"cards\":[101,110,205],\"bet_coins\":50000,\"win\":false},{\"card_type\":1,\"uid\":112198,\"side\":2,\"add_score\":-350000,\"cards\":[113,106,303],\"bet_coins\":350000,\"win\":false},{\"card_type\":1,\"uid\":108922,\"side\":3,\"add_score\":-100000,\"cards\":[406,105,102],\"bet_coins\":100000,\"win\":false},{\"card_type\":1,\"uid\":108973,\"side\":4,\"add_score\":-100000,\"cards\":[112,411,104],\"bet_coins\":100000,\"win\":false},{\"card_type\":1,\"uid\":110903,\"side\":5,\"add_score\":570000,\"cards\":[413,310,404],\"bet_coins\":1300000,\"win\":true}],\"game_type\":1,\"address\":\":000000a3\",\"key\":\"tablesvr_1_1629914232_1441\",\"op\":\"card_record\",\"mstime\":1629914232309,\"table_gid\":\"20210826015712_53\"}");
//        Card card = JSON.toJavaObject(result, Card.class);
//        if("{}".equals(card.getLoserList()) && "{}".equals(card.getWinnerList()))return;
//        List<CardUser> list = new ArrayList<>();
//        setCardUser(card,list);
//        insertMapper.insertCard(card);
//        insertMapper.insertCardUser(list);
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
                }
                addScore+=cardUser.getAddScore();
                payFee+=cardUser.getPayFee();
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
                }
                addScore+=cardUser.getAddScore();
                payFee+=cardUser.getPayFee();
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

package com.data.service;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;

/**
 * @author marvin 2021/9/11
 */
public interface MonthCardService {

    JSONObject getMonthConfig(String cid);

    JSONObject getBookConfig(String data,String cid);

    Integer getVipGive(String channelId);

    Integer saveExchange(String channel, String uid, String type, String name, String account, String originBank);

    Integer saveWithdraw(String channel, String uid, String type, BigDecimal curr, BigDecimal withdraw);
}

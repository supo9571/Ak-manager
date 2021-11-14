package com.data.service;

import com.alibaba.fastjson.JSONObject;
import com.manager.common.core.domain.model.ExchangeOrder;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

/**
 * @author marvin 2021/9/11
 */
public interface MonthCardService {

    JSONObject getMonthConfig(String cid);

    JSONObject getBookConfig(JSONObject param, String cid);

    Integer getVipGive(String channelId);


    int getAccountCount(String channelId, String uid, String type, String account);

    Integer saveExchange(String channel, String uid, String type, String name, String account, String originBank);

    Integer saveWithdraw(ExchangeOrder exchangeOrder);

    JSONObject getExchangeConfig(String uid, String channelId);

    JSONObject getBankList();

    Integer getBankGive(String channelId);
}

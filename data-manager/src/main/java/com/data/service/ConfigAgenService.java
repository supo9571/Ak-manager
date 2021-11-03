package com.data.service;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/9/11
 */
public interface ConfigAgenService {
    List<Map> getConfigAgentList(String cid);

    JSONObject bindAgent(String channelId, String uid, String agentId);

    JSONObject getSubInfo(String uid, Integer limit, Integer index);

    JSONObject getWithdrawHistory(Long uid, int limit, int page);

    JSONObject getInfo(String uid, String channelId);

    JSONObject getIncome(Long uid, int limit, int page);

    JSONObject getWithdraw(String uid, BigDecimal cash);

    List getActList(String channelId);

    JSONObject getMenu(String channelId);

    JSONObject getBeifen(String channelId);
}

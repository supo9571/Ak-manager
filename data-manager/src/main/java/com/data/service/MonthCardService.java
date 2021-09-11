package com.data.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @author marvin 2021/9/11
 */
public interface MonthCardService {

    JSONObject getMonthConfig(String cid);

    JSONObject getBookConfig(String data,String cid);
}

package com.data.service;

import com.alibaba.fastjson.JSONObject;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/9/25
 */
public interface MailService {
    List getTips(String channelId, String uid);

    List getMailList(String channelId, String uid);

    void readMail(String ids);

    JSONObject receiveMail(String id);

    void delMail(Integer mid);

    List getAdvert(String channelId);
}

package com.data.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.data.mapper.MonthCardMapper;
import com.data.mapper.TenantMapper;
import com.data.service.MonthCardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author marvin 2021/9/11
 */
@Service
public class MonthCardServiceImpl implements MonthCardService {
    @Autowired
    private MonthCardMapper monthCardMapper;

    @Autowired
    private TenantMapper tenantMapper;
    @Override
    public JSONObject getMonthConfig(String cid) {
        Integer tid = tenantMapper.getTidByCid(cid);
        JSONObject result = new JSONObject();
        Map map = monthCardMapper.getMonthConfig(tid);
        result.put("code","200");
        result.put("result",map);
        return result;
    }

    @Override
    public JSONObject getBookConfig(JSONObject param,String cid) {
        Integer tid = tenantMapper.getTidByCid(cid);
        String phoneType = param.getString("phone_type");
        if("ios".equals(phoneType)){
            phoneType = "1";
        }else if("Android".equals(phoneType)){
            phoneType = "2";
        }else {
            phoneType = "3";
        }
        Integer vip = param.getInteger("vip_level")==null?0:param.getInteger("vip_level");
        List<Map> list = monthCardMapper.selectConfigPay(tid);
        JSONArray jsonArray = new JSONArray();
        JSONObject result = new JSONObject();
        for (int i = 0; i < list.size(); i++) {
            JSONObject payInfo = new JSONObject(list.get(i));
            Integer payType = payInfo.getInteger("pay_type");
            if(payType==1){//vip充值
                List<Map> payList = monthCardMapper.selectVipconfig(payInfo.getInteger("id"),tid);
                payList.forEach(map -> map.put("btn", Arrays.asList(String.valueOf(map.get("btn")).split(","))));
                payInfo.put("pay_list",payList);
            }else if(payType==3){//银行卡充值
                List<Map> payList = monthCardMapper.selectBankconfig(payInfo.getInteger("id"),vip,tid);
                payInfo.put("pay_list",getBankInfo(payList));
            }else{//线上充值
                List<Map> payList = monthCardMapper.selectOnlineconfig(payInfo.getInteger("id"),vip,phoneType,tid);
                payList.forEach(map -> map.put("btn", Arrays.asList(String.valueOf(map.get("btn")).split(","))));
                payInfo.put("pay_list",payList);
            }
            payInfo.remove("pay_type");
            jsonArray.add(payInfo);
        }
        result.put("onRspPayList",jsonArray);
        return result;
    }

    /**
     * 获取vip充值 赠送比例
     * @param channelId
     * @return
     */
    @Override
    public Integer getVipGive(String channelId) {
        return monthCardMapper.getVipGive(tenantMapper.getTidByCid(channelId));
    }

    /**
     * 绑定 提现支付宝/银行卡
     * @return
     */
    @Override
    public Integer saveExchange(String channel, String uid, String type, String name, String account, String originBank) {
        return monthCardMapper.saveExchange(channel,tenantMapper.getTidByCid(channel),uid,type,name,account,originBank);
    }

    /**
     * 申请 提现
     * @return
     */
    @Override
    public Integer saveWithdraw(String channel, String uid, String type, BigDecimal curr, BigDecimal withdraw) {
        return monthCardMapper.saveWithdraw(channel,tenantMapper.getTidByCid(channel),uid,type,curr,withdraw);
    }

    private List<JSONObject> getBankInfo(List<Map> list){
        List<JSONObject> result = new ArrayList<>();
        list.forEach(m->{
            JSONObject jsonObject = new JSONObject(m);
            if(2==jsonObject.getInteger("jump_type")){
                jsonObject.remove("url");
                String bankValue = jsonObject.getString("bank_value");
                JSONArray jsonArray = JSONArray.parseArray(bankValue);
                int i =new Random().nextInt(100)+1;
                for (int l = 0; l < jsonArray.size(); l++) {
                    JSONObject json = jsonArray.getJSONObject(l);
                    if(Integer.valueOf(json.getInteger("bank_rate"))>=i){
                        jsonObject.put("bank_name",json.get("bank_name"));
                        jsonObject.put("bank_card_num",json.get("bank_card_num"));
                        jsonObject.put("bank_user_name",json.get("bank_user_name"));
                        break;
                    }
                    i = i-Integer.valueOf(json.getInteger("bank_rate"));
                }
            }
            jsonObject.remove("bank_value");
            jsonObject.put("btn",Arrays.asList(jsonObject.getString("btn").split(",")));
            result.add(jsonObject);
        });
        return result;
    }
}

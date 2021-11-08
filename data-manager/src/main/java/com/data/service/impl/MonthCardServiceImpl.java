package com.data.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.data.config.GlobalConfig;
import com.data.config.redis.RedisCache;
import com.data.mapper.MonthCardMapper;
import com.data.mapper.TenantMapper;
import com.data.service.MonthCardService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.ExchangeOrder;
import com.manager.common.utils.http.HttpUtils;
import com.manager.common.utils.uuid.IdUtils;
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

    @Autowired
    private RedisCache redisCache;

    @Override
    public JSONObject getMonthConfig(String cid) {
        Integer tid = tenantMapper.getTidByCid(cid);
        JSONObject result = new JSONObject();
        Map map = monthCardMapper.getMonthConfig(tid);
        result.put("code", 200);
        result.put("result", map);
        return result;
    }

    @Override
    public JSONObject getBookConfig(JSONObject param, String cid) {
        JSONObject jsonObject = new JSONObject();
        JSONObject result = new JSONObject();
        Integer tid = tenantMapper.getTidByCid(cid);
        String phoneType = param.getString("phone_type");
        if ("ios".equals(phoneType)) {
            phoneType = "1";
        } else if ("Android".equals(phoneType)) {
            phoneType = "2";
        } else {
            phoneType = "3";
        }
        Integer vip = param.getInteger("vip_level") == null ? 0 : param.getInteger("vip_level");
        List<Map> list = monthCardMapper.selectConfigPay(tid);
        for (int i = 0; i < list.size(); i++) {
            JSONObject payInfo = new JSONObject(list.get(i));
            Integer payType = payInfo.getInteger("pay_type");
            if (payType == 1) {//vip充值
                List<Map> payList = monthCardMapper.selectVipconfig(payInfo.getInteger("id"), tid);
                payList.forEach(map -> {
                    map.put("recharge_type", Arrays.asList(String.valueOf(map.get("recharge_type")).split(",")));
                    map.put("recharge_other_type", Arrays.asList(String.valueOf(map.get("recharge_other_type")).split(",")));
                });
                payInfo.put("pay_list", payList);
            } else if (payType == 3) {//银行卡充值
                List<Map> payList = monthCardMapper.selectBankconfig(payInfo.getInteger("id"), vip, tid);
                payInfo.put("pay_list", getBankInfo(payList));
            } else {//线上充值
                List<Map> payList = monthCardMapper.selectOnlineconfig(payInfo.getInteger("id"), vip, phoneType, tid);
                payList.forEach(map -> map.put("btn", Arrays.asList(String.valueOf(map.get("btn")).split(","))));
                payInfo.put("pay_list", payList);
            }
            payInfo.remove("pay_type");
            result.put(payInfo.getString("pay_channel"), payInfo);
        }
        jsonObject.put("code", 200);
        jsonObject.put("msg", "ok");
        jsonObject.put("result", result);
        return jsonObject;
    }

    /**
     * 获取vip充值 赠送比例
     *
     * @param channelId
     * @return
     */
    @Override
    public Integer getVipGive(String channelId) {
        return monthCardMapper.getVipGive(tenantMapper.getTidByCid(channelId));
    }

    @Override
    public int getAccountCount(String type, String account) {
        return monthCardMapper.getAccountCount(type, account);
    }

    @Override
    public Integer getBankGive(String channelId) {
        return monthCardMapper.getBankGive(tenantMapper.getTidByCid(channelId));
    }



    /**
     * 绑定 提现支付宝/银行卡
     *
     * @return
     */
    @Override
    public Integer saveExchange(String channel, String uid, String type, String name, String account, String originBank) {
        return monthCardMapper.saveExchange(channel, tenantMapper.getTidByCid(channel), uid, type, name, account, originBank);
    }

    @Autowired
    private GlobalConfig globalConfig;
    /**
     * 申请 提现
     *
     * @return
     */
    @Override
    public Integer saveWithdraw(ExchangeOrder exchangeOrder) {
        Integer tid = tenantMapper.getTidByCid(exchangeOrder.getChannel());
        exchangeOrder.setTid(tid);
        if ("alipay".equals(exchangeOrder.getWithdrawType())) {
            exchangeOrder.setWithdrawType("2");
        } else {
            exchangeOrder.setWithdrawType("1");
        }
        //请求游戏服 减钱
        //提现配置
        List<Map> moneyVal = monthCardMapper.getExchangeConfig(tid);
        BigDecimal i = (BigDecimal) moneyVal.get(0).get("keep_money");
        //操作 用户金币
        String result = HttpUtils.sendGet(globalConfig.getReportDomain() + "/exchange","uid="+exchangeOrder.getUid()+"&coins="
                +exchangeOrder.getWithdrawMoney().multiply(new BigDecimal(10000)).longValue()+"&keep_money="+i.longValue());
        JSONObject resultJson = JSONObject.parseObject(result);
        if (resultJson != null && resultJson.getInteger("code") == 0) {
            //查询是否 符合黑名单
            Map blackMap = monthCardMapper.checkBlack(exchangeOrder);
            if(blackMap!=null){//符合黑名单策略
                monthCardMapper.saveBlackInfo(tid,exchangeOrder.getUid(),blackMap.get("blackType"),blackMap.get("blackNum"));
            }
            Map map = monthCardMapper.findUserByid(exchangeOrder.getUid());
            BigDecimal b = new BigDecimal(10000);
            exchangeOrder.setAccumulateWater(new BigDecimal((Long) map.get("totalWater")).divide(b));//累计流水
            exchangeOrder.setAccumulateRecharge(new BigDecimal((Long) map.get("totalAdd")).divide(b));//累计充值
            exchangeOrder.setRechargeExcoins(new BigDecimal((Long) map.get("totalGive")).divide(b));//累计赠送
            exchangeOrder.setRegisterIp((String) map.get("registerIp"));//注册ip
            exchangeOrder.setUname((String) map.get("name"));//注册ip
            exchangeOrder.setExaaStatus("1");//提现状态
            exchangeOrder.setWithdrawOrderNumber(IdUtils.getExchangeOrderId());//提现订单号

            //计算手续费
            String type = "1";
            if ("2".equals(exchangeOrder.getWithdrawType())) type = "0";
            Integer poundage = monthCardMapper.getPoundage(type, tid);
            exchangeOrder.setPoundage(exchangeOrder.getWithdrawMoney().multiply(new BigDecimal(poundage).divide(new BigDecimal(100))));
            exchangeOrder.setTransferAmount(exchangeOrder.getWithdrawMoney().subtract(exchangeOrder.getPoundage()));

            exchangeOrder.setWithdrawNumber(monthCardMapper.getWithdrawNumber(exchangeOrder.getUid()));//提现次数
            return monthCardMapper.saveWithdraw(exchangeOrder);
        }

        return 0;
    }

    /**
     * 获取提现配置
     *
     * @param uid
     * @param channelId
     * @return
     */
    @Override
    public JSONObject getExchangeConfig(String uid, String channelId) {
        Integer tid = tenantMapper.getTidByCid(channelId);
        JSONObject jsonObject = new JSONObject();
        JSONObject result = new JSONObject();
        jsonObject.put("code", 200);
        //查询玩家 绑定提现信息
        List<Map> bindInfo = monthCardMapper.getUserBind(uid, tid);
        result.put("bind_info", bindInfo);
        //提现配置
        List<Map> moneyVal = monthCardMapper.getExchangeConfig(tid);
        result.put("money_val", moneyVal);
        //查询玩家 充值 流水
//        List<Map> waterInfo = monthCardMapper.getUserWater(uid);
//        result.put("water_info",waterInfo);

        jsonObject.put("result", result);
        return jsonObject;
    }

    @Override
    public JSONObject getBankList() {
        JSONObject result = new JSONObject();
        result.put("code", 200);
        result.put("msg", "ok");
        List list = redisCache.getCacheObject("Bank_List");
        if(list==null || list.size()<0 ){
            list = monthCardMapper.getBankList();
            redisCache.setCacheObject("Bank_List",list);
        }
        result.put("result", monthCardMapper.getBankList());
        return result;
    }

    private List<JSONObject> getBankInfo(List<Map> list) {
        List<JSONObject> result = new ArrayList<>();
        list.forEach(m -> {
            JSONObject jsonObject = new JSONObject(m);
            if (2 == jsonObject.getInteger("jump_type")) {
                jsonObject.remove("url");
                String bankValue = jsonObject.getString("bank_value");
                JSONArray jsonArray = JSONArray.parseArray(bankValue);
                int i = new Random().nextInt(100) + 1;
                for (int l = 0; l < jsonArray.size(); l++) {
                    JSONObject json = jsonArray.getJSONObject(l);
                    if (Integer.valueOf(json.getInteger("bank_rate")) >= i) {
                        jsonObject.put("bank_name", json.get("bank_name"));
                        jsonObject.put("bank_card_num", json.get("bank_card_num"));
                        jsonObject.put("bank_user_name", json.get("bank_user_name"));
                        break;
                    }
                    i = i - Integer.valueOf(json.getInteger("bank_rate"));
                }
            }
            jsonObject.remove("bank_value");
            jsonObject.put("btn", Arrays.asList(jsonObject.getString("btn").split(",")));
            result.add(jsonObject);
        });
        return result;
    }
}

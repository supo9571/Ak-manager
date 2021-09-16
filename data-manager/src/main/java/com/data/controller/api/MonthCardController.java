package com.data.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.data.config.GlobalConfig;
import com.data.controller.BaseController;
import com.data.service.MonthCardService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.utils.http.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @author marvin 2021/9/11
 * 月卡 接口
 */
@RestController
@RequestMapping("/api/v1")
@Slf4j
public class MonthCardController extends BaseController {

    @Autowired
    private MonthCardService monthCardService;

    @Autowired
    private GlobalConfig globalConfig;
    /**
     * 月卡接口
     */
    @PostMapping("/pay/get_month_mark")
    public JSONObject month(){
        String channelId = getHeader("Client-ChannelId");//渠道id
        return monthCardService.getMonthConfig(channelId);
    }

    /**
     * 充值 配置接口
     */
    @PostMapping("/pay/get_book_mark")
    public JSONObject book(@RequestBody JSONObject param){
        String channelId = getHeader("Client-ChannelId");//渠道id
        return monthCardService.getBookConfig(param,channelId);
    }

    /**
     * 提现 配置接口
     */
    @PostMapping("/onebyone/user_info")
    public JSONObject exchange(){
        String channelId = getHeader("Client-ChannelId");//渠道id
        String uid = getHeader("uid");//玩家id
        return monthCardService.getExchangeConfig(uid,channelId);
    }

    /**
     * 提现银行卡列表
     */
    @PostMapping("/onebyone/get_bank_list")
    public JSONObject bankList(){
        return monthCardService.getBankList();
    }


    /**
     * vip充值 赠送比例
     *
     */
    @PostMapping("/pay/get_recharge_give")
    public JSONObject rechargeGive(){
        String channelId = getHeader("Client-ChannelId");//渠道id
        JSONObject jsonObject = new JSONObject();
        Integer give = monthCardService.getVipGive(channelId);
        Map map = new HashMap();
        map.put("vip",give);
        jsonObject.put("result",map);
        return jsonObject;
    }

    /**
     * 绑定银行卡/支付宝
     * type	是	string	支付宝：”alipay” 银行卡：”bank”
     * name	是	string	支付宝/银行卡姓名
     * account	是	string	支付宝/银行卡号码
     * originBank	否	string	银行编码（绑银行卡必填）
     */
    @PostMapping("/onebyone/bingding")
    public JSONObject bingding(@RequestBody JSONObject param){
        String type = param.getString("type");
        String name = param.getString("name");
        String account = param.getString("account");
        String originBank = param.getString("originBank");
        if("alipay".equals(type)){
            type = "0";
        }else{
            type = "1";
        }
        String channel = getHeader("Client-ChannelId");//渠道id
        String uid = getHeader("uid"); // uid
        Integer i = monthCardService.saveExchange(channel,uid,type,name,account,originBank);
        JSONObject jsonObject = new JSONObject();
        if(i>0){
            jsonObject.put("code","200");
            jsonObject.put("msg","绑定成功");
        }else {
            jsonObject.put("code","500");
        }
        return jsonObject;
    }

    /**
     * 提现
     * type	是	string	银行卡/支付宝： bank/alipay
     * currentAmount	是	int	当前携带余额 1元 = 10000
     * withdrawAmount	是	int	提现金额 1元 = 10000
     */
    @PostMapping("/onebyone/withdraw")
    public JSONObject withdraw(String type,Long currentAmount,Long withdrawAmount){
        JSONObject jsonObject = new JSONObject();
        String channel = getHeader("Client-ChannelId");//渠道id
        String uid = getHeader("uid"); // uid
        //扣除金币
        JSONObject param = new JSONObject();
        param.put("cmd","reducecoins");
        param.put("reason",100040);
        param.put("type",1);
        param.put("value",withdrawAmount);
        param.put("uid",uid);
        //操作 用户金币
        String result = HttpUtils.sendPost(globalConfig.getReportDomain() + globalConfig.getChangeCoins(),
                "data="+param.toJSONString());
        JSONObject resultJson = JSONObject.parseObject(result);
        if(resultJson!=null && resultJson.getInteger("code")==0){
            //添加 提现记录
            Integer i = monthCardService.saveWithdraw(channel,uid,type,new BigDecimal(currentAmount).divide(new BigDecimal(10000))
                    ,new BigDecimal(withdrawAmount).divide(new BigDecimal(10000)));
            if(i>0){
                jsonObject.put("code","200");
                jsonObject.put("msg","成功");
            }else {
                jsonObject.put("code","500");
                jsonObject.put("code","扣钱成功，添加提现记录失败");
                log.error("提现申请，扣钱成功，添加提现记录失败，参数：{}",param);
            }
        }else{
            jsonObject.put("code","500");
            jsonObject.put("code","请求游戏服扣钱失败");
        }
        return jsonObject;
    }
}

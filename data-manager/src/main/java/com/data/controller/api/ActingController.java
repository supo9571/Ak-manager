package com.data.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.data.controller.BaseController;
import com.data.service.ConfigAgenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/9/11
 * 推广链接 接口
 */
@RestController
@RequestMapping("/api/v1")
public class ActingController extends BaseController {

    @Autowired
    private ConfigAgenService configAgenService;

    /**
     * 返佣比例
     */
    @PostMapping("/agentv2/rebate_form")
    public JSONObject rebate_form(){
        JSONObject result = new JSONObject();
        String channelId = getHeader("Client-ChannelId");//渠道id
        List<Map> list = configAgenService.getConfigAgentList(channelId);
        result.put("code",0);
        result.put("reCommissionRuleList",list);
        return result;
    }

    /**
     * 我的业绩
     */
    @PostMapping("/agentv2/info")
    public JSONObject info(@RequestBody JSONObject param){
        String channelId = getHeader("Client-ChannelId");//渠道id
        String uid = param.getString("uid");//渠道id
        return configAgenService.getInfo(uid,channelId);
    }

    /**
     * 我的业绩 下级列表
     */
    @PostMapping("/agentv2/subinfo")
    public JSONObject subinfo(@RequestBody JSONObject param){
        String uid = getHeader("uid");//玩家id
        Integer limit = param.getInteger("limit");//条数
        Integer index = param.getInteger("index");//当前页数
        JSONObject result = new JSONObject();
        return configAgenService.getSubInfo(uid,limit,index);
    }

    /**
     * 绑定代理
     */
    @PostMapping("/agentv2/bind")
    public JSONObject bind(@RequestBody JSONObject param){
        String channelId = getHeader("Client-ChannelId");//渠道id
        String uid = param.getString("uid");//玩家id
        String agentId = param.getString("agent_id");//代理id
        return configAgenService.bindAgent(channelId,uid,agentId);
    }

    /**
     * 领取记录
     */
    @PostMapping("/agentv2/withdrawhistory")
    public JSONObject withdrawhistory(@RequestBody JSONObject param){
        Long uid = param.getLong("uid");//玩家id
        int limit = param.getInteger("limit");//玩家id
        int page = param.getInteger("page");//玩家id
        return configAgenService.getWithdrawHistory(uid,limit,page);
    }

    /**
     * 每日业绩
     */
    @PostMapping("/agentv2/income")
    public JSONObject income(@RequestBody JSONObject param){
        Long uid = param.getLong("uid");//玩家id
        int limit = param.getInteger("limit");//玩家id
        int page = param.getInteger("page");//玩家id
        return configAgenService.getIncome(uid,limit,page);
    }

    /**
     * 领取佣金
     */
    @PostMapping("/agentv2/withdraw")
    public JSONObject withdraw(@RequestBody JSONObject param){
        String uid = getHeader("uid");//玩家id
        int cash = param.getInteger("cash");//领取金额
        return configAgenService.getWithdraw(uid,cash);
    }
}

package com.data.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.data.config.redis.RedisCache;
import com.data.controller.BaseController;
import com.data.service.ConfigAgenService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
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

    @Autowired
    private RedisCache redisCache;

    /**
     * 返佣比例
     */
    @PostMapping("/agentv2/rebate_form")
    public JSONObject rebate_form() {
        String resultStr = redisCache.getCacheObject("rebate_form");
        if (StringUtils.isBlank(resultStr)) {
            JSONObject result = new JSONObject();
            String channelId = getHeader("Client-ChannelId");//渠道id
            List<Map> list = configAgenService.getConfigAgentList(channelId);
            result.put("code", 200);
            result.put("reCommissionRuleList", list);
            redisCache.setCacheObject("rebate_form", result.toJSONString());
            return result;
        }
        JSONObject result = JSONObject.parseObject(resultStr);
        return result;
    }

    /**
     * 我的业绩
     */
    @PostMapping("/agentv2/info")
    public JSONObject info(@RequestBody JSONObject param) {
        String channelId = getHeader("Client-ChannelId");//渠道id
        String uid = param.getString("uid");//渠道id
        return configAgenService.getInfo(uid, channelId);
    }

    /**
     * 我的业绩 下级列表
     */
    @PostMapping("/agentv2/subinfo")
    public JSONObject subinfo(@RequestBody JSONObject param) {
        String uid = getHeader("uid");//玩家id
        Integer limit = param.getInteger("limit");//条数
        Integer index = param.getInteger("index");//当前页数
        return configAgenService.getSubInfo(uid, limit, index);
    }

    /**
     * 绑定代理
     */
    @PostMapping("/agentv2/bind")
    public JSONObject bind(@RequestBody JSONObject param) {
        String channelId = getHeader("Client-ChannelId");//渠道id
        String uid = param.getString("uid");//玩家id
        String agentId = param.getString("agent_id");//代理id
        return configAgenService.bindAgent(channelId, uid, agentId);
    }

    /**
     * 获取全民推广信息
     */
    @PostMapping("/pay/get_bind_recharge")
    public JSONObject getRecharge(@RequestBody JSONObject param) {
        String channelId = getHeader("Client-ChannelId");//渠道id
        String uid = param.getString("uid");//玩家id
        String start = param.getString("start");
        String end_time = param.getString("end_time");
        String line = param.getString("line");
        return null;
    }

    /**
     * 领取记录
     */
    @PostMapping("/agentv2/withdrawhistory")
    public JSONObject withdrawhistory(@RequestBody JSONObject param) {
        Long uid = param.getLong("uid");//玩家id
        int limit = param.getInteger("limit");//玩家id
        int page = param.getInteger("page");//玩家id
        return configAgenService.getWithdrawHistory(uid, limit, page);
    }

    /**
     * 每日业绩
     */
    @PostMapping("/agentv2/income")
    public JSONObject income(@RequestBody JSONObject param) {
        Long uid = param.getLong("uid");//玩家id
        int limit = param.getInteger("limit");//玩家id
        int page = param.getInteger("page");//玩家id
        return configAgenService.getIncome(uid, limit, page);
    }

    /**
     * 领取佣金
     */
    @PostMapping("/agentv2/withdraw")
    public JSONObject withdraw(@RequestBody JSONObject param){
        String uid = getHeader("uid");//玩家id
        BigDecimal cash = param.getBigDecimal("cash");//领取金额
        return configAgenService.getWithdraw(uid, cash.divide(new BigDecimal(10000)));
    }

    /**
     * 活动列表配置
     */
    @PostMapping("/act/get_act_list")
    public JSONObject getActList(){
        String channelId = getHeader("Client-ChannelId");//渠道id
        String uid = getHeader("uid");
        List list = configAgenService.getActList(channelId,uid);
        JSONObject result = new JSONObject();
        result.put("code", 200);
        result.put("result", list);
        return result;
    }

    /**
     * 大厅活动按钮配置
     */
    @PostMapping("/onebyone/get_menu")
    public JSONObject getMenu(@RequestBody JSONObject param){
        String channelId = param.getString("user_channle");//渠道id
        return configAgenService.getMenu(channelId);
    }

    /**
     * 官网配置
     */
    @PostMapping("/beifen")
    public JSONObject getBeifen(){
        String channelId = getHeader("Client-ChannelId");//渠道id
        return configAgenService.getBeifen(channelId);
    }
}

package com.data.controller.api;

import com.alibaba.fastjson.JSONObject;
import com.data.controller.BaseController;
import com.data.service.ConfigAgenService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author sieGuang 2021/9/9
 * 推广链接 接口
 */
@RestController
@RequestMapping("/api/v1/agentv2")
public class ActingController extends BaseController {

    @Autowired
    private ConfigAgenService configAgenService;

    /**
     * 返佣金额表
     */
    @PostMapping("/rebate_form")
    public JSONObject rebate_form(){
        String channelId = getHeader("Client-ChannelId");//渠道id
        JSONObject result = new JSONObject();
        String uid = getHeader("uid"); // uid
        String promotionDomain = "";

        List<Map> list = configAgenService.getConfigAgentList(channelId);
        if(CollectionUtils.isNotEmpty(list)){
            promotionDomain = (String)list.get(0).get("promotionDomain");
        }
        result.put("reCommissionRuleList",list);
        result.put("spread_url",promotionDomain);
        result.put("uid",uid);
        return result;
    }

}

package com.data.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.data.mapper.ConfigAgentMapper;
import com.data.mapper.TenantMapper;
import com.data.service.ConfigAgenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/9/11
 */
@Service
public class ConfigAgentServiceImpl implements ConfigAgenService {

    @Autowired
    private ConfigAgentMapper configAgentMapper;

    @Autowired
    private TenantMapper tenantMapper;

    @Override
    public List<Map> getConfigAgentList(String cid) {
        Integer tid = tenantMapper.getTidByCid(cid);
        return configAgentMapper.getConfigAgentList(tid);
    }

    /**
     * 绑定 代理
     * @param channelId
     * @param uid
     * @param agentId
     * @return
     */
    @Override
    public JSONObject bindAgent(String channelId, String uid, String agentId) {
        JSONObject result = new JSONObject();
        Integer tid = tenantMapper.getTidByCid(channelId);
        Long time = configAgentMapper.selectAgent(tid,agentId);
        result.put("code",200);
        if(time==null){
            Map map = new HashMap();
            map.put("status",false);
            map.put("msg","请输入正确的推荐人ID!!!");
            return result;
        }
        Integer i = configAgentMapper.setAgentId(agentId,uid,time);
        if(i>0){
            Map map = new HashMap();
            map.put("status",true);
            map.put("msg","绑定成功!!!");
            return result;
        }else{
            Map map = new HashMap();
            map.put("status",false);
            map.put("msg","推荐人注册时间必须早于自己!!!");
            return result;
        }
    }
}

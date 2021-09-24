package com.data.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.data.config.GlobalConfig;
import com.data.mapper.ConfigAgentMapper;
import com.data.mapper.TenantMapper;
import com.data.service.ConfigAgenService;
import com.manager.common.utils.DateUtils;
import com.manager.common.utils.http.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
     *
     * @param channelId
     * @param uid
     * @param agentId
     * @return
     */
    @Override
    public JSONObject bindAgent(String channelId, String uid, String agentId) {
        JSONObject result = new JSONObject();
        Integer tid = tenantMapper.getTidByCid(channelId);
        Long time = configAgentMapper.selectAgent(tid, agentId);
        result.put("code", 200);
        Map map = new HashMap();
        if (time == null) {
            map.put("status", false);
            map.put("msg", "请输入正确的推荐人ID!!!");
        } else {
            Integer i = configAgentMapper.setAgentId(agentId, uid, time, System.currentTimeMillis());
            if (i > 0) {
                map.put("status", true);
                map.put("msg", "绑定成功!!!");
            } else {
                map.put("status", false);
                map.put("msg", "推荐人注册时间必须早于自己!!!");
            }
        }
        result.put("result", map);
        return result;
    }

    /**
     * 查询 直属下级列表信息 分页
     */
    @Override
    public JSONObject getSubInfo(String uid, Integer limit, Integer index) {
        JSONObject result = new JSONObject();
        List<Map> data = configAgentMapper.selectSubinfo((index - 1) * limit, limit, uid, DateUtils.getDate());
        Integer total = configAgentMapper.selectSubinfoCount(uid, DateUtils.getDate());
        result.put("code", 200);
        Map map = new HashMap();
        map.put("cur_page", index);
        map.put("total", total);
        Integer allPage = 0;
        if (total % limit == 0) {
            allPage = total / limit;
        } else {
            allPage = total / limit + 1;
        }
        map.put("all_page", allPage);
        map.put("data", data);
        result.put("result", map);
        return result;
    }

    /**
     * 查询 领取记录
     *
     * @param uid
     * @param limit 每页条数
     * @param page  当前页数
     * @return
     */
    @Override
    public JSONObject getWithdrawHistory(Long uid, int limit, int page) {
        JSONObject result = new JSONObject();
        Map map = new HashMap();
        List<Map> data = configAgentMapper.getWithdrawHistory((page - 1) * limit, limit, uid);
        Integer limitCount = configAgentMapper.getWithdrawHistoryCount(uid);
        Integer pageCount = 0;
        if (limitCount % limit == 0) {
            pageCount = limitCount / limit;
        } else {
            pageCount = limitCount / limit + 1;
        }
        map.put("page_count", pageCount);
        map.put("limit_count", limitCount);
        map.put("page", page);
        map.put("limit", limit);
        map.put("data", data);
        result.put("code", 200);
        result.put("result", map);
        return result;
    }

    @Override
    public JSONObject getInfo(String uid, String channelId) {
        JSONObject result = new JSONObject();
        result.put("code", 200);
        Map map = configAgentMapper.getInfo(uid, DateUtils.getDate());
        String spreadUrl = configAgentMapper.getSpreatUrl(tenantMapper.getTidByCid(channelId));
        map.put("spread_url", spreadUrl.concat("?ch=" + channelId + "&uid" + uid));
        result.put("result", map);
        return result;
    }

    @Override
    public JSONObject getIncome(Long uid, int limit, int page) {
        JSONObject result = new JSONObject();
        Map map = new HashMap();
        List<Map> data = configAgentMapper.getIncome((page - 1) * limit, limit, uid);
        Integer total = configAgentMapper.getIncomeCount(uid);
        Integer totalPage = 0;
        if (total % limit == 0) {
            totalPage = total / limit;
        } else {
            totalPage = total / limit + 1;
        }
        map.put("page_count", totalPage);
        map.put("total", total);
        map.put("page", page);
        map.put("limit", limit);
        map.put("data", data);
        result.put("code", 200);
        result.put("result", map);
        return result;
    }

    @Autowired
    private GlobalConfig globalConfig;

    /**
     * 100004 分享奖励
     */
    @Override
    @Transactional
    public JSONObject getWithdraw(String uid, BigDecimal cash) {
        JSONObject result = new JSONObject();
        Map map = new HashMap();
        //查询 可提余额
        BigDecimal decimal = configAgentMapper.getWaitIncom(uid);
        if (decimal.compareTo(cash) >= 0) {
            JSONObject param = new JSONObject();
            param.put("cmd", "addcoins");
            param.put("reason", 100004);
            param.put("type", 1);
            param.put("value", cash.multiply(new BigDecimal(10000)));
            param.put("uid", uid);
            //操作 用户金币
            String resultStr = HttpUtils.sendPost(globalConfig.getReportDomain() + globalConfig.getChangeCoins(),
                    "data=" + param.toJSONString());
            JSONObject resultJson = JSONObject.parseObject(resultStr);
            if (resultJson != null && resultJson.getInteger("code") == 0) {
                //记录 领取记录
                configAgentMapper.saveWithdarw(uid,cash);
                configAgentMapper.updateWaitIncome(uid,cash);
                map.put("rebate", cash);
                map.put("commission_pre_all", decimal.subtract(cash));
                result.put("code", 200);
                result.put("result", map);
                return result;
            }
        }
        result.put("code", 500);
        result.put("msg", "领取失败");

        return result;
    }

}

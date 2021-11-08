package com.data.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.data.config.GlobalConfig;
import com.data.mapper.MailMapper;
import com.data.mapper.TenantMapper;
import com.data.service.MailService;
import com.manager.common.utils.http.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/9/25
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private MailMapper mailMapper;

    @Autowired
    private TenantMapper tenantMapper;

    @Autowired
    private GlobalConfig globalConfig;

    @Override
    public List getTips(String channelId, String uid) {
        return mailMapper.getTips(channelId, uid, tenantMapper.getTidByCid(channelId));
    }

    @Override
    public List getMailList(String channelId, String uid) {
        Integer tid = tenantMapper.getTidByCid(channelId);
        saveMailRecord(channelId, uid, tid);
        return mailMapper.getMailList(uid, tid);
    }

    @Override
    public void readMail(String ids) {
        mailMapper.readMail(ids);
    }

    @Override
    @Transactional
    public JSONObject receiveMail(String id) {
        JSONObject result = new JSONObject();
        Map map = mailMapper.receiveMail(id);
        if (map != null) {
            BigDecimal coins = new BigDecimal((String) map.get("coins"));
            String uid = (String) map.get("uid");
            if (coins.compareTo(new BigDecimal(0)) >= 0) {
                JSONObject paramJson = new JSONObject();
                paramJson.put("cmd", "takeattach");
                JSONObject record_list = new JSONObject();
                record_list.put("uid", Long.valueOf(uid));
                record_list.put("reason", 100022);
                record_list.put("coins", coins.multiply(new BigDecimal(10000)).longValue());
                JSONArray jsonArray = new JSONArray();
                jsonArray.add(record_list);
                paramJson.put("record_list", jsonArray);
                //操作 用户金币
                String resultStr = HttpUtils.sendPost(globalConfig.getReportDomain() + "/mail",
                        "data=" + paramJson.toJSONString());
                JSONObject resultJson = JSONObject.parseObject(resultStr);
                if (resultJson != null && resultJson.getInteger("code") == 0) {
                    //修改状态 已领取
                    mailMapper.updateMailState(id);
                    result.put("code", 200);
                    result.put("msg", "OK");
                    result.put("read_id", id);
                    Map resultMap = new HashMap();
                    resultMap.put("coins", coins);
                    resultMap.put("reason", 0);
                    result.put("result", resultMap);
                    return result;
                }
            }
        }
        result.put("code", 500);
        result.put("msg", "领取失败");
        return result;
    }

    @Override
    public void delMail(Integer mid) {
        mailMapper.delMail(mid);
    }

    @Override
    public List getAdvert(String channelId) {
        Integer tid = tenantMapper.getTidByCid(channelId);
        return mailMapper.getAdvert(tid);
    }

    /**
     * 生成邮件记录
     */
    private void saveMailRecord(String channelId, String uid, Integer tid) {
        List list = mailMapper.getMailConfig(channelId, uid, tid);
        if (list != null && list.size() > 0) {
            mailMapper.saveMailRecord(list, uid);
        }
    }
}

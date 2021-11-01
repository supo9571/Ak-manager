package com.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.data.config.GlobalConfig;
import com.data.mapper.OnlineMaper;
import com.data.service.OnlineService;
import com.manager.common.core.domain.model.OnlinePlayer;
import com.manager.common.utils.http.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/21
 */
@Service
@Slf4j
public class OnlineServiceImpl implements OnlineService {
    @Autowired
    private OnlineMaper onlineMaper;

    @Autowired
    GlobalConfig globalConfig;
    @Override
    public List selectOnline(OnlinePlayer onlinePlayer) {
        String onlinePlay = globalConfig.getOnlinePlay();
        String result = HttpUtils.sendPost(onlinePlay, null);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if ("0".equals(jsonObject.getString("code"))) {
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("play_info_list");
                //清空 data_online
                onlineMaper.cleanOnline();
                List list = new ArrayList();
                jsonArray.forEach(j -> {
                    list.add(JSONObject.toJavaObject((JSON) j, OnlinePlayer.class));
                });
                onlineMaper.insertOnline(list);
                log.info("玩家在线人数更新--->{}", jsonArray.size());
            } catch (Exception e) {
                log.error("在线玩家人数出错：{}", e.getMessage());
            }
        } else {
            log.error(result);
        }
        return onlineMaper.selectOnline(onlinePlayer);
    }

    @Override
    public Map selectOnlineCount(OnlinePlayer onlinePlayer) {
        return onlineMaper.selectOnlineCount(onlinePlayer);
    }
}

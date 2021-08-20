package com.job.executor.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.job.core.handler.annotation.XxlJob;
import com.job.core.util.HttpUtils;
import com.job.executor.config.GlobalConfig;
import com.job.executor.domain.OnlinePlayer;
import com.job.executor.mapper.TotalMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class TotalHandle {

    @Autowired
    private GlobalConfig globaConfig;


    @Autowired
    private TotalMapper totalMapper;
    /**
     * 在线玩家人数
     */
    @XxlJob("online_play")
    @PostConstruct
    public void onlinePlay() {
        String domain = globaConfig.getDomain();
        String onlinePlay = globaConfig.getOnlinePlay();
        String result = HttpUtils.sendPost(domain + onlinePlay, null);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if ("0".equals(jsonObject.getString("code"))) {
            JSONArray jsonArray = jsonObject.getJSONArray("play_info_list");
            //清空 data_online
            totalMapper.cleanOnline();
            List list = new ArrayList();
            jsonArray.forEach(j->{
                list.add(JSONObject.toJavaObject((JSON) j, OnlinePlayer.class));
            });
            totalMapper.insertOnline(list);
            log.info("玩家在线人数更新--->{}", jsonArray.size());
        } else {
            log.error(result);
        }

    }
}

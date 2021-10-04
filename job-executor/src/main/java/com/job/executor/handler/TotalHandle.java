package com.job.executor.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.job.core.handler.annotation.XxlJob;
import com.job.core.util.DateUtil;
import com.job.core.util.HttpUtils;
import com.job.executor.config.GlobalConfig;
import com.job.executor.domain.OnlinePlayer;
import com.job.executor.mapper.TotalMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
    public void onlinePlay() {
        String domain = globaConfig.getDomain();
        String onlinePlay = globaConfig.getOnlinePlay();
        String result = HttpUtils.sendPost(domain + onlinePlay, null);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if ("0".equals(jsonObject.getString("code"))) {
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("play_info_list");
                //清空 data_online
                totalMapper.cleanOnline();
                List list = new ArrayList();
                jsonArray.forEach(j -> {
                    list.add(JSONObject.toJavaObject((JSON) j, OnlinePlayer.class));
                });
                totalMapper.insertOnline(list);
                log.info("玩家在线人数更新--->{}", jsonArray.size());
            } catch (Exception e) {
                log.error("在线玩家人数出错：{}", e.getMessage());
            }
        } else {
            log.error(result);
        }
    }

    /**
     * 今日登录统计
     */
    @XxlJob("login_count")
    public void login() {
        String date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        Long time = DateUtil.getTodayTimes();
        int num = totalMapper.selectTodayLogins(time);
        totalMapper.saveTodayLogins(date, num);
    }

    /**
     * 重置 今日数据
     * 清理 流水表
     */
    @XxlJob("reset_today")
    public void resetToday() {
        Long time = DateUtil.getTodayTimes() - (60 * 60 * 24 * 3);//三天前时间戳
        totalMapper.updateRegister();
        totalMapper.deleteWater(time);
    }

    /**
     * 计算 总览
     */
    @XxlJob("summarize_today")
    public void summarize() {
        String date = DateUtil.formatDate(new Date());//当天日期
        Long time = DateUtil.getTodayTimes();//当天零点 时间戳
        //查询 渠道列表
        List<String> channelList = totalMapper.getChannelList();
        channelList.forEach(channel->{
            //游戏盈亏
            BigDecimal systemWin = new BigDecimal(totalMapper.getSystemWin(channel,time));
        });


    }
}

package com.job.executor.handler;

import com.job.core.handler.annotation.XxlJob;
import com.job.core.util.HttpUtils;
import com.job.executor.config.GlobalConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class OnlinePlay {

    @Autowired
    private GlobalConfig globaConfig;
    /**
     * 在线玩家人数
     */
    @XxlJob("online_play")
    public void onlinePlay(){
        String domain = globaConfig.getDomain();
        String onlinePlay = globaConfig.getOnlinePlay();
        String result = HttpUtils.sendPost(domain+onlinePlay,null);
        log.info(result);
    }
}

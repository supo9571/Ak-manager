package com.job.executor.handler;

import com.job.core.handler.annotation.XxlJob;
import com.job.core.util.DateUtil;
import com.job.executor.mapper.CountSubGameMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;


/**
 * 子游戏实时数据
 * @author sieGuang 2021/10/01
 */
@Component
@Slf4j
public class CountSubGameHandle {

    @Autowired
    private CountSubGameMapper countSubGameMapper;

    /**
     * 5分钟统计一次数据
     */
    @XxlJob("init_sub_game")
//    @PostConstruct
    public void initSubGame() {
        String date = DateUtil.formatDate(new Date());//当天日期
        String beginTime = DateUtil.getTodayTimes()+"000";
        String endTime = System.currentTimeMillis() + "";
        //获取 游戏 投注 税收
        List gameCardInfo = countSubGameMapper.getGameCardInfo(beginTime,endTime);
        if(gameCardInfo.size()>0){
            countSubGameMapper.saveGameCardInfo(date,gameCardInfo);
        }
        //获取 游戏 返奖金额
        List gameWaterInfo = countSubGameMapper.getGameWaterInfo(beginTime,endTime);
        if(gameWaterInfo.size()>0){
            countSubGameMapper.saveGameWaterInfo(date,gameWaterInfo);
        }
    }
}

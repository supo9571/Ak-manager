package com.job.executor.handler;

import com.job.core.handler.annotation.XxlJob;
import com.job.core.util.DateUtil;
import com.job.executor.domain.GameResult;
import com.job.executor.mapper.ResultMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

/**
 * @author marvin 2021/10/15
 * 执行结果
 */
@Component
@Slf4j
public class ResultHandle {
    @Autowired
    private ResultMapper resultMapper;

    /**
     * 计算 每日 游戏执行结果
     */
    @XxlJob("game_result")
//    @PostConstruct
    public void gameResult() {
        String date = DateUtil.formatDate(new Date());
        String endTime = System.currentTimeMillis()+"";
        String beginTime = resultMapper.getEndTime();
        if(StringUtils.isEmpty(beginTime)){
            beginTime = DateUtil.getTodayTimes()+"000";
        }
        List<GameResult> list = resultMapper.selectGameResult(beginTime,endTime);
        String finalBeginTime = beginTime;
        list.forEach(gameResult -> {
            Long games = resultMapper.getGameCount(finalBeginTime,endTime);
            gameResult.setGames(games);
            gameResult.setDay(date);
            gameResult.setEndTime(endTime);

        });
        if (list.size()>0)
            resultMapper.saveGameResult(list);
    }

}

package com.job.executor.handler;

import com.job.core.handler.annotation.XxlJob;
import com.job.core.util.DateUtil;
import com.job.executor.domain.CountSubGame;
import com.job.executor.mapper.CountSubGameMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
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
    @PostConstruct
    public void initSubGame() {
        String initTime;// 初始化时间
        String endTime; // 后5分钟时间

        // 游戏数据集合
        List<CountSubGame> parentList = new ArrayList<>();
        // 游戏对应的房间集合
        List<CountSubGame> subList = new ArrayList<>();

        // 获取初始化时间，subGame等于空，代表还没有初始化过数据
        CountSubGame subGame = countSubGameMapper.getInitTime();

        if(subGame != null){
            initTime = subGame.getInitTime();
            endTime = subGame.getEndTime();

            // 获取parentId等于0的数据
            parentList = countSubGameMapper.selectSubGameActualData("0",initTime,endTime);

            if(parentList != null && !parentList.isEmpty()){

                // 循环父节点，通过父id找到对应的游戏房间
                for (CountSubGame countSubGame : parentList) {
                    countSubGame.setParentId("0");
                    countSubGame.setInitTime(endTime);

                    subList  = new ArrayList<>();
                    // 获取对应的游戏房间
                    subList = countSubGameMapper.selectSubGameActualData(countSubGame.getGameId(),initTime,endTime);

                    for (CountSubGame game : subList) {
                        game.setParentId(countSubGame.getGameId());
                        game.setInitTime(endTime);
                    }
                    countSubGameMapper.initSubGameActualData(subList);
                }

                countSubGameMapper.initSubGameActualData(parentList);
            }
        }else{
            // 获取当前时间 做初始化时间
            String date = DateUtil.formatDateTime(new Date());

            // 初始化第一版数据
            parentList = countSubGameMapper.selectSubGameActualData("0",null,null);
            if(parentList != null && !parentList.isEmpty()){

                for (CountSubGame countSubGame : parentList) {
                    countSubGame.setParentId("0");
                    countSubGame.setInitTime(date);

                    subList  = new ArrayList<>();
                    subList = countSubGameMapper.selectSubGameActualData(countSubGame.getGameId(),null,null);

                    for (CountSubGame game : subList) {
                        game.setParentId(countSubGame.getGameId());
                        game.setInitTime(date);
                    }
                    countSubGameMapper.initSubGameActualData(subList);
                }

                countSubGameMapper.initSubGameActualData(parentList);
            }
        }
    }

}

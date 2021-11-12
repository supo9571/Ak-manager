package com.manager.system.service.impl;

import com.manager.common.utils.DateUtils;
import com.manager.system.domain.vo.GameResult;
import com.manager.system.mapper.ResultMapper;
import com.manager.system.service.ResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/10/15
 */
@Service
@Slf4j
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultMapper resultMapper;
    @Override
    public List getGameResult(int tid, int strategyId, String day) {
        String date = DateUtils.getDate();;
        String endTime = System.currentTimeMillis()+"";
        String beginTime = resultMapper.getEndTime();
        if(StringUtils.isEmpty(beginTime)){
            beginTime = DateUtils.getTodayTimes()+"000";
        }
        List<GameResult> list = resultMapper.selectGameResult(beginTime,endTime);
        String finalBeginTime = beginTime;
        list.forEach(gameResult -> {
            Long games = resultMapper.getGameCount(finalBeginTime,endTime);
            gameResult.setGames(games);
            gameResult.setDay(date);
            gameResult.setEndTime(endTime);
        });
        if (list.size()>0) resultMapper.saveGameResult(list);
        return resultMapper.getGameResult(tid, strategyId, day);
    }

    @Override
    public List getPersonResult(int tid, int strategyId, int uid, String day) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(day);
            String year = String.format("%ty",date);
            String mon = String.format("%tm", date);
            String cardName = "data_card_" + year + mon;
            String cardUserName = "data_card_user_" + year + mon;
            return resultMapper.getPersonResult(tid, strategyId, uid, day,cardName,cardUserName);
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public Map getPersonResultCount(int tid, int strategyId, int uid, String day) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(day);
            String year = String.format("%ty",date);
            String mon = String.format("%tm", date);
            String cardName = "data_card_" + year + mon;
            String cardUserName = "data_card_user_" + year + mon;
            return resultMapper.getPersonResultCount(tid, strategyId, uid, day,cardName,cardUserName);
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @Override
    public List getPersonResultInfo(int uid, String strategyFlag, String day) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date date = simpleDateFormat.parse(day);
            String year = String.format("%ty",date);
            String mon = String.format("%tm", date);
            String cardName = "data_card_" + year + mon;
            return resultMapper.getPersonResultInfo(uid,strategyFlag,cardName);
        } catch (ParseException e) {
            log.error(e.getMessage());
        }
        return null;
    }
}

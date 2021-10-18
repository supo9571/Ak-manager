package com.manager.system.service.impl;

import com.manager.system.mapper.ResultMapper;
import com.manager.system.service.ResultService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

package com.data.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.data.config.GlobalConfig;
import com.data.mapper.PlayerMapper;
import com.data.service.PlayerService;
import com.data.utils.IpUtils;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.PlayUser;
import com.manager.common.core.domain.model.PlayWater;
import com.manager.common.core.domain.model.UserExchange;
import com.manager.common.core.domain.model.UserLock;
import com.manager.common.utils.http.HttpUtils;
import com.manager.common.utils.ip.AddressUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/20
 */
@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerMapper playerMapper;

    @Override
    public List selectPlayer(PlayUser playUser) {
        List<PlayUser> list = playerMapper.selectPlayer(playUser);
        list.forEach(playUser1 -> {
            playUser1.setOneRecharge(playerMapper.getOneRecharge(playUser1.getUid()));
            playUser1.setOneRecharge(playerMapper.getSumChannel(playUser1.getChannel()));
        });
        return list;
    }

    @Override
    public List selectPlayerCurr(Long uid) {
        return playerMapper.selectPlayerCurr(uid);
    }

    @Override
    public Integer updatePlayer(PlayUser playUser) {
        return playerMapper.updatePlayer(playUser);
    }

    @Override
    public String getPhone(Long uid) {
        return playerMapper.getPhone(uid);
    }

    @Override
    public Map getBankInfo(Long uid) {
        return playerMapper.getBankInfo(uid);
    }

    @Override
    public Map getAlipayInfo(Long uid) {
        return playerMapper.getAlipayInfo(uid);
    }

    @Override
    public Integer updateBank(UserExchange userExchange) {
        return playerMapper.updateBank(userExchange);
    }

    @Override
    public Map getRecAndexc(Long uid) {
        return playerMapper.getRecAndexc(uid);
    }

    @Override
    public List getRechargeInfo(String uid) {
        return playerMapper.getRechargeInfo(uid);
    }

    @Override
    public List getExchangeInfo(String uid) {
        return playerMapper.getExchangeInfo(uid);
    }

    @Override
    public Map userInfo(Long uid) {
        Map map = playerMapper.userInfo(uid);
        map.put("registerAddress", AddressUtils.getRealAddressByIP((String) map.get("registerIp")));
        return map;
    }

    @Override
    public List waterInfo(PlayWater playWater) {
        return playerMapper.waterInfo(playWater);
    }

    @Override
    public void updateToken(Long uid) {
        playerMapper.updateToken(uid);
    }

    @Autowired
    private GlobalConfig globalConfig;

    @Override
    @Transactional
    public void saveUserLock(UserLock userLock) {
        if(userLock.getLockType()==0){// 不清币
            JSONObject param = new JSONObject();
            param.put("cmd", "forbidden");
            param.put("reason", userLock.getLockMark());
            param.put("uid", Long.valueOf(userLock.getUid()));
            HttpUtils.sendPost(globalConfig.getReportDomain() + globalConfig.getChangeCoins(),
                    "data=" + param.toJSONString());
        }
        if(userLock.getLockType()==1){// 清币
            JSONObject param = new JSONObject();
            param.put("cmd", "forbidden");
            param.put("reason", userLock.getLockMark());
            param.put("uid", Long.valueOf(userLock.getUid()));
            String url = globalConfig.getReportDomain() + globalConfig.getChangeCoins();
            //踢人
            HttpUtils.sendPost(url,"data=" + param.toJSONString());
            //查询玩家余额
            Long curr = playerMapper.getUsercurr(userLock.getUid());
            //清币
            param.put("cmd", "reducecoins");
            param.put("reason", "300001");
            param.put("type", 1);
            param.put("value", curr);
            HttpUtils.sendPost(url,"data=" + param.toJSONString());
        }
        playerMapper.saveUserLock(userLock);
        playerMapper.saveUserLockLog(userLock);
    }

    @Override
    public List getLockLog(Long uid) {
        return playerMapper.getLockLog(uid);
    }
}

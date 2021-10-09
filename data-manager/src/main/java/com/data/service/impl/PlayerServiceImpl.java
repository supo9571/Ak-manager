package com.data.service.impl;

import com.data.mapper.PlayerMapper;
import com.data.service.PlayerService;
import com.data.utils.IpUtils;
import com.manager.common.core.domain.model.PlayUser;
import com.manager.common.core.domain.model.PlayWater;
import com.manager.common.core.domain.model.UserExchange;
import com.manager.common.core.domain.model.UserLock;
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
        return playerMapper.selectPlayer(playUser);
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

    @Override
    @Transactional
    public void saveUserLock(UserLock userLock) {
        playerMapper.saveUserLock(userLock);
        playerMapper.saveUserLockLog(userLock);
    }

    @Override
    public List getLockLog(Long uid) {
        return playerMapper.getLockLog(uid);
    }
}

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

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author marvin 2021/8/20
 */
@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerMapper playerMapper;

    @Override
    public Map selectPlayer(PlayUser playUser) {
        // 放回参数
        Map result = new HashMap();
        List<PlayUser> list = playerMapper.selectPlayer(playUser,null);
        List<PlayUser> listCount = playerMapper.selectPlayer(playUser,"count");

        // 增加 首充金额和归属总代 字段
        list.forEach(playUser1 -> {
            playUser1.setOneRecharge(playerMapper.getOneRecharge(playUser1.getUid()));
            playUser1.setSumChannel(playerMapper.getSumChannel(playUser1.getChannel()));
        });

        list = filter(playUser, list);
        listCount = filter(playUser, listCount);

        result.put("data",list);
        result.put("page",playUser.getPage());
        result.put("size",playUser.getSize());
        result.put("total",listCount.size());
        return result;
    }

    // 根据页面的筛选条件过滤数据
    private List<PlayUser> filter(PlayUser playUser, List<PlayUser> list) {
        if(list !=null && !list.isEmpty()){
            String alipay = playUser.getAlipay();
            String bankCard = playUser.getBankCard();
            // 过滤支付宝
            if(alipay != null && !"".equals(alipay)){
                List<String> alipayUid = playerMapper.getAlipayOrBankCard(alipay,0);
                list = list.stream().filter(f -> alipayUid.contains(f.getUid())).collect(Collectors.toList());
            }
            // 过滤银行卡
            if(bankCard != null && !"".equals(bankCard)){
                List<String> bankCardUid = playerMapper.getAlipayOrBankCard(bankCard,1);
                list = list.stream().filter(f -> bankCardUid.contains(f.getUid())).collect(Collectors.toList());
            }
        }
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

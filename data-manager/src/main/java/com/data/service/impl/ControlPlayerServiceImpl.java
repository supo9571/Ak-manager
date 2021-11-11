package com.data.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.data.config.GlobalConfig;
import com.data.mapper.ControlPlayerMapper;
import com.data.service.ControlPlayerService;
import com.manager.common.core.domain.model.ControlPlayer;
import com.manager.common.core.domain.model.ControlPlayerInfo;
import com.manager.common.utils.http.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/10/20
 */
@Service
public class ControlPlayerServiceImpl implements ControlPlayerService {

    @Autowired
    private ControlPlayerMapper controlPlayerMapper;

    @Autowired
    private GlobalConfig globalConfig;

    @Override
    @Transactional
    public void add(ControlPlayer controlPlayer) {
        //发送配置
        JSONObject param = new JSONObject();
        param.put("cmd", "send_risk_user");
        param.put("type", controlPlayer.getType());
        param.put("risk_power", controlPlayer.getRiskPower());
        param.put("level", Math.abs(controlPlayer.getSendLevel()));
        param.put("uid", controlPlayer.getUid());
        String result = HttpUtils.sendPost(globalConfig.getReportDomain() + "/gm",
                "data=" + param.toJSONString());
        JSONObject resultJson = JSONObject.parseObject(result);
        if (resultJson != null && resultJson.getInteger("code") == 0) {
            //添加玩家风控设置
            controlPlayerMapper.add(controlPlayer);
            //添加操作日志
            ControlPlayerInfo controlPlayerInfo = new ControlPlayerInfo();
            controlPlayerInfo.setUid(controlPlayer.getUid());
            controlPlayerInfo.setUpdateType(1);
            controlPlayerInfo.setUpdateBy(controlPlayer.getCreateBy());
            controlPlayerInfo.setTid(controlPlayer.getTid());
            controlPlayerMapper.addInfo(controlPlayerInfo);
        }
    }

    @Override
    public boolean checkUid(ControlPlayer controlPlayer) {
        Integer i = controlPlayerMapper.checkUid(controlPlayer);
        if(i<=0){
            return true;
        }
        Integer j = controlPlayerMapper.isExist(controlPlayer);
        if(j>0){
            return true;
        }
        return false;
    }

    @Override
    public List list(ControlPlayer controlPlayer) {
        List<ControlPlayer> list = controlPlayerMapper.list(controlPlayer);
        list.forEach(cp->{
            List<Map> betList = controlPlayerMapper.selectBet(cp.getUid());
            BigDecimal addScore = new BigDecimal(0);
            BigDecimal betCoins = new BigDecimal(0);
            BigDecimal reward = new BigDecimal(0);
            for (int i = 0; i < betList.size(); i++) {
                Map m = betList.get(i);
                if(m!=null){
                    addScore = addScore.add((BigDecimal) m.get("addScore"));
                    betCoins = betCoins.add((BigDecimal) m.get("betCoins"));
                    reward = reward.add((BigDecimal) m.get("reward"));
                }
            }
            cp.setAddScore(addScore);
            cp.setBetCoins(betCoins);
            cp.setReward(reward);
        });
        return list;
    }

    @Override
    public List infoList(ControlPlayerInfo controlPlayerInfo) {
        return controlPlayerMapper.infoList(controlPlayerInfo);
    }

    @Override
    public void edit(ControlPlayer controlPlayer) {
        //发送配置
        JSONObject param = new JSONObject();
        param.put("cmd", "send_risk_user");
        param.put("type", controlPlayer.getType());
        param.put("risk_power", controlPlayer.getRiskPower());
        param.put("level", Math.abs(controlPlayer.getSendLevel()));
        param.put("uid", controlPlayer.getUid());
        String result = HttpUtils.sendPost(globalConfig.getReportDomain() + "/gm",
                "data=" + param.toJSONString());
        JSONObject resultJson = JSONObject.parseObject(result);
        if (resultJson != null && resultJson.getInteger("code") == 0) {
            //查询 玩家风控设置
            ControlPlayer oldControl = controlPlayerMapper.select(controlPlayer.getUid());
            //修改 玩家风控设置
            controlPlayerMapper.edit(controlPlayer);
            //添加操作日志
            if(oldControl.getType() != controlPlayer.getType()){
                ControlPlayerInfo controlPlayerInfo = new ControlPlayerInfo();
                controlPlayerInfo.setUpdateType(2);
                controlPlayerInfo.setUpdateBefore(oldControl.getType()+"");
                controlPlayerInfo.setUpdateAfter(controlPlayer.getType()+"");

                controlPlayerInfo.setUid(controlPlayer.getUid());
                controlPlayerInfo.setUpdateBy(controlPlayer.getCreateBy());
                controlPlayerInfo.setTid(controlPlayer.getTid());
                controlPlayerMapper.addInfo(controlPlayerInfo);
            }
            if(oldControl.getRiskPower() != controlPlayer.getRiskPower()){
                ControlPlayerInfo controlPlayerInfo = new ControlPlayerInfo();
                controlPlayerInfo.setUpdateType(3);
                controlPlayerInfo.setUpdateBefore(oldControl.getRiskPower()+"");
                controlPlayerInfo.setUpdateAfter(controlPlayer.getRiskPower()+"");

                controlPlayerInfo.setUid(controlPlayer.getUid());
                controlPlayerInfo.setUpdateBy(controlPlayer.getCreateBy());
                controlPlayerInfo.setTid(controlPlayer.getTid());
                controlPlayerMapper.addInfo(controlPlayerInfo);
            }
            if(oldControl.getSendLevel() != controlPlayer.getSendLevel()){
                ControlPlayerInfo controlPlayerInfo = new ControlPlayerInfo();
                controlPlayerInfo.setUpdateType(4);
                controlPlayerInfo.setUpdateBefore(oldControl.getSendLevel()+"");
                controlPlayerInfo.setUpdateAfter(controlPlayer.getSendLevel()+"");

                controlPlayerInfo.setUid(controlPlayer.getUid());
                controlPlayerInfo.setUpdateBy(controlPlayer.getCreateBy());
                controlPlayerInfo.setTid(controlPlayer.getTid());
                controlPlayerMapper.addInfo(controlPlayerInfo);
            }
            if(!oldControl.getMark().equals(controlPlayer.getMark())){
                ControlPlayerInfo controlPlayerInfo = new ControlPlayerInfo();
                controlPlayerInfo.setUpdateType(5);
                controlPlayerInfo.setUpdateBefore(oldControl.getMark());
                controlPlayerInfo.setUpdateAfter(controlPlayer.getMark());

                controlPlayerInfo.setUid(controlPlayer.getUid());
                controlPlayerInfo.setUpdateBy(controlPlayer.getCreateBy());
                controlPlayerInfo.setTid(controlPlayer.getTid());
                controlPlayerMapper.addInfo(controlPlayerInfo);
            }
        }
    }

    @Override
    public void del(ControlPlayer controlPlayer) {
        //发送配置
        JSONObject param = new JSONObject();
        param.put("cmd", "send_risk_user");
        param.put("type", 0);
        param.put("uid", controlPlayer.getUid());
        param.put("risk_power", 0);
        param.put("level", 0);
        String result = HttpUtils.sendPost(globalConfig.getReportDomain() + "/gm",
                "data=" + param.toJSONString());
        JSONObject resultJson = JSONObject.parseObject(result);
        if (resultJson != null && resultJson.getInteger("code") == 0) {
            //添加玩家风控设置
            controlPlayerMapper.del(controlPlayer.getUid());
            //添加操作日志
            ControlPlayerInfo controlPlayerInfo = new ControlPlayerInfo();
            controlPlayerInfo.setUid(controlPlayer.getUid());
            controlPlayerInfo.setUpdateType(6);
            controlPlayerInfo.setUpdateBy(controlPlayer.getCreateBy());
            controlPlayerInfo.setTid(controlPlayer.getTid());
            controlPlayerMapper.addInfo(controlPlayerInfo);

        }

    }
}

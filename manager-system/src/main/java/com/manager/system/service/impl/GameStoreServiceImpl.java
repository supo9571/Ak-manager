package com.manager.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.manager.common.core.domain.entity.GameStore;
import com.manager.common.utils.StringUtils;
import com.manager.system.mapper.GameStoreMapper;
import com.manager.system.service.GameStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/10/13
 */
@Service
public class GameStoreServiceImpl implements GameStoreService {

    @Autowired
    private GameStoreMapper gameStoreMapper;

    @Override
    public int addGameStrategy(GameStore gameStore) {
        return gameStoreMapper.addGameStrategy(gameStore);
    }

    @Override
    public List getGameStrategys(Integer strategyTagId) {
        return gameStoreMapper.getGameStrategys(strategyTagId);
    }

    @Override
    public int editGameStrategy(GameStore gameStore) {
        return gameStoreMapper.editGameStrategy(gameStore);
    }

    @Override
    public int delGameStrategy(Integer id) {
        return gameStoreMapper.delGameStrategy(id);
    }

    @Override
    public String sendGameStrategy() {
        JSONObject result = new JSONObject();
        JSONObject param = new JSONObject();
        Map calculation = new HashMap();
        calculation.put("1","库存盈亏比");
        param.put("calculation_list",new JSONObject(calculation));

        List<Map> strategyList = gameStoreMapper.getGameStrategyList();
        Map strategyMap = new HashMap();
        strategyList.forEach(map -> {
            map.put("limit_list", JSON.parse((String) map.get("limit_list")));
            map.put("game_list", JSON.parse((String) map.get("game_list")));
            strategyMap.put(map.get("strategy_id")+"",new JSONObject(map));
        });
        param.put("strategyList",new JSONObject(strategyMap));
        result.put("strategy_game_store.json", param.toJSONString());
        return result.toJSONString();
    }
}

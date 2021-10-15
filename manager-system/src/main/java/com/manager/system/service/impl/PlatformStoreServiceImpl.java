package com.manager.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.manager.common.core.domain.entity.PlatformStore;
import com.manager.common.utils.StringUtils;
import com.manager.system.mapper.PlatformStoreMapper;
import com.manager.system.service.PlatformStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/10/14
 */
@Service
public class PlatformStoreServiceImpl implements PlatformStoreService {

    @Autowired
    private PlatformStoreMapper platformStoreMapper;

    @Override
    public List getPlatformStrategys(Integer platformId, Integer strategyGameId, Integer strategyPersonId) {
        return platformStoreMapper.getPlatformStrategys(platformId, strategyGameId, strategyPersonId);
    }

    @Override
    public int editPlatformStrategy(PlatformStore platformStore) {
        return platformStoreMapper.editPlatformStrategy(platformStore);
    }

    @Override
    public String sendPlatformStrategy() {
        JSONObject result = new JSONObject();
        JSONObject param = new JSONObject();
        List<Map> platformList = platformStoreMapper.getPlatformStrategyList();
        Map platformMap = new HashMap();
        platformList.forEach(map -> platformMap.put(map.get("platform_id")+"",new JSONObject(map)));
        param.put("plateform_list",new JSONObject(platformMap));
        String resultStr = StringUtils.jsonToLua(param);
        result.put("strategy_platform_config.lua", "return {" + resultStr + "}");
        return result.toJSONString();
    }
}

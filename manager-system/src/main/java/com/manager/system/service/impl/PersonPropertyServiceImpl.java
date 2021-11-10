package com.manager.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.manager.common.core.domain.entity.PersonProperty;
import com.manager.common.utils.StringUtils;
import com.manager.system.mapper.PersonPropertyMapper;
import com.manager.system.service.PersonPropertyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/10/13
 */
@Service
public class PersonPropertyServiceImpl implements PersonPropertyService {

    @Autowired
    private PersonPropertyMapper personPropertyMapper;

    @Override
    public List getPersonPropertys() {
        return personPropertyMapper.getPersonPropertys();
    }

    @Override
    public Integer editPersonPropertys(PersonProperty personProperty) {
        return personPropertyMapper.editPersonPropertys(personProperty);
    }

    @Override
    public String sendProperty() {
        JSONObject result = new JSONObject();
        JSONObject param = new JSONObject();
        List<Map> propertyList = personPropertyMapper.getPersonPropertyList();
        Map propertyMap = new HashMap();
        propertyList.forEach(map -> {
            propertyMap.put(map.get("property_id")+"",new JSONObject(map));
        });
        param.put("property_list",new JSONObject(propertyMap));
        result.put("strategy_person_property.json", param.toJSONString());
        return result.toJSONString();
    }
}

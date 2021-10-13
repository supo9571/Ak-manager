package com.manager.system.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.Tags;
import com.manager.common.utils.StringUtils;
import com.manager.common.utils.http.HttpUtils;
import com.manager.system.mapper.TagsMapper;
import com.manager.system.service.TagsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/10/12
 */
@Service
@Slf4j
public class TagsServiceImpl implements TagsService {

    @Autowired
    private TagsMapper tagsMapper;

    @Override
    public int addTags(Tags tags) {
        return tagsMapper.addTags(tags);
    }

    @Override
    public int editTags(Tags tags) {
        return tagsMapper.editTags(tags);
    }

    @Override
    public List getTags(int tagType) {
        return tagsMapper.getTags(tagType);
    }

    @Override
    public int deleteTags(Integer id) {
        return tagsMapper.deleteTags(id);
    }

    @Override
    public String sendTags() {
        JSONObject result = new JSONObject();
        JSONObject param = new JSONObject();
        Map show = new HashMap();
        show.put("1","游戏库存策略");
        show.put("2","个人库存策略");
        param.put("show",new JSONObject(show));

        List<Map> tagList = tagsMapper.selectTags();
        Map tagMap = new HashMap();
        tagList.forEach(map -> {
            tagMap.put(map.get("id")+"",new JSONObject(map));
        });
        param.put("tag_list",new JSONObject(tagMap));
        String resultStr = StringUtils.jsonToLua(param);
        result.put("strategy_tags_config.lua", "return {" + resultStr + "}");
        return result.toJSONString();
    }
}

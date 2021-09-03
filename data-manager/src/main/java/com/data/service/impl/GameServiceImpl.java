package com.data.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.data.mapper.GameMapper;
import com.data.service.GameService;
import com.manager.common.core.domain.entity.SysDept;
import com.manager.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/27
 */
@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameMapper gameMapper;
    @Override
    public List getGames() {
        return buildTree(gameMapper.getGames());
    }

    @Override
    public void saveIp(String ip, String createBy) {
        gameMapper.saveIp(ip,createBy);
    }

    @Override
    public List findIp(String ip, String createBy, String beginTime, String endTime) {
        return  gameMapper.findIp(ip,createBy,beginTime,endTime);
    }

    @Override
    public void delIp(Integer id) {
        gameMapper.delIp(id);
    }

    @Override
    public JSONObject getGameConfig() {
        List<Map> gameList = gameMapper.getGamesConfig();
        JSONObject result = new JSONObject();
        JSONObject game = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < gameList.size(); i++) {
            Map map = gameList.get(i);
            if(game.getString(String.valueOf(map.get("tid")))==null){
                jsonArray = new JSONArray();
                game.put(String.valueOf(map.get("tid")),jsonArray);
            }
            JSONObject gameJson = new JSONObject();
            gameJson.put("position",map.get("position"));
            gameJson.put("game_type",map.get("game_type"));
            gameJson.put("shown_type",map.get("shown_type"));
            gameJson.put("notice_type",map.get("notice_type"));
            gameJson.put("status",map.get("status"));
            jsonArray.add(gameJson);
        }
        result.put("game_list.lua",game);
        return result;
    }


    public List buildTree(List list) {
        List returnList = new ArrayList();
        List<Integer> tempList = new ArrayList();
        list.forEach(l -> tempList.add((Integer) ((Map) l).get("gameType")));

        for (Iterator iterator = list.iterator(); iterator.hasNext(); ) {
            Map map = (Map) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(map.get("parentId"))) {
                recursionFn(list, map);
                returnList.add(map);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List list, Map map) {
        // 得到子节点列表
        List<Map> childList = getChildList(list, map);
        map.put("child", childList);
        for (Map tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List getChildList(List<SysDept> list, Map t) {
        List tlist = new ArrayList<SysDept>();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Map n = (Map) it.next();
            if (StringUtils.isNotNull(n.get("parentId")) &&  n.get("parentId") ==  t.get("gameType")) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List list, Map t) {
        return getChildList(list, t).size() > 0 ? true : false;
    }
}

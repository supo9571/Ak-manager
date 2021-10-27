package com.manager.system.service.impl;

import com.manager.common.utils.SecurityUtils;
import com.manager.common.utils.StringUtils;
import com.manager.system.mapper.SysTenantMapper;
import com.manager.system.service.SysTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
public class SysTenantServiceImpl implements SysTenantService {

    @Autowired
    private SysTenantMapper sysTenantMapper;

    @Override
    public List selectTenants(String tid, String tType) {
        return sysTenantMapper.selectTenants(tid, tType);
    }

    @Override
    public List selectAllTenant() {
        return sysTenantMapper.selectAllTenant(SecurityUtils.getUserId());
    }

    @Override
    public List buildTree(List list) {
        List returnList = new ArrayList();
        List<String> tempList = new ArrayList();
        list.forEach(l -> tempList.add((String) ((Map) l).get("tid")));

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
    private List getChildList(List list, Map t) {
        List tlist = new ArrayList();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            Map n = (Map) it.next();
            if (StringUtils.isNotNull(n.get("parentId")) && n.get("parentId").equals(t.get("tid"))) {
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

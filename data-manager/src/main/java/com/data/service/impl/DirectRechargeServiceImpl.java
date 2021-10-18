package com.data.service.impl;

import com.data.mapper.DirectRechargeMapper;
import com.data.service.DirectRechargeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.manager.common.core.domain.model.DirectRecharge;
import com.manager.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 直属玩家充值报表
 * @author sieGuang 2021/10/016
 */
@Service
public class DirectRechargeServiceImpl implements DirectRechargeService {

    @Autowired
    private DirectRechargeMapper directRechargeMapper;

    @Override
    public Map getList(DirectRecharge directRecharge) {
        // 放回参数
        Map result = new HashMap();
        List<DirectRecharge> list = directRechargeMapper.getList(directRecharge);

        // 充值金额处理
        for (DirectRecharge recharge : list) {
            recharge.setRechargeAmount(directRechargeMapper.getRechargeAmount(
                    recharge.getUid(),recharge.getAgentTime(),directRecharge.getRechargeTime1(),directRecharge.getRechargeTime2()));
        }

        // 处理排序
        if(list!=null && !list.isEmpty()){
            if("asc".equals(directRecharge.getIsAsc())){
                Collections.sort(list, (a, b) -> a.getRechargeAmount().compareTo(b.getRechargeAmount()));
            }else{
                Collections.sort(list, (a, b) -> b.getRechargeAmount().compareTo(a.getRechargeAmount()));
            }
        }

        result.put("data",list);
        result.put("page",directRecharge.getPage());
        result.put("size",directRecharge.getSize());
        result.put("total",directRechargeMapper.getListCount(directRecharge));
        return result;
    }

    @Override
    public Map getSubList(DirectRecharge directRecharge) {
        // 放回参数
        Map result = new HashMap();

        DirectRecharge directRecharge2 = directRechargeMapper.getSubList(directRecharge);
        List<DirectRecharge> list2 = new ArrayList<DirectRecharge>();

        if(directRecharge2 != null){
            String uid = directRecharge2.getUid();
            String agentTime = directRecharge2.getAgentTime();
            directRecharge.setUid(uid);
            directRecharge.setAgentTime(agentTime);
            list2 = directRechargeMapper.getSubList2(directRecharge);
            for (DirectRecharge recharge : list2) {
                recharge.setUid(uid);
                recharge.setAgentId(directRecharge2.getAgentId());
                recharge.setChannel(directRecharge2.getChannel());
            }
        }
        result.put("data",list2);
        result.put("page",directRecharge.getPage());
        result.put("size",directRecharge.getSize());
        result.put("total",directRechargeMapper.getSubListCount(directRecharge));
        return result;
    }
}


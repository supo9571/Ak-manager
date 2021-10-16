package com.data.service.impl;

import com.data.mapper.DirectRechargeMapper;
import com.data.service.DirectRechargeService;
import com.manager.common.core.domain.model.DirectRecharge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 直属玩家充值报表
 * @author sieGuang 2021/10/016
 */
@Service
public class DirectRechargeServiceImpl implements DirectRechargeService {

    @Autowired
    private DirectRechargeMapper directRechargeMapper;

    @Override
    public List getList(DirectRecharge directRecharge) {
        List<DirectRecharge> list = directRechargeMapper.getList(directRecharge);
        return list;
    }

    @Override
    public List getSubList(DirectRecharge directRecharge) {
        DirectRecharge directRecharge2 = directRechargeMapper.getSubList(directRecharge);

        List<DirectRecharge> list2 = new ArrayList<DirectRecharge>();

        if(directRecharge2 != null){
            String uid = directRecharge2.getUid();
            String agentTime = directRecharge2.getAgentTime();
            list2 = directRechargeMapper.getSubList2(uid,agentTime);
            for (DirectRecharge recharge : list2) {
                recharge.setUid(uid);
                recharge.setAgentId(directRecharge2.getAgentId());
                recharge.setChannel(directRecharge2.getChannel());
            }

        }
        return list2;
    }


}


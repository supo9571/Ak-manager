package com.data.service;

import com.manager.common.core.domain.model.DirectRecharge;

import java.util.List;
import java.util.Map;

/**
 * 直属玩家充值报表
 * @author sieGuang 2021/10/016
 */
public interface DirectRechargeService {

    /**
     * 查询
     */
    Map getList(DirectRecharge directRecharge);

    /**
     * 每日明细
     */
    Map getSubList(DirectRecharge directRecharge);
}

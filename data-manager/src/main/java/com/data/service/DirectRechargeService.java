package com.data.service;

import com.manager.common.core.domain.model.DirectRecharge;

import java.util.List;

/**
 * 直属玩家充值报表
 * @author sieGuang 2021/10/016
 */
public interface DirectRechargeService {

    /**
     * 查询
     */
    List<DirectRecharge> getList(DirectRecharge directRecharge);

    /**
     * 每日明细
     */
    List<DirectRecharge> getSubList(DirectRecharge directRecharge);
}

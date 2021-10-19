package com.data.service;

import com.manager.common.core.domain.model.RechargeAndExchange;

import java.util.List;

/**
 * 充值和提现统计
 * @author sieGuang 2021/10/19
 */
public interface RechargeAndExchangeService {

    /**
     * 查询
     */
    List<RechargeAndExchange> getList(RechargeAndExchange rechargeAndExchange);
}

package com.data.service.impl;

import com.data.mapper.AddUserMapper;
import com.data.mapper.RechargeAndExchangeMapper;
import com.data.service.AddUserService;
import com.data.service.RechargeAndExchangeService;
import com.manager.common.core.domain.model.AddUser;
import com.manager.common.core.domain.model.RechargeAndExchange;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 充值和提现统计
 * @author sieGuang 2021/10/19
 */
@Service
public class RechargeAndExchangeServiceImpl implements RechargeAndExchangeService {

    @Autowired
    private RechargeAndExchangeMapper rechargeAndExchangeMapper;

    @Autowired
    private AddUserMapper addUserMapper;

    @Override
    public List getList(RechargeAndExchange rechargeAndExchange) {
        // 默认查看当前身份的总数据
        Long tid2 = addUserMapper.getTid(rechargeAndExchange.getTid2());
        rechargeAndExchange.setTid2(tid2);

        List<RechargeAndExchange> list = rechargeAndExchangeMapper.getList(rechargeAndExchange);
        return list;
    }
}


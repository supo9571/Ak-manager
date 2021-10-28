package com.data.service.impl;

import com.data.mapper.CoinsMapper;
import com.data.service.CoinsService;
import com.manager.common.core.domain.model.Coins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author marvin 2021/8/19
 */
@Service
public class CoinsServiceImpl implements CoinsService {

    @Autowired
    private CoinsMapper coinsMapper;

    @Override
    public List selectCoins(Coins coins) {
        return coinsMapper.selectCoins(coins);
    }

    @Override
    public BigDecimal selectCoinsCount(Coins coins) {
        return coinsMapper.selectCoinsCount(coins);
    }
}

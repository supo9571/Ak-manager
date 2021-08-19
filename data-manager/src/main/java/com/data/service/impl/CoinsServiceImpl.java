package com.data.service.impl;

import com.data.domain.Coins;
import com.data.mapper.CoinsMapper;
import com.data.service.CoinsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        coins.setMstime(1629359812025l);
        return coinsMapper.selectCoins(coins);
    }
}

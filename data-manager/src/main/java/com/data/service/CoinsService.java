package com.data.service;

import com.manager.common.core.domain.model.Coins;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author marvin 2021/8/19
 */
public interface CoinsService {
    List selectCoins(Coins coins);

    BigDecimal selectCoinsCount(Coins coins);
}

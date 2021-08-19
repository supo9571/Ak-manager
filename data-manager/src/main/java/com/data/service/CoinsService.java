package com.data.service;

import com.data.domain.Coins;

import java.util.List;

/**
 * @author marvin 2021/8/19
 */
public interface CoinsService {
    List selectCoins(Coins coins);
}

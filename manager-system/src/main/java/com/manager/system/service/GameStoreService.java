package com.manager.system.service;

import com.manager.common.core.domain.entity.GameStore;

import java.util.List;

/**
 * @author marvin 2021/10/13
 */
public interface GameStoreService {

    int addGameStrategy(GameStore gameStore);

    List getGameStrategys(Integer strategyTagId);

    int editGameStrategy(GameStore gameStore);

    int delGameStrategy(Integer id);

    String sendGameStrategy();
}

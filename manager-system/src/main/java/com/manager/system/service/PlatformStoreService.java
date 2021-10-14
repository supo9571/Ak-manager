package com.manager.system.service;

import com.manager.common.core.domain.entity.PlatformStore;

import java.util.List;

/**
 * @author marvin 2021/10/14
 */
public interface PlatformStoreService {

    List getPlatformStrategys(Integer platformId, Integer strategyGameId, Integer strategyPersonId);

    int editPlatformStrategy(PlatformStore platformStore);

    String sendPlatformStrategy();
}

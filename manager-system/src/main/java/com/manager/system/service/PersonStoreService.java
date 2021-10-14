package com.manager.system.service;

import com.manager.common.core.domain.entity.PersonStore;

import java.util.List;

/**
 * @author marvin 2021/10/13
 */
public interface PersonStoreService {

    int addPersonStore(PersonStore personStore);

    List getPersonStrategys(Integer strategyTagId);

    int editPersonStrategy(PersonStore personStore);

    int delPersonStrategy(Integer strategyId);

    String sendPersonStrategy();
}

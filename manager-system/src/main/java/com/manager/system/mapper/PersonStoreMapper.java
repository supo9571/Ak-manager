package com.manager.system.mapper;

import com.manager.common.core.domain.entity.PersonStore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/10/13
 */
@Mapper
public interface PersonStoreMapper {

    int addPersonStore(PersonStore personStore);

    List getPersonStrategys(@Param("strategyTagId") Integer strategyTagId);

    int editPersonStrategy(PersonStore personStore);

    int delPersonStrategy(@Param("strategyId") Integer strategyId);

    List<Map> getPersonStrategyList();
}

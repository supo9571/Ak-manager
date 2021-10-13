package com.manager.system.mapper;

import com.manager.common.core.domain.entity.GameStore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/10/13
 */
@Mapper
public interface GameStoreMapper {

    int addGameStrategy(GameStore gameStore);

    List getGameStrategys(@Param("strategyTagId") Integer strategyTagId);

    int editGameStrategy(GameStore gameStore);

    int delGameStrategy(@Param("id") Integer id);

    List getGameStrategyList();
}

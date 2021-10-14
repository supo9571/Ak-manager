package com.manager.system.mapper;

import com.manager.common.core.domain.entity.PlatformStore;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author marvin 2021/10/14
 */
@Mapper
public interface PlatformStoreMapper {

    List getPlatformStrategys(@Param("platformId") Integer platformId,@Param("strategyGameId") Integer strategyGameId,@Param("strategyPersonId") Integer strategyPersonId);

    int editPlatformStrategy(PlatformStore platformStore);

    List getPlatformStrategyList();
}

package com.data.mapper;

import com.manager.common.core.domain.model.SubGameData;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 子游戏实时数据
 * @author sieGuang 2021/09/30
 */
@Mapper
public interface SubGameDataMapper {

    /**
     * 查询
     * @param subGameActualData 过滤条件
     */
    List<SubGameData> getSubGameDataList(SubGameData subGameActualData);

    /**
     * 获取人局数
     */
    List<SubGameData> getHumBurCount(SubGameData subGameActualData);

}

package com.data.mapper;

import com.manager.common.core.domain.model.Game;
import com.manager.common.core.domain.model.SubGameActualData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 子游戏实时数据
 * @author sieGuang 2021/09/30
 */
@Mapper
public interface SubGameActualDataMapper {

    /**
     * 查询
     * @param subGameActualData 过滤条件
     */
    List<SubGameActualData> getSubGameActualDataList(SubGameActualData subGameActualData);

    /**
     * 获取人局数
     */
    List<SubGameActualData> getHumBurCount(SubGameActualData subGameActualData);

}

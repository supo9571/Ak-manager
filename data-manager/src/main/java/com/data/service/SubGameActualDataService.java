package com.data.service;

import com.manager.common.core.domain.model.Game;
import com.manager.common.core.domain.model.SubGameActualData;

import java.util.List;

/**
 * 子游戏实时数据
 * @author sieGuang 2021/09/30
 */
public interface SubGameActualDataService {

    /**
     * 查询
     * @param subGameActualData 过滤条件
     */
    List<SubGameActualData> getSubGameActualDataList(SubGameActualData subGameActualData);

}

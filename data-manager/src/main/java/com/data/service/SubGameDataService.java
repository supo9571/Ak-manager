package com.data.service;

import com.manager.common.core.domain.model.SubGameData;

import java.util.List;

/**
 * 子游戏实时数据
 * @author sieGuang 2021/09/30
 */
public interface SubGameDataService {

    /**
     * 查询
     */
    List<SubGameData> getSubGameDataList(SubGameData subGameData);

}

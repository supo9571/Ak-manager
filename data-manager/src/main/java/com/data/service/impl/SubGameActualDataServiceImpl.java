package com.data.service.impl;

import com.data.mapper.SubGameActualDataMapper;
import com.data.service.SubGameActualDataService;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.domain.model.SubGameActualData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import static java.util.stream.Collectors.groupingBy;
import java.util.List;
import java.util.Map;

/**
 * 子游戏实时数据
 * @author sieGuang 2021/09/30
 */
@Service
public class SubGameActualDataServiceImpl implements SubGameActualDataService {

    @Autowired
    private SubGameActualDataMapper subGameActualDataMapper;

    @Override
    public List<SubGameActualData> getSubGameActualDataList(SubGameActualData subGameActualData) {
        List<SubGameActualData> list = subGameActualDataMapper.getSubGameActualDataList(subGameActualData);
        return list;
    }

    @Override
    public List export(SubGameActualData subGameActualData) {
        List<SubGameActualData> list = subGameActualDataMapper.getSubGameActualDataList(subGameActualData);
        return list;
    }

}

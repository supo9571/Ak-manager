package com.data.service.impl;

import com.data.mapper.SubGameActualDataMapper;
import com.data.service.SubGameActualDataService;
import com.manager.common.core.domain.model.SubGameActualData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        // 获取humBurCount
        List<SubGameActualData> humBurCountList = subGameActualDataMapper.getHumBurCount(subGameActualData);

        for (int i = 0; i < list.size(); i++) {
            // 防止异常数据，导致null指针
            if(list.size() == humBurCountList.size()){
                list.get(i).setHumBurCount(humBurCountList.get(i).getHumBurCount());
            }
        }
        return list;
    }

}

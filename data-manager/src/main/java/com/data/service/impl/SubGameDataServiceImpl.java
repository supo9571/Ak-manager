package com.data.service.impl;

import com.data.mapper.SubGameDataMapper;
import com.data.service.SubGameDataService;
import com.manager.common.core.domain.model.SubGameData;
import com.manager.common.core.domain.model.SubGameDataExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 子游戏实时数据
 * @author sieGuang 2021/09/30
 */
@Service
public class SubGameDataServiceImpl implements SubGameDataService {

    @Autowired
    private SubGameDataMapper subGameDataMapper;

    @Override
    public List getSubGameDataList(SubGameData subGameData) {
        List<SubGameDataExcel> subGameDataList = subGameDataMapper.getSubGameDataList(subGameData);
        List<SubGameDataExcel> humBurCount = subGameDataMapper.getHumBurCount(subGameData);
        List list  = merge(subGameDataList,humBurCount);
        return list;
    }

    @Override
    public List<SubGameData> getTableDate(SubGameData subGameData) {
        List<SubGameDataExcel> subGameDataList = subGameDataMapper.getTableDate(subGameData);
        List<SubGameDataExcel> humBurCount = subGameDataMapper.getHumBurTableDate(subGameData);
        List list  = merge(subGameDataList,humBurCount);
        return list;
    }

    private List merge(List<SubGameDataExcel> l1, List<SubGameDataExcel> l2) {
        l1.forEach(subGameData1->{
            l2.forEach(subGameData2->{
                if(subGameData1.getGameId().equals(subGameData2.getGameId())){
                    subGameData1.setParCount(subGameData2.getParCount());
                    subGameData1.setHumBurCount(subGameData2.getHumBurCount());
                }
            });
        });
        return l1;
    }
}


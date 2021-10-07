package com.data.service.impl;

import com.data.mapper.SubGameDataMapper;
import com.data.service.SubGameDataService;
import com.manager.common.core.domain.model.SubGameData;
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
        List<SubGameData> subGameDataList = subGameDataMapper.getSubGameDataList(subGameData);
        List<SubGameData> humBurCount = subGameDataMapper.getHumBurCount(subGameData);
        List list  = merge(subGameDataList,humBurCount);
        return list;
    }

    private List merge(List<SubGameData> l1, List<SubGameData> l2) {
        l1.forEach(subGameData1->{
            l2.forEach(subGameData2->{
                if(subGameData1.getGameId().equals(subGameData2.getGameId())){
                    subGameData1.setParCount(subGameData2.getParCount());
                    subGameData1.setHumBurCount(subGameData2.getHumBurCount());
                }else {
                    subGameData1.setParCount(0);
                    subGameData1.setHumBurCount(0);
                }
            });
        });
        return l1;
    }
}


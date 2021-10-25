package com.data.service.impl;

import com.data.mapper.SubGameDataMapper;
import com.data.service.SubGameDataService;
import com.manager.common.core.domain.model.SubGameData;
import com.manager.common.core.domain.model.SubGameDataExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
        List<SubGameDataExcel> winCount = subGameDataMapper.getWinCount(subGameData);
        List list  = merge(subGameDataList,humBurCount,winCount);
        return list;
    }

    @Override
    public List<SubGameData> getTableDate(SubGameData subGameData) {
        List<SubGameDataExcel> subGameDataList = subGameDataMapper.getTableDate(subGameData);
        List<SubGameDataExcel> humBurCount = subGameDataMapper.getHumBurTableDate(subGameData);
        List<SubGameDataExcel> winTableDate = subGameDataMapper.getWinTableDate(subGameData);
        List list  = merge(subGameDataList,humBurCount,winTableDate);
        return list;
    }

    private List merge(List<SubGameDataExcel> l1, List<SubGameDataExcel> l2, List<SubGameDataExcel> l3) {
        l1.forEach(subGameData1->{
            l2.forEach(subGameData2->{
                if(subGameData1.getGameId().equals(subGameData2.getGameId())){
                    subGameData1.setParCount(subGameData2.getParCount());
                    subGameData1.setHumBurCount(subGameData2.getHumBurCount());
                }
            });
        });

        l1.forEach(subGameData1->{
            l3.forEach(subGameData3->{
                if(subGameData1.getGameId().equals(subGameData3.getGameId())){
                    subGameData1.setWinCount(subGameData3.getWinCount());

                    // 赢钱人数/参与人数;
                    Double d = Integer.valueOf(subGameData3.getWinCount()).doubleValue()
                            / Integer.valueOf(subGameData1.getParCount()).doubleValue();
                    // 保留两位小数
                    BigDecimal b = new BigDecimal(d);
                    subGameData1.setWinCountRatio(b.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue());
                }
            });
        });


        return l1;
    }
}


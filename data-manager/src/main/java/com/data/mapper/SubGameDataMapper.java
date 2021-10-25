package com.data.mapper;

import com.manager.common.core.domain.model.SubGameData;
import com.manager.common.core.domain.model.SubGameDataExcel;
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
    List<SubGameDataExcel> getSubGameDataList(SubGameData subGameActualData);

    /**
     * 获取人局数
     */
    List<SubGameDataExcel> getHumBurCount(SubGameData subGameActualData);

    /**
     * 赢钱人数
     */
    List<SubGameDataExcel> getWinCount(SubGameData subGameActualData);


    List<SubGameDataExcel> getTableDate(SubGameData subGameData);

    List<SubGameDataExcel> getHumBurTableDate(SubGameData subGameData);

    List<SubGameDataExcel> getWinTableDate(SubGameData subGameData);
}

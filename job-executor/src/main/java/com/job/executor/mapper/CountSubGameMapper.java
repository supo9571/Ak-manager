package com.job.executor.mapper;

import com.job.executor.domain.AgentCommission;
import com.job.executor.domain.CountSubGame;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

/**
 * 子游戏实时数据
 * @author sieGuang 2021/10/01
 */
@Mapper
public interface CountSubGameMapper {

    CountSubGame getInitTime();

    /**
     * 子游戏实时汇总数据
     * @param parentId 通过父id找到对应的，游戏id数据
     * @param initTime 筛选时间
     * @param endTime 筛选时间
     * @return
     */
    List<CountSubGame> selectSubGameActualData(@Param("parentId") String parentId,
                                               @Param("initTime") String initTime,
                                               @Param("endTime") String endTime);


    void initSubGameActualData(List<CountSubGame> list);


}

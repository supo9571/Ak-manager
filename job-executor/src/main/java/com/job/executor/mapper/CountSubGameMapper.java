package com.job.executor.mapper;

import com.job.executor.domain.AgentCommission;
import com.job.executor.domain.CountSubGame;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    /**
     * 插入对应的数据到子游戏实时表
     * @param list
     */
    void initSubGameActualData(List<CountSubGame> list);

    /**
     * 更新全表 初始化时间
     */
    @Update("update config_sub_game_actual_data set init_time = sysdate() where 1 = 1")
    void editInitTime();


}

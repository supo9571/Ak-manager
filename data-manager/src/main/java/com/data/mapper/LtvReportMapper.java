package com.data.mapper;

import com.manager.common.core.domain.model.LtvReport;
import com.manager.common.core.domain.model.RetainedAnalysis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Ltv报表
 * @author sieGuang 2021/10/15
 */
@Mapper
public interface LtvReportMapper {

    /**
     * 查询
     * @param ltvReport 过滤条件
     */
    List<LtvReport> getList(LtvReport ltvReport);

    String getRecharge(@Param("day") String day,
                        @Param("uids") String uids,
                        @Param("recharge") Integer recharge);


}

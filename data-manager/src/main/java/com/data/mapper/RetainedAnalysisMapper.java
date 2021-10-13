package com.data.mapper;

import com.manager.common.core.domain.model.AddUser;
import com.manager.common.core.domain.model.RetainedAnalysis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 留存分析
 * @author sieGuang 2021/10/13
 */
@Mapper
public interface RetainedAnalysisMapper {

    /**
     * 查询
     * @param retainedAnalysis 过滤条件
     */
    List<RetainedAnalysis> getList(RetainedAnalysis retainedAnalysis);

}

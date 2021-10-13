package com.data.service;

import com.manager.common.core.domain.model.AddUser;
import com.manager.common.core.domain.model.RetainedAnalysis;

import java.util.List;

/**
 * 留存分析
 * @author sieGuang 2021/10/13
 */
public interface RetainedAnalysisService {

    /**
     * 查询
     */
    List<RetainedAnalysis> getList(RetainedAnalysis retainedAnalysis);
}

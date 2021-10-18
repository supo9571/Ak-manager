package com.data.service;

import com.manager.common.core.domain.model.LtvReport;
import com.manager.common.core.domain.model.RetainedAnalysis;

import java.util.List;

/**
 * Ltv报表
 * @author sieGuang 2021/10/15
 */
public interface LtvReportService {

    /**
     * 查询
     */
    List<LtvReport> getList(LtvReport ltvReport);
}

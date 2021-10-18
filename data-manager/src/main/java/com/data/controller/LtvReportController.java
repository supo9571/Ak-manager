package com.data.controller;

import com.data.service.LtvReportService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.LtvReport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Ltv报表
 * @author sieGuang 2021/10/15
 */
@RestController
@RequestMapping("/data/ltvReport")
@Slf4j
public class LtvReportController extends BaseController {

    @Autowired
    private LtvReportService ltvReportService;

    /**
     * 查询
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody LtvReport ltvReport) {
        return AjaxResult.success("查询成功", ltvReportService.getList(ltvReport));
    }

}

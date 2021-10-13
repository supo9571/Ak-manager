package com.data.controller;

import com.data.service.RetainedAnalysisService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.AddUser;
import com.manager.common.core.domain.model.RetainedAnalysis;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 留存分析
 * @author sieGuang 2021/10/13
 */
@RestController
@RequestMapping("/data/retainedAnalysis")
@Slf4j
public class RetainedAnalysisController extends BaseController {

    @Autowired
    private RetainedAnalysisService retainedAnalysisService;

    /**
     * 查询
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody RetainedAnalysis retainedAnalysis) {
        return AjaxResult.success("查询成功", retainedAnalysisService.getList(retainedAnalysis));
    }

}

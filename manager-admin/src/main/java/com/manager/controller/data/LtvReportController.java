package com.manager.controller.data;

import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.LtvReport;
import com.manager.common.core.domain.model.RetainedAnalysis;
import com.manager.common.utils.SecurityUtils;
import com.manager.common.utils.file.FileUtils;
import com.manager.common.utils.poi.ExcelUtil;
import com.manager.openFegin.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Ltv报表
 * @author sieGuang 2021/10/15
 */
@RestController
@Api(tags = "Ltv报表")
@RequestMapping("/data/ltvReport")
public class LtvReportController {

    @Autowired
    private DataService dataService;

    /**
     * 查询Ltv报表
     */
    @PreAuthorize("@ss.hasPermi('data:ltvReport:list')")
    @ApiOperation(value = "查询Ltv报表")
    @PostMapping("/list")
    public AjaxResult getLtvReport(@RequestBody LtvReport ltvReport) {
        ltvReport.setTid2(SecurityUtils.getUserId());
        return dataService.getLtvReport(ltvReport);
    }

    @PreAuthorize("@ss.hasPermi('data:ltvReport:list')")
    @ApiOperation(value = "Ltv报表导出")
    @PostMapping("/export")
    public void export(@RequestBody LtvReport ltvReport, HttpServletResponse response) throws IOException {
        ltvReport.setTid2(SecurityUtils.getUserId());
        List list = (List) dataService.getLtvReport(ltvReport).get("data");
        ExcelUtil<LtvReport> util = new ExcelUtil(LtvReport.class);
        String fileName = "Ltv报表导出";
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setFileName(response, fileName + ".xlsx");
        util.downloadExcel(list, fileName, response.getOutputStream());
    }

}

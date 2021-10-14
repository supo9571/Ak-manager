package com.manager.controller.data;

import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.AddUser;
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
 * 留存分析
 * @author sieGuang 2021/10/13
 */
@RestController
@Api(tags = "留存分析")
@RequestMapping("/data/retainedAnalysis")
public class RetainedAnalysisController {

    @Autowired
    private DataService dataService;

    /**
     * 查询留存分析
     */
    @PreAuthorize("@ss.hasPermi('data:retainedAnalysis:list')")
    @ApiOperation(value = "查询留存分析 (type:获取对应页面的数据)")
    @PostMapping("/list")
    public AjaxResult getRetainedAnalysis(@RequestBody RetainedAnalysis retainedAnalysis) {
        retainedAnalysis.setTid2(SecurityUtils.getUserId());
        return dataService.getRetainedAnalysis(retainedAnalysis);
    }

    @PreAuthorize("@ss.hasPermi('data:retainedAnalysis:list')")
    @ApiOperation(value = "留存分析导出")
    @PostMapping("/export")
    public void gameExport(@RequestBody RetainedAnalysis retainedAnalysis, HttpServletResponse response) throws IOException {
        retainedAnalysis.setTid2(SecurityUtils.getUserId());
        List list = (List) dataService.getRetainedAnalysis(retainedAnalysis).get("data");
        ExcelUtil<RetainedAnalysis> util = new ExcelUtil(RetainedAnalysis.class);
        String fileName = "留存分析导出";
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setFileName(response, fileName + ".xlsx");
        util.downloadExcel(list, fileName, response.getOutputStream());
    }

}

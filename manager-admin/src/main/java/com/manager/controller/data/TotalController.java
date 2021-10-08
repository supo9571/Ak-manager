package com.manager.controller.data;

import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.SubGameDataExcel;
import com.manager.common.core.domain.model.Summarize;
import com.manager.common.utils.file.FileUtils;
import com.manager.common.utils.poi.ExcelUtil;
import com.manager.openFegin.AgentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author marvin 2021/10/6
 * 总览
 */
@RestController
@Api(tags = "总览")
@RequestMapping("/data/total")
public class TotalController {
    @Autowired
    private AgentService agentService;

    /**
     * 获取 总览列表
     */
    @PreAuthorize("@ss.hasPermi('data:total:list')")
    @ApiOperation(value = "查询总览下方列表")
    @GetMapping("/list")
    public AjaxResult list(Summarize summarize) {
        return agentService.getTotals(summarize);
    }

    /**
     * 导出 总览列表
     */
    @PreAuthorize("@ss.hasPermi('data:total:list')")
    @ApiOperation(value = "总览列表导出")
    @GetMapping("/export")
    public void export(Summarize summarize, HttpServletResponse response) throws IOException {
        List list = agentService.getExport(summarize);
        ExcelUtil<Summarize> util = new ExcelUtil(Summarize.class);
        String fileName = "总览列表导出";
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setFileName(response, fileName + ".xlsx");
        util.downloadExcel(list, fileName, response.getOutputStream());
    }

    /**
     * 总览 左侧数据
     */
    @PreAuthorize("@ss.hasPermi('data:total:list')")
    @ApiOperation(value = "查询总览左侧数据")
    @PostMapping("/left")
    public AjaxResult left(int tid) {
        return agentService.getLeft(tid);
    }

    /**
     * 总览 右侧数据
     */
    @PreAuthorize("@ss.hasPermi('data:total:list')")
    @ApiOperation(value = "查询总览右侧数据")
    @PostMapping("/right")
    public AjaxResult right(int tid,String beginTime,String endTime) {
        return agentService.getRight(tid,beginTime,endTime);
    }
}

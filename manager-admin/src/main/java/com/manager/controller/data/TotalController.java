package com.manager.controller.data;

import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.Summarize;
import com.manager.openFegin.AgentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

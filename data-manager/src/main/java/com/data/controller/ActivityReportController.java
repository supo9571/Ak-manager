package com.data.controller;

import com.data.service.ActivityReportService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.Coins;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jason
 * @date 2021-10-07
 */
@RestController
@RequestMapping("/data/activity/report")
public class ActivityReportController extends BaseController {

    @Resource
    private ActivityReportService activityReportService;

    /**
     * 获取账变记录列表
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody Coins coins) {
        List list = activityReportService.selectActivityList(coins);
        return AjaxResult.success("查询成功", list);
    }



}

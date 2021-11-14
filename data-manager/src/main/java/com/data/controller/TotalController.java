package com.data.controller;

import com.data.service.TotalService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.Summarize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/10/6
 * 总览 数据
 */
@RestController
@RequestMapping("/data/total")
public class TotalController extends BaseController {

    @Autowired
    private TotalService totalService;

    /**
     * 总览 列表数据查询
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody Summarize summarize) {
        startPage(summarize.getPage(), summarize.getSize(), summarize.getOrderByColumn(), summarize.getIsAsc());
        List list = totalService.getTotals(summarize);
        return AjaxResult.success(getDataTable(list));
    }
    /**
     * 总览 列表数据导出
     */
    @PostMapping("/export")
    public List export(@RequestBody Summarize summarize) {
        return totalService.getTotals(summarize);
    }

    /**
     * 总览 左侧数据
     */
    @PostMapping("/left")
    public AjaxResult left(String tid) {
        //左侧 玩家数据
        Map left = totalService.getLeft(tid);
        return AjaxResult.success(left);
    }

    /**
     * 总览 右侧数据
     */
    @PostMapping("/right")
    public AjaxResult right(String tid,String beginTime,String endTime) {
        //右侧 经济 盈亏
        Map right = totalService.getRight(tid,beginTime,endTime);
        return AjaxResult.success(right);
    }
}

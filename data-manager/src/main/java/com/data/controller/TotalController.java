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
}

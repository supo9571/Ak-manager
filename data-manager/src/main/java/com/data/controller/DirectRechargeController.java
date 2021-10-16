package com.data.controller;

import com.data.service.DirectRechargeService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.DirectRecharge;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 直属玩家充值报表
 * @author sieGuang 2021/10/016
 */
@RestController
@RequestMapping("/data/directRecharge")
@Slf4j
public class DirectRechargeController extends BaseController {

    @Autowired
    private DirectRechargeService directRechargeService;

    /**
     * 查询
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody DirectRecharge directRecharge) {
        startOrder(directRecharge.getOrderByColumn(),directRecharge.getIsAsc());
        return AjaxResult.success("查询成功", directRechargeService.getList(directRecharge));
    }

    /**
     * 每日明细
     */
    @PostMapping("/subList")
    public AjaxResult subList(@RequestBody DirectRecharge directRecharge) {
        return AjaxResult.success("查询成功", directRechargeService.getSubList(directRecharge));
    }

}

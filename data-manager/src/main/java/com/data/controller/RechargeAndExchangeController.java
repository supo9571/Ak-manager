package com.data.controller;

import com.data.service.RechargeAndExchangeService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.AddUser;
import com.manager.common.core.domain.model.RechargeAndExchange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 充值和提现统计
 * @author sieGuang 2021/10/19
 */
@RestController
@RequestMapping("/data/rechargeAndExchange")
@Slf4j
public class RechargeAndExchangeController extends BaseController {

    @Autowired
    private RechargeAndExchangeService rechargeAndExchangeService;

    /**
     * 查询
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody RechargeAndExchange rechargeAndExchange) {
        return AjaxResult.success("查询成功", rechargeAndExchangeService.getList(rechargeAndExchange));
    }

}

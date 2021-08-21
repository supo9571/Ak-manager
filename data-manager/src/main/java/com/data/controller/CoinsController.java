package com.data.controller;

import com.data.service.CoinsService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.Coins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author marvin 2021/8/19
 * 账变记录
 */
@RestController
@RequestMapping("/data/coins")
public class CoinsController extends BaseController {

    @Autowired
    private CoinsService coinsService;
    /**
     * 获取用户列表
     */
    @PostMapping("/list")
    public AjaxResult list(Coins coins) {
        startPage(coins.getPage(),coins.getSize(),coins.getOrderByColumn(),coins.getIsAsc());
        List list = coinsService.selectCoins(coins);
        return AjaxResult.success("查询成功", getDataTable(list));
    }
}

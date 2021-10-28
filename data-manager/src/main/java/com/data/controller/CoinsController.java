package com.data.controller;

import com.data.service.CoinsService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.Coins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     * 获取账变记录列表
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody Coins coins) {
        startPage(coins.getPage(), coins.getSize(), coins.getOrderByColumn(), coins.getIsAsc());
        List list = coinsService.selectCoins(coins);
        //计算总计
        BigDecimal count = coinsService.selectCoinsCount(coins);
        Map result = new HashMap();
        result.put("list", getDataTable(list));
        result.put("count", count);
        return AjaxResult.success("查询成功", result);
    }
}

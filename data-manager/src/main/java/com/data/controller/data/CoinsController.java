package com.data.controller.data;

import com.data.controller.common.BaseController;
import com.data.domain.Coins;
import com.data.domain.common.AjaxResult;
import com.data.service.CoinsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author marvin 2021/8/19
 * 账变记录
 */
@RestController
public class CoinsController extends BaseController {

    @Autowired
    private CoinsService coinsService;
    /**
     * 获取用户列表
     */
    @GetMapping("/list")
    public AjaxResult list(Coins coins) {
        startPage();
        List list = coinsService.selectCoins(coins);
        return AjaxResult.success("查询成功", getDataTable(list));
    }
}

package com.manager.controller.data;

import com.manager.common.annotation.Log;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.PlayUser;
import com.manager.common.core.domain.model.UserExchange;
import com.manager.common.enums.BusinessType;
import com.manager.openFegin.AgentService;
import com.manager.openFegin.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * @author marvin 2021/8/19
 */
@RestController
@Api(tags = "玩家列表")
@RequestMapping("/data/player")
public class PlayerController extends BaseController {

    @Autowired
    private DataService dataService;

    @Autowired
    private AgentService agentService;

    /**
     * 获取用户列表
     */
    @PreAuthorize("@ss.hasPermi('data:player:list')")
    @ApiOperation(value = "查询玩家列表")
    @GetMapping("/list")
    public AjaxResult list(PlayUser playUser) {
        return dataService.getPlayers(playUser);
    }

    /**
     * 查询玩家余额
     */
    @PreAuthorize("@ss.hasPermi('data:player:curr')")
    @ApiOperation(value = "查询玩家余额")
    @GetMapping("/curr")
    public AjaxResult curr(Long uid) {
        return dataService.getPlayerCurr(uid);
    }

    /**
     * 修改玩家信息
     */
    @PreAuthorize("@ss.hasPermi('data:player:edit')")
    @ApiOperation(value = "修改基础信息")
    @Log(title = "修改玩家基础信息", businessType = BusinessType.UPDATE)
    @GetMapping("/edit")
    public AjaxResult edit(PlayUser playUser) {
        return dataService.updatePlayer(playUser);
    }

    /**
     * 玩家信息
     */
    @PreAuthorize("@ss.hasPermi('data:player:edit')")
    @ApiOperation(value = "信息修改详情")
    @GetMapping("/info")
    public AjaxResult info(Long uid) {
        return dataService.getInfo(uid);
    }

    /**
     * 修改 银行卡/支付宝 信息
     */
    @PreAuthorize("@ss.hasPermi('data:player:edit')")
    @ApiOperation(value = "银行卡/支付宝修改")
    @Log(title = "修改玩家银行卡/支付宝信息", businessType = BusinessType.UPDATE)
    @GetMapping("/editExchange")
    public AjaxResult editBank(@RequestBody UserExchange userExchange) {
        return dataService.updateBank(userExchange);
    }

    /**
     * 充值提现 数据
     */
    @PreAuthorize("@ss.hasPermi('data:player:list')")
    @ApiOperation(value = "充值提现数据")
    @PostMapping("/recAndexc")
    public AjaxResult recAndexc(Long uid) {
        return dataService.recAndexc(uid);
    }

    /**
     * 充值记录
     */
    @PreAuthorize("@ss.hasPermi('data:player:list')")
    @ApiOperation(value = "充值记录")
    @PostMapping("/rechargeInfo")
    public AjaxResult rechargeInfo(@RequestBody PlayUser playUser) {
        return dataService.rechargeInfo(playUser);
    }

    /**
     * 提现记录
     */
    @PreAuthorize("@ss.hasPermi('data:player:list')")
    @ApiOperation(value = "提现记录")
    @PostMapping("/exchangeInfo")
    public AjaxResult exchangeInfo(@RequestBody PlayUser playUser) {
        return dataService.exchangeInfo(playUser);
    }

    /**
     * 推广记录
     */
    @PreAuthorize("@ss.hasPermi('data:player:list')")
    @ApiOperation(value = "推广记录")
    @PostMapping("/popularize")
    public AjaxResult popularize(String uid, Integer page, Integer size, String orderByColumn, String isAsc) {
        return agentService.getPopularizes(uid, page, size, orderByColumn, isAsc);
    }


}

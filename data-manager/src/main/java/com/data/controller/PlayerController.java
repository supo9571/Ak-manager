package com.data.controller;

import com.data.service.PlayerService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.PlayUser;
import com.manager.common.core.domain.model.PlayWater;
import com.manager.common.core.domain.model.UserExchange;
import com.manager.common.core.domain.model.UserLock;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/20
 * 玩家列表
 */
@RestController
@RequestMapping("/data/player")
public class PlayerController extends BaseController {

    @Autowired
    private PlayerService playerService;

    @PostMapping("/list")
    public AjaxResult list(@RequestBody PlayUser playUser) {
        startPage(playUser.getPage(), playUser.getSize(), playUser.getOrderByColumn(), playUser.getIsAsc());
        List list = playerService.selectPlayer(playUser);
        return AjaxResult.success(getDataTable(list));
    }

    @PostMapping("/curr")
    public AjaxResult curr(Long uid) {
        List list = playerService.selectPlayerCurr(uid);
        return AjaxResult.success(list);
    }

    /**
     * 修改基础信息
     */
    @PostMapping("/edit")
    public AjaxResult update(@RequestBody PlayUser playUser) {
        if (StringUtils.isNotBlank(playUser.getPassword())) {
            String password = DigestUtils.md5DigestAsHex(playUser.getPassword().getBytes(StandardCharsets.UTF_8));
            playUser.setPassword(password);
        }
        Integer i = playerService.updatePlayer(playUser);
        return i > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 玩家信息
     */
    @PostMapping("/info")
    public AjaxResult info(Long uid) {
        AjaxResult ajaxResult = AjaxResult.success();
        Map map = new HashMap();
        String phone = playerService.getPhone(uid);
        map.put("phone",phone);
        Map bankMap = playerService.getBankInfo(uid);
        Map alipayMap = playerService.getAlipayInfo(uid);
        map.put("bankInfo",bankMap);
        map.put("alipayInfo",alipayMap);
        ajaxResult.put("data",map);
        return ajaxResult;
    }

    /**
     * 修改 银行卡/支付宝 信息
     */
    @PostMapping("/editExchange")
    public AjaxResult editBank(@RequestBody UserExchange userExchange) {
        Integer i = playerService.updateBank(userExchange);
        return i > 0 ? AjaxResult.success() : AjaxResult.error();
    }

    /**
     * 充值提现 数据
     */
    @PostMapping("/recAndexc")
    public AjaxResult recAndexc(Long uid) {
        return AjaxResult.success(playerService.getRecAndexc(uid));
    }

    /**
     * 充值记录
     */
    @PostMapping("/rechargeInfo")
    public AjaxResult rechargeInfo(@RequestBody PlayUser playUser) {
        startPage(playUser.getPage(), playUser.getSize(), playUser.getOrderByColumn(), playUser.getIsAsc());
        List list = playerService.getRechargeInfo(playUser.getUid());
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 提现记录
     */
    @PostMapping("/exchangeInfo")
    public AjaxResult exchangeInfo(@RequestBody PlayUser playUser) {
        startPage(playUser.getPage(), playUser.getSize(), playUser.getOrderByColumn(), playUser.getIsAsc());
        List list = playerService.getExchangeInfo(playUser.getUid());
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 基本详情
     */
    @PostMapping("/userInfo")
    public AjaxResult userInfo(Long uid) {
        Map map = playerService.userInfo(uid);
        return AjaxResult.success(map);
    }

    /**
     * 流水日志
     */
    @PostMapping("/waterInfo")
    public AjaxResult waterInfo(@RequestBody PlayWater playWater) {
        startPage(playWater.getPage(), playWater.getSize(), playWater.getOrderByColumn(), playWater.getIsAsc());
        List list = playerService.waterInfo(playWater);
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 更改设备码
     */
    @PostMapping("/updateToken")
    public AjaxResult updateToken(Long uid) {
        playerService.updateToken(uid);
        return AjaxResult.success();
    }

    /**
     * 封号解封
     */
    @PostMapping("/lock")
    public AjaxResult lock(@RequestBody UserLock userLock) {
        playerService.saveUserLock(userLock);
        return AjaxResult.success();
    }

    /**
     * 封号解封记录
     */
    @PostMapping("/lockLog")
    public AjaxResult lockLog(Long uid) {
        return AjaxResult.success(playerService.getLockLog(uid));
    }
}

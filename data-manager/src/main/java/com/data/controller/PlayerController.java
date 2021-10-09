package com.data.controller;

import com.data.service.PlayerService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.PlayUser;
import com.manager.common.core.domain.model.UserExchange;
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
    @PostMapping("/recharInfo")
    public AjaxResult recharInfo(@RequestBody PlayUser playUser) {
        startPage(playUser.getPage(), playUser.getSize(), playUser.getOrderByColumn(), playUser.getIsAsc());
        List list = playerService.getRecharInfo(playUser.getUid());
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
}

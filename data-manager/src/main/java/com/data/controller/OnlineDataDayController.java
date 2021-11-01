package com.data.controller;

import com.data.service.OnlineDataDayService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.AddUser;
import com.manager.common.core.domain.model.OnlineDataDay;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 实时在线数据
 * @author sieGuang 2021/10/21
 */
@RestController
@RequestMapping("/data/onlineDataDay")
@Slf4j
public class OnlineDataDayController extends BaseController {

    @Autowired
    private OnlineDataDayService onlineDataDayService;

    /**
     * 在线玩家数据（图）
     */
    @PostMapping("/getOnlineUserData")
    public AjaxResult getOnlineUserData(@RequestBody OnlineDataDay onlineDataDay) {
        return AjaxResult.success("查询成功", onlineDataDayService.getOnlineUserData(onlineDataDay));
    }

    /**
     * PCU周期数据分析
     */
    @PostMapping("/getPcuData")
    public AjaxResult getPcuData(@RequestBody OnlineDataDay onlineDataDay) {
        return AjaxResult.success("查询成功", onlineDataDayService.getPcuData(onlineDataDay));
    }

    /**
     * 在线玩家数据（表格）
     */
    @PostMapping("/getOnlineUserNum")
    public AjaxResult getOnlineUserNum(@RequestBody OnlineDataDay onlineDataDay) {
        return AjaxResult.success("查询成功", onlineDataDayService.getOnlineUserNum(onlineDataDay));
    }

}

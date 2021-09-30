package com.data.controller;

import com.data.service.Loginservice;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author marvin 2021/8/21
 * 玩家登录日志
 */
@RestController
@RequestMapping("/data/login")
public class LoginController extends BaseController {
    @Autowired
    private Loginservice loginservice;

    /**
     * 获取在线玩家列表
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody Login login) {
        startPage(login.getPage(), login.getSize(), login.getOrderByColumn(), login.getIsAsc());
        List list = loginservice.selectLogin(login);
        return AjaxResult.success("查询成功", getDataTable(list));
    }

    /**
     * 今日登录总数
     */
    @PostMapping("/today")
    public AjaxResult today() {
        Integer todayLogins = loginservice.selectTodayLogins();
        return AjaxResult.success("查询成功", todayLogins);
    }

    /**
     * 近期登录数
     */
    @PostMapping("/count")
    public AjaxResult count(String type) {
        return AjaxResult.success("查询成功", loginservice.selectLoginCounts(type));
    }
}

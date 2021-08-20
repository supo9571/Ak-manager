package com.manager.web.controller.data;

import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.PlayUser;
import com.manager.openFegin.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marvin 2021/8/19
 */
@RestController
@Api(tags = "玩家列表")
@RequestMapping("/data/player")
public class PlayerController extends BaseController {

    @Autowired
    private UserService userService;
    /**
     * 获取用户列表
     */
    @PreAuthorize("@ss.hasPermi('data:player:list')")
    @ApiOperation(value = "查询账变列表")
    @GetMapping("/list")
    public AjaxResult list(PlayUser playUser) {
        return userService.list(playUser);
    }
}

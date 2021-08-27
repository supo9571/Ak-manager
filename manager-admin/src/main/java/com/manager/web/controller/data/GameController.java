package com.manager.web.controller.data;

import com.manager.common.core.domain.AjaxResult;
import com.manager.openFegin.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marvin 2021/8/27
 */
@RestController
@Api(tags = "游戏管理")
@RequestMapping("/data/game")
public class GameController {

    @Autowired
    private DataService dataService;

    /**
     * 获取游戏下拉框
     */
    @ApiOperation(value = "获取游戏下拉列表")
    @GetMapping("/option")
    public AjaxResult option() {
        return dataService.getGames();
    }
}

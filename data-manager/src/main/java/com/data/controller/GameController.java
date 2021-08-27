package com.data.controller;

import com.data.service.GameService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.Coins;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marvin 2021/8/27
 */
@RestController
@RequestMapping("/data/game")
public class GameController extends BaseController{

    @Autowired
    private GameService gameService;
    /**
     * 获取游戏下拉选项
     */
    @PostMapping("/option")
    public AjaxResult list() {
        return AjaxResult.success("查询成功", gameService.getGames());
    }

}

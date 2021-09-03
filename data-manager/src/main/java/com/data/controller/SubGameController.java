package com.data.controller;

import com.data.service.SubGameService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.Game;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 子游戏管理
 * @author sieGuang 2021/09/03
 */
@RestController
@RequestMapping("/data/game")
@Slf4j
public class SubGameController extends BaseController {

    @Autowired
    private SubGameService subgameService;

    /**
     * 查询
     * @param game 过滤条件
     */
    @PostMapping("/list")
    public AjaxResult getSubGameList(@RequestBody Game game) {
        startOrder(game.getOrderByColumn(),game.getIsAsc());
        Map result = new HashMap();
        result.put("list",subgameService.getSubGameList(game));
        result.put("count",subgameService.getIpCount());
        return AjaxResult.success("查询成功", result);
    }

    /**
     * 编辑
     * @param game 需要修改的内容
     */
    @PostMapping("/edit")
    public AjaxResult editSubGame(@RequestBody Game game) {
        int i = subgameService.editSubGame(game);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

}

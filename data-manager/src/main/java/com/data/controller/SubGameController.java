package com.data.controller;

import com.alibaba.fastjson.JSONObject;
import com.data.config.GlobalConfig;
import com.data.service.GameService;
import com.data.service.SubGameService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.Game;
import com.manager.common.core.domain.model.Hotupdate;
import com.manager.common.utils.http.HttpUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 子游戏管理
 * @author sieGuang 2021/09/03
 */
@RestController
@RequestMapping("/data/subGame")
@Slf4j
public class SubGameController extends BaseController {

    @Autowired
    private SubGameService subgameService;

    /**
     * 查询
     * @param game 过滤条件
     */
    @PostMapping("/getSubGameList")
    public AjaxResult getSubGameList(@RequestBody Game game) {
        return AjaxResult.success("查询成功", subgameService.getSubGameList(game));
    }

    /**
     * 编辑
     * @param game 需要修改的内容
     */
    @PostMapping("/editSubGame")
    public AjaxResult editSubGame(@RequestBody Game game) {
        int i = subgameService.editSubGame(game);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

}
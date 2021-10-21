package com.data.controller;

import com.data.service.ControlPlayerService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.ControlPlayer;
import com.manager.common.core.domain.model.ControlPlayerInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author marvin 2021/10/20
 * 玩家风控设置
 */
@RestController
@RequestMapping("/data/control/player")
public class ControlPlayerController extends BaseController{

    @Autowired
    private ControlPlayerService controlPlayerService;

    /**
     * 添加玩家风控设置
     */
    @PostMapping("/add")
    public AjaxResult add(@RequestBody ControlPlayer controlPlayer) {
        if(controlPlayerService.checkUid(controlPlayer)){//判断uid是否合法
            return AjaxResult.error("玩家id错误!");
        }
        controlPlayerService.add(controlPlayer);
        return AjaxResult.success();
    }

    /**
     * 查询玩家风控设置
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody ControlPlayer controlPlayer) {
        startPage(controlPlayer.getPage(),controlPlayer.getSize(),controlPlayer.getOrderByColumn(),controlPlayer.getIsAsc());
        List list = controlPlayerService.list(controlPlayer);
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 修改玩家风控设置
     */
    @PostMapping("/edit")
    public AjaxResult edit(@RequestBody ControlPlayer controlPlayer) {
        controlPlayerService.edit(controlPlayer);
        return AjaxResult.success();
    }

    /**
     * 移除玩家风控设置
     */
    @PostMapping("/del")
    public AjaxResult del(@RequestBody ControlPlayer controlPlayer) {
        controlPlayerService.del(controlPlayer);
        return AjaxResult.success();
    }

    /**
     * 查询玩家风控设日志
     */
    @PostMapping("/infoList")
    public AjaxResult infoList(@RequestBody ControlPlayerInfo controlPlayerInfo) {
        startPage(controlPlayerInfo.getPage(),controlPlayerInfo.getSize(),controlPlayerInfo.getOrderByColumn(),controlPlayerInfo.getIsAsc());
        List list = controlPlayerService.infoList(controlPlayerInfo);
        return AjaxResult.success(getDataTable(list));
    }

}

package com.manager.controller.control;

import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.GameStore;
import com.manager.common.enums.BusinessType;
import com.manager.system.service.GameStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author marvin 2021/10/13
 * 游戏库存策略
 */
@RestController
@Api(tags = "游戏库存策略")
@RequestMapping("/control/game")
@Slf4j
public class GameStoreController extends BaseController {

    @Autowired
    private GameStoreService gameStoreService;

    @Autowired
    private ManagerConfig managerConfig;

    /**
     * 新增游戏库存策略
     */
    @PreAuthorize("@ss.hasPermi('control:game:add')")
    @ApiOperation(value = "新增游戏库存策略")
    @Log(title = "新增游戏库存策略", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult addGameStrategy(GameStore gameStore) {
        return toAjax(gameStoreService.addGameStrategy(gameStore));
    }

    /**
     * 查询游戏库存策略
     */
    @PreAuthorize("@ss.hasPermi('control:game:list')")
    @ApiOperation(value = "查询游戏库存策略")
    @GetMapping("/list")
    public AjaxResult getGameStrategys(@RequestParam(defaultValue = "0") Integer strategyTagId) {
        startPage();
        List list = gameStoreService.getGameStrategys(strategyTagId);
        return AjaxResult.success(getDataTable(list));
    }

    /**
     * 编辑游戏库存策略
     */
    @PreAuthorize("@ss.hasPermi('control:game:edit')")
    @ApiOperation(value = "编辑游戏库存策略")
    @Log(title = "编辑游戏库存策略", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult editGameStrategy(GameStore gameStore) {
        return toAjax(gameStoreService.editGameStrategy(gameStore));
    }

    /**
     * 删除游戏库存策略
     */
    @PreAuthorize("@ss.hasPermi('control:game:del')")
    @ApiOperation(value = "删除游戏库存策略")
    @Log(title = "删除游戏库存策略", businessType = BusinessType.DELETE)
    @GetMapping("/del")
    public AjaxResult delGameStrategy(Integer id) {
        return toAjax(gameStoreService.delGameStrategy(id));
    }

    /**
     * 发送游戏库存策略
     */
    @PreAuthorize("@ss.hasPermi('control:game:send')")
    @ApiOperation(value = "发送游戏库存策略")
    @Log(title = "发送游戏库存策略", businessType = BusinessType.OTHER)
    @GetMapping("/send")
    public AjaxResult sendGameStrategy() {
        String param = gameStoreService.sendGameStrategy();
        return toSend(managerConfig.getDomain()+managerConfig.getGameSend(),param);
    }
}

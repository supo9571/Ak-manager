package com.manager.controller.control;

import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.PlatformStore;
import com.manager.common.enums.BusinessType;
import com.manager.system.service.PlatformStoreService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author marvin 2021/10/13
 */
@RestController
@Api(tags = "盘口策略配置")
@RequestMapping("/control/platform")
@Slf4j
public class PlatformStoreController extends BaseController {

    @Autowired
    private PlatformStoreService platformStoreService;

    /**
     * 查询盘口策略配置
     */
    @PreAuthorize("@ss.hasPermi('control:platform:list')")
    @ApiOperation(value = "查询盘口策略配置")
    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "platformId", value = "所属平台"),
            @ApiImplicitParam(name = "strategyGameId", value = "游戏库存标签"),
            @ApiImplicitParam(name = "strategyPersonId", value = "个人库存标签")
    })
    public AjaxResult getPlatformStrategys(@RequestParam(defaultValue = "0") Integer platformId,@RequestParam(defaultValue = "0") Integer strategyGameId,
                                         @RequestParam(defaultValue = "0") Integer strategyPersonId) {
        return AjaxResult.success(platformStoreService.getPlatformStrategys(platformId,strategyGameId,strategyPersonId));
    }

    /**
     * 编辑盘口策略配置
     */
    @PreAuthorize("@ss.hasPermi('control:platform:edit')")
    @ApiOperation(value = "编辑盘口策略配置")
    @Log(title = "编辑盘口策略配置", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult editPlatformStrategy(PlatformStore platformStore) {
        return toAjax(platformStoreService.editPlatformStrategy(platformStore));
    }

    @Autowired
    private ManagerConfig managerConfig;

    /**
     * 发送盘口策略配置
     */
    @PreAuthorize("@ss.hasPermi('control:platform:send')")
    @ApiOperation(value = "发送盘口策略配置")
    @Log(title = "发送盘口策略配置", businessType = BusinessType.OTHER)
    @GetMapping("/send")
    public AjaxResult sendPlatformStrategy() {
        //查询 游戏配置
        String param = platformStoreService.sendPlatformStrategy();
        return toSend(managerConfig.getDomain()+managerConfig.getGameSend(),param);
    }
}

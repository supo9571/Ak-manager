package com.manager.controller.control;

import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.PersonStore;
import com.manager.common.enums.BusinessType;
import com.manager.system.service.PersonStoreService;
import io.swagger.annotations.Api;
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
@Api(tags = "个人库存策略")
@RequestMapping("/control/person")
@Slf4j
public class PersonStoreController extends BaseController {

    @Autowired
    private PersonStoreService personStoreService;
    /**
     * 新增个人库存策略
     */
    @PreAuthorize("@ss.hasPermi('control:person:add')")
    @ApiOperation(value = "新增个人库存策略")
    @Log(title = "新增个人库存策略", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    public AjaxResult addPersonStore(PersonStore personStore) {
        return toAjax(personStoreService.addPersonStore(personStore));
    }

    /**
     * 查询个人库存策略
     */
    @PreAuthorize("@ss.hasPermi('control:person:list')")
    @ApiOperation(value = "查询个人库存策略")
    @GetMapping("/list")
    public AjaxResult getPersonStrategys(@RequestParam(defaultValue = "0") Integer strategyTagId) {
        return AjaxResult.success(personStoreService.getPersonStrategys(strategyTagId));
    }

    /**
     * 编辑个人库存策略
     */
    @PreAuthorize("@ss.hasPermi('control:person:edit')")
    @ApiOperation(value = "编辑个人库存策略")
    @Log(title = "编辑个人库存策略", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    public AjaxResult editPersonStrategy(PersonStore personStore) {
        return toAjax(personStoreService.editPersonStrategy(personStore));
    }

    /**
     * 删除个人库存策略
     */
    @PreAuthorize("@ss.hasPermi('control:person:del')")
    @ApiOperation(value = "删除个人库存策略")
    @Log(title = "删除个人库存策略", businessType = BusinessType.DELETE)
    @GetMapping("/del")
    public AjaxResult delPersonStrategy(Integer strategyId) {
        return toAjax(personStoreService.delPersonStrategy(strategyId));
    }

    @Autowired
    private ManagerConfig managerConfig;

    /**
     * 发送个人库存策略
     */
    @PreAuthorize("@ss.hasPermi('control:person:send')")
    @ApiOperation(value = "发送个人库存策略")
    @Log(title = "发送个人库存策略", businessType = BusinessType.OTHER)
    @GetMapping("/send")
    public AjaxResult sendPersonStrategy() {
        String param = personStoreService.sendPersonStrategy();
        return toSend(managerConfig.getDomain()+managerConfig.getGameSend(),param);
    }
}

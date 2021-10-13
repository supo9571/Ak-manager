package com.manager.controller.control;

import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.PersonProperty;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.http.HttpUtils;
import com.manager.system.service.PersonPropertyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marvin 2021/10/13
 * 个人属性配置
 */
@RestController
@Api(tags = "个人属性配置")
@RequestMapping("/control/property")
@Slf4j
public class PersonPropertyController extends BaseController {

    @Autowired
    private PersonPropertyService personPropertyService;

    /**
     * 查询个人属性配置
     */
    @PreAuthorize("@ss.hasPermi('control:property:list')")
    @ApiOperation(value = "查询个人属性配置")
    @GetMapping("/list")
    public AjaxResult getPersonPropertys() {
        return AjaxResult.success(personPropertyService.getPersonPropertys());
    }

    /**
     * 编辑个人属性配置
     */
    @PreAuthorize("@ss.hasPermi('control:property:edit')")
    @ApiOperation(value = "编辑个人属性配置")
    @GetMapping("/edit")
    public AjaxResult editPersonPropertys(PersonProperty personProperty) {
        return toAjax(personPropertyService.editPersonPropertys(personProperty));
    }

    @Autowired
    private ManagerConfig managerConfig;

    /**
     * 发送个人属性配置
     */
    @PreAuthorize("@ss.hasPermi('control:property:send')")
    @ApiOperation(value = "发送个人属性配置")
    @Log(title = "发送个人属性配置", businessType = BusinessType.OTHER)
    @GetMapping("/send")
    public AjaxResult sendProperty() {
        String domain = managerConfig.getDomain();
        String gameSend = managerConfig.getGameSend();
        //查询 游戏配置
        String param = personPropertyService.sendProperty();
        String result = HttpUtils.sendPost(domain + gameSend, "data=" + param);
        if (!"scuess".equals(result)) {
            log.error(result);
            return AjaxResult.error();
        }
        return AjaxResult.success();
    }
}

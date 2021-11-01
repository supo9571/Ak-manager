package com.manager.controller.control;

import com.manager.common.annotation.Log;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.entity.Tags;
import com.manager.common.enums.BusinessType;
import com.manager.system.service.TagsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marvin 2021/10/12
 * 策略标签配置
 * control_tags_config
 */
@RestController
@Api(tags = "策略标签配置")
@RequestMapping("/control/tags")
@Slf4j
public class TagsController extends BaseController {

    @Autowired
    private TagsService tagsService;

    @Autowired
    private ManagerConfig managerConfig;

    /**
     * 标签策略列表
     */
    @PreAuthorize("@ss.hasPermi('control:tags:list')")
    @ApiOperation(value = "标签列表")
    @GetMapping("/list")
    public AjaxResult getTags(@RequestParam(defaultValue = "0") Integer tagType) {
        startOrderBy();
        return AjaxResult.success(tagsService.getTags(tagType));
    }

    /**
     * 新增策略标签
     */
    @PreAuthorize("@ss.hasPermi('control:tags:add')")
    @ApiOperation(value = "新增标签")
    @Log(title = "新增标签", businessType = BusinessType.INSERT)
    @GetMapping("/add")
    public AjaxResult addTags(Tags tags) {
        return toAjax(tagsService.addTags(tags));
    }

    /**
     * 编辑策略标签
     */
    @PreAuthorize("@ss.hasPermi('control:tags:edit')")
    @ApiOperation(value = "编辑标签")
    @Log(title = "编辑标签", businessType = BusinessType.UPDATE)
    @GetMapping("/edit")
    public AjaxResult editTags(Tags tags) {
        return toAjax(tagsService.editTags(tags));
    }

    /**
     * 删除策略标签
     */
    @PreAuthorize("@ss.hasPermi('control:tags:del')")
    @ApiOperation(value = "删除标签")
    @Log(title = "删除标签", businessType = BusinessType.DELETE)
    @GetMapping("/del")
    public AjaxResult deleteTags(Integer id) {
        return toAjax(tagsService.deleteTags(id));
    }

    /**
     * 发送策略标签配置
     */
    @PreAuthorize("@ss.hasPermi('control:tags:send')")
    @ApiOperation(value = "发送策略标签配置")
    @Log(title = "发送策略标签配置", businessType = BusinessType.OTHER)
    @GetMapping("/send")
    public AjaxResult sendTags() {
        String param = tagsService.sendTags();
        return toSend(managerConfig.getDomain()+managerConfig.getGameSend(),param);
    }
}

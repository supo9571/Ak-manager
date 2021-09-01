package com.data.controller;

import com.data.service.UpdateService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.Allupdate;
import com.manager.common.core.domain.model.Hotupdate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marvin 2021/8/30
 * 包更新
 */
@RestController
@RequestMapping("/data")
@Slf4j
public class UpdateController extends BaseController {

    @Autowired
    private UpdateService updateService;

    /**
     * 添加整包更新
     */
    @PostMapping("/allupdate/add")
    public AjaxResult addAllUpdate(@RequestBody Allupdate allupdate) {
        int i = updateService.addAllUpdate(allupdate);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 整包更新 查询
     */
    @PostMapping("/allupdate/list")
    public AjaxResult list() {
        return AjaxResult.success(updateService.findAllUpdate());
    }

    /**
     * 整包更新 历史版本
     */
    @PostMapping("/allupdate/history")
    public AjaxResult history(Integer tid) {
        return AjaxResult.success(updateService.findAllUpdateHistory(tid));
    }

    /**
     * 整包更新 编辑
     */
    @PostMapping("/allupdate/edit")
    public AjaxResult editAllupdate(@RequestBody Allupdate allupdate) {
        Integer i = updateService.editAllUpdate(allupdate);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 整包更新 删除
     */
    @PostMapping("/allupdate/del")
    public AjaxResult delAllupdate(String id) {
        Integer i = updateService.deleteAllupdate(id);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 查询 热更新
     */
    @PostMapping("/hotupdate/list")
    public AjaxResult hotUpdateList() {
        return AjaxResult.success(updateService.findHotupdate());
    }

    /**
     * 根据id查询 热更新
     */
    @PostMapping("/hotupdate/find")
    public AjaxResult findHotUpdate(Integer id) {
        return AjaxResult.success(updateService.findHotupdateById(id));
    }
    /**
     * 添加 热更新
     */
    @PostMapping("/hotupdate/add")
    public AjaxResult addHotUpdate(@RequestBody Hotupdate hotUpdate) {
        int i = updateService.addHotUpdate(hotUpdate);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 修改 热更新
     */
    @PostMapping("/hotupdate/edit")
    public AjaxResult editHotUpdate(@RequestBody Hotupdate hotUpdate) {
        int i = updateService.editHotUpdate(hotUpdate);
        return i>0?AjaxResult.success():AjaxResult.error();
    }

    /**
     * 整包更新 删除
     */
    @PostMapping("/hotupdate/del")
    public AjaxResult delHotupdate(String id) {
        Integer i = updateService.delHotupdate(id);
        return i>0?AjaxResult.success():AjaxResult.error();
    }
}

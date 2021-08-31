package com.data.controller;

import com.data.service.UpdateService;
import com.manager.common.config.ManagerConfig;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.Allupdate;
import com.manager.common.core.domain.model.Hotupdate;
import com.manager.common.utils.file.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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
     * 整包更新 文件下载
     */
    @GetMapping("/package/{url}")
    public void downLoad(HttpServletResponse response, @PathVariable(value = "url",required = false) String url) {
        try {
            String realFileName = System.currentTimeMillis() + url.substring(url.indexOf("_") + 1);
            String filePath = ManagerConfig.getDownloadPath() + url;
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, realFileName);
            FileUtils.writeBytes(filePath, response.getOutputStream());
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }

    /**
     * 查询 热更新
     */
    @PostMapping("/hotupdate/list")
    public AjaxResult hotUpdateList(Integer id) {
        return AjaxResult.success(updateService.findHotupdate(id));
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
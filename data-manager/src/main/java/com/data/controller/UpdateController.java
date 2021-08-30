package com.data.controller;

import com.data.service.UpdateService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.Allupdate;
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
public class UpdateController extends BaseController {

    @Autowired
    private UpdateService updateService;
    /**
     * 添加热更新
     */
    @PostMapping("/hotupdate/add")
    public AjaxResult hotupdate() {

        return AjaxResult.success();
    }

    /**
     * 添加整包更新
     */
    @PostMapping("/allupdate/add")
    public AjaxResult addAllUpdate(@RequestBody Allupdate allupdate) {
        String version = allupdate.getVersion();
        //计算版本号
        String[] vers = version.split(".");
        int verInt = Integer.valueOf(vers[0])*10000+Integer.valueOf(vers[1])*100+Integer.valueOf(vers[2]);
        allupdate.setVerInt(verInt);
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
    public AjaxResult history(String tid) {
        return AjaxResult.success(updateService.findAllUpdateHistory(tid));
    }
}

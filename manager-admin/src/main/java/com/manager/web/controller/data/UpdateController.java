package com.manager.web.controller.data;

import com.manager.common.annotation.Log;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.Allupdate;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.file.FileUploadUtils;
import com.manager.openFegin.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author marvin 2021/8/30
 */
@RestController
@Api(tags = "包更新管理")
@RequestMapping("/data")
public class UpdateController extends BaseController {

    @Autowired
    private DataService dataService;
    /**
     * 获取用户列表
     */
    @PreAuthorize("@ss.hasPermi('data:allupdate:add')")
    @ApiOperation(value = "新增整包更新")
    @Log(title = "新增整包更新",businessType= BusinessType.INSERT)
    @PostMapping("/allupdate/add")
    public AjaxResult addAllUpdate(Allupdate allupdate) {
        return dataService.addAllUpdate(allupdate);
    }
    /**
     * 文件上传
     */
    @PreAuthorize("@ss.hasPermi('data:allupdate:upload')")
    @ApiOperation(value = "新增整包更新")
    @Log(title = "新增整包更新",businessType= BusinessType.INSERT)
    @PostMapping("/allupdate/upload")
    public AjaxResult upload(MultipartFile file) {
        String url = "";
        try {
            url = FileUploadUtils.upload(file);
        } catch (IOException e) {
            return AjaxResult.error(e.getMessage());
        }
        return AjaxResult.success("上传成功",url);
    }

}

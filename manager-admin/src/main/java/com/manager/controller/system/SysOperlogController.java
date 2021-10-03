package com.manager.controller.system;

import com.manager.common.annotation.Log;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.page.TableDataInfo;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.poi.ExcelUtil;
import com.manager.system.domain.SysOperLog;
import com.manager.system.service.ISysOperLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

/**
 * 操作日志记录
 *
 * @author marvin
 */
@RestController
@Api(tags = "操作日志")
@RequestMapping("/system/operlog")
public class SysOperlogController extends BaseController {
    @Autowired
    private ISysOperLogService operLogService;

    @PreAuthorize("@ss.hasPermi('system:operlog:list')")
    @ApiOperation(value = "查询操作日志列表")
    @GetMapping("/list")
    public AjaxResult list(SysOperLog operLog) {
        startPage();
        List<SysOperLog> list = operLogService.selectOperLogList(operLog);
        return AjaxResult.success(getDataTable(list));
    }
}

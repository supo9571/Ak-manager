package com.manager.controller.analysis;

import com.manager.common.annotation.Log;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.SubGameData;
import com.manager.common.enums.BusinessType;
import com.manager.openFegin.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 子游戏管理
 *
 * @author sieGuang 2021/09/03
 */
@RestController
@Api(tags = "子游戏实时数据")
@RequestMapping("/data/subGameActualData")
public class SubGameActualDataController {

    @Autowired
    private DataService dataService;

    /**
     * 查询
     *
     * @param subGameActualData 过滤条件
     */
    @PreAuthorize("@ss.hasPermi('data:subGameActualData:list')")
    @ApiOperation(value = "查询子游戏实时数据列表")
    @PostMapping("/list")
    public AjaxResult getSubGameActualDataList(@RequestBody SubGameData subGameActualData) {
        return dataService.getSubGameActualDataList(subGameActualData);
    }

    /**
     * 子游戏实时数据导出
     */
    @PreAuthorize("@ss.hasPermi('data:subGameActualData:edit')")
    @ApiOperation(value = "子游戏实时数据导出")
    @Log(title = "子游戏实时数据导出", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public AjaxResult exportSubGameActualData(@RequestBody SubGameData subGameActualData) {
        return dataService.exportSubGameActualData(subGameActualData);
    }
}

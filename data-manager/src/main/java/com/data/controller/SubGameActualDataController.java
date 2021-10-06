package com.data.controller;

import com.data.service.SubGameActualDataService;
import com.manager.common.annotation.Log;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.SubGameActualData;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.file.FileUtils;
import com.manager.common.utils.poi.ExcelUtil;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 子游戏实时数据
 * @author sieGuang 2021/09/30
 */
@RestController
@RequestMapping("/data/subGameActualData")
@Slf4j
public class SubGameActualDataController extends BaseController {

    @Autowired
    private SubGameActualDataService subGameActualDataService;

    /**
     * 查询
     * @param subGameActualData 过滤条件
     */
    @ApiOperation(value = "查询子游戏实时数据列表")
    @PostMapping("/list")
    public AjaxResult getSubGameActualDataList(@RequestBody SubGameActualData subGameActualData) {
        startOrder(subGameActualData.getOrderByColumn(),subGameActualData.getIsAsc());
        return AjaxResult.success("查询成功", subGameActualDataService.getSubGameActualDataList(subGameActualData));
    }

    @ApiOperation(value = "子游戏实时数据导出")
    @Log(title = "子游戏实时数据导出", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@RequestBody SubGameActualData subGameActualData, HttpServletResponse response) throws IOException {
        startOrder(subGameActualData.getOrderByColumn(),subGameActualData.getIsAsc());
        List<SubGameActualData> list = subGameActualDataService.getSubGameActualDataList(subGameActualData);

        ExcelUtil util = new ExcelUtil<SubGameActualData>(SubGameActualData.class);
        String fileName = "子游戏实时数据导出";

        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setAttachmentResponseHeader(response, fileName+".xlsx");
        util.downloadExcel(list, fileName,response.getOutputStream());
    }


}

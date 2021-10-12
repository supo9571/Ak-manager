package com.manager.controller.data;

import com.manager.common.annotation.Log;
import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.param.DataAnalysisParam;
import com.manager.common.core.domain.model.vo.DataAnalysisVO;
import com.manager.common.core.domain.model.vo.DataWaterTopVO;
import com.manager.common.enums.BusinessType;
import com.manager.common.utils.file.FileUtils;
import com.manager.common.utils.poi.ExcelUtil;
import com.manager.openFegin.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 用于数据分析相关接口
 * @author jason
 * @date 2021-10-08
 */
@RestController
@Api(tags = "数据分析")
@RequestMapping("/data/analysis/report")
public class DataAnalysisController extends BaseController {

    @Resource
    private DataService dataService;

    @ApiOperation(value = "提现top100")
    @PostMapping("/withdraw/top/List")
    public AjaxResult withdrawTopList(@RequestBody DataAnalysisParam param) {
        return dataService.withdrawTopList(param);
    }

    @ApiOperation(value = "提现top100导出")
    @Log(title = "提现top100导出", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(@RequestBody DataAnalysisParam param, HttpServletResponse response) throws IOException {
        AjaxResult ajaxResult = dataService.withdrawTopList(param);
        ExcelUtil<DataAnalysisVO> util = new ExcelUtil(DataAnalysisVO.class);
        String fileName = "提现top100导出";
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setAttachmentResponseHeader(response, fileName + ".xlsx");
        util.downloadExcel((List) ajaxResult.get("data"), fileName, response.getOutputStream());
    }

    @ApiOperation(value = "流水top100")
    @PostMapping("/water/top/List")
    public AjaxResult getDataWaterTopList(@RequestBody DataAnalysisParam param) {
        return dataService.getDataWaterTopList(param);
    }

    @ApiOperation(value = "流水top100导出")
    @Log(title = "提现top100导出", businessType = BusinessType.EXPORT)
    @PostMapping("/water/top/export")
    public void dataWaterTopExport(@RequestBody DataAnalysisParam param, HttpServletResponse response) throws IOException {
        AjaxResult ajaxResult = dataService.getDataWaterTopList(param);
        ExcelUtil<DataWaterTopVO> util = new ExcelUtil(DataWaterTopVO.class);
        String fileName = "流水top100导出";
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setAttachmentResponseHeader(response, fileName + ".xlsx");
        util.downloadExcel((List) ajaxResult.get("data"), fileName, response.getOutputStream());
    }

}

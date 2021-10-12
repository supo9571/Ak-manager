package com.data.controller;

import com.data.service.DataAnalysisService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.param.DataAnalysisParam;
import com.manager.common.core.domain.model.vo.DataAnalysisVO;
import com.manager.common.core.domain.model.vo.DataWaterTopVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用于数据分析相关接口
 * @author jason
 * @date 2021-10-08
 */
@RestController
@Api(tags = "数据分析")
@RequestMapping("/data/report")
public class DataAnalysisController extends BaseController {


    @Resource
    private DataAnalysisService dataAnalysisService;

    @ApiModelProperty("提现top100")
    @PostMapping("/withdraw/top/List")
    public AjaxResult withdrawTopList(@RequestBody DataAnalysisParam param) {
        List<DataAnalysisVO> list = dataAnalysisService.withdrawTopList(param);
        return AjaxResult.success("查询成功", list);
    }

    @ApiModelProperty("流水top100")
    @PostMapping("/water/top/List")
    public AjaxResult getDataWaterTopList(@RequestBody DataAnalysisParam param) {
        List<DataWaterTopVO> list = dataAnalysisService.getDataWaterTopList(param);
        return AjaxResult.success("查询成功", list);
    }



}

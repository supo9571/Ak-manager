package com.data.controller;

import com.data.service.DataAnalysisService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.param.DataAnalysisParam;
import com.manager.common.core.domain.model.param.PlayerReportParam;
import com.manager.common.core.domain.model.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用于数据分析相关接口
 *
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

    @ApiModelProperty("充值top100")
    @PostMapping("/recharge/top/List")
    public AjaxResult getRechargeTopList(@RequestBody DataAnalysisParam param) {
        List<RechargeTopVO> list = dataAnalysisService.getRechargeTopList(param);
        return AjaxResult.success("查询成功", list);
    }

    @ApiModelProperty("净盈利top100")
    @PostMapping("/earnings/top/List")
    public AjaxResult getEarningsTopList(@RequestBody DataAnalysisParam param) {
        List<EarningsTopVO> list = dataAnalysisService.getEarningsTopList(param);
        return AjaxResult.success("查询成功", list);
    }

    @ApiModelProperty("全民代理top100")
    @PostMapping("/agent/top/List")
    public AjaxResult getAgentTopList(@RequestBody DataAnalysisParam param) {
        List<AgentTopVO> list = dataAnalysisService.getAgentTopList(param);
        return AjaxResult.success("查询成功", list);
    }

    @ApiModelProperty("付费习惯")
    @PostMapping("/pay/top/List")
    public AjaxResult getPayInfoList(@RequestBody DataAnalysisParam param) {
        List<PayInfoVO> list = dataAnalysisService.getPayInfoList(param);
        return AjaxResult.success("查询成功", list);
    }

    @ApiModelProperty("玩家报表-主功能")
    @PostMapping("/player/List")
    public AjaxResult getPlayerReportList(@RequestBody PlayerReportParam param) {
        param.setPage2(getHandlePage(param.getPage(), param.getSize()));
        Map result = new HashMap();
        int count = dataAnalysisService.getPlayerReportCount(param);
        param.setSize(param.getSize());
        List<PlayerReportVO> list = dataAnalysisService.getPlayerReportList(param);
        List<PlayerReportVO> dataSum = dataAnalysisService.getPlayerReportSum(param);
        result.put("data", list);
        result.put("dataSum", dataSum);
        result.put("page", param.getPage());
        result.put("size", param.getSize());
        result.put("total", count);
        return AjaxResult.success("查询成功", result);
    }

    @ApiModelProperty("玩家报表-主功能导出")
    @PostMapping("/player/export")
    public AjaxResult getPlayerReportListExport(@RequestBody PlayerReportParam param) {
        List<PlayerReportVO> list = dataAnalysisService.getPlayerReportList(param);
        return AjaxResult.success("查询成功", list);
    }

    @ApiModelProperty("玩家报表-游戏明细")
    @PostMapping("/player/game/List")
    public AjaxResult getPlayerGameReportList(@RequestBody PlayerReportParam param) {
        List<PlayerGameReportVO> list = dataAnalysisService.getPlayerGameReportList(param);
        return AjaxResult.success("查询成功", list);
    }

    @ApiModelProperty("玩家报表-日期明细")
    @PostMapping("/player/day/List")
    public AjaxResult getPlayerDayReportList(@RequestBody PlayerReportParam param) {
        List<PlayerDayReportVO> list = dataAnalysisService.getPlayerDayReportList(param);
        return AjaxResult.success("查询成功", list);
    }

}

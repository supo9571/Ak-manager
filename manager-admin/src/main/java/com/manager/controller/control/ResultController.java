package com.manager.controller.control;

import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.utils.DateUtils;
import com.manager.system.service.ResultService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/10/13
 * 游戏库存策略 执行结果
 */
@RestController
@Api(tags = "库存执行结果")
@RequestMapping("/control/result")
@Slf4j
public class ResultController extends BaseController {

    @Autowired
    private ResultService resultService;

    /**
     * 查询游戏库存执行结果
     */
    @PreAuthorize("@ss.hasPermi('control:result:game')")
    @ApiOperation(value = "查询游戏库存执行结果")
    @PostMapping("/game")
    public AjaxResult getGameResult(@RequestParam(defaultValue = "0") int tid,@RequestParam(defaultValue = "0") int strategyId, String day) {
        if(StringUtils.isBlank(day)){
            day = DateUtils.getDate();
        }
        return AjaxResult.success(resultService.getGameResult(tid,strategyId,day));
    }

    /**
     * 查询个人库存执行结果
     */
    @PreAuthorize("@ss.hasPermi('control:result:person')")
    @ApiOperation(value = "查询个人库存执行结果")
    @PostMapping("/person")
    public AjaxResult getPersonResult(@RequestParam(defaultValue = "0") int tid,@RequestParam(defaultValue = "0") int strategyId,
                                      @RequestParam(defaultValue = "0") int uid, String day) {
        startPage();
        if(StringUtils.isBlank(day)){
            day = DateUtils.getDate();
        }
        List list = resultService.getPersonResult(tid,strategyId,uid,day);
        Map count = resultService.getPersonResultCount(tid,strategyId,uid,day);
        Map result = new HashMap();
        result.put("list", getDataTable(list));
        result.put("count", count);
        return AjaxResult.success(result);
    }

    /**
     * 查询个人库存执行结果 详情
     */
    @PreAuthorize("@ss.hasPermi('control:result:person')")
    @ApiOperation(value = "查询个人库存执行结果详情")
    @PostMapping("/personInfo")
    public AjaxResult getPersonResultInfo(int uid,String strategyFlag,String day) {
        if(StringUtils.isBlank(day)){
            day = DateUtils.getDate();
        }
        return AjaxResult.success(resultService.getPersonResultInfo(uid,strategyFlag,day));
    }
}

package com.manager.controller.data;

import com.manager.common.core.controller.BaseController;
import com.manager.common.core.domain.AjaxResult;
import com.manager.openFegin.AgentService;
import com.manager.system.service.PlayerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author marvin 2021/9/29
 */
@RestController
@Api(tags = "代理查询")
@RequestMapping("/data/agent")
public class AgentController extends BaseController {

    @Autowired
    private AgentService agentService;

    @Autowired
    private PlayerService playerService;
    /**
     * 获取代理 列表
     */
    @PreAuthorize("@ss.hasPermi('data:agent:list')")
    @ApiOperation(value = "查询代理列表")
    @GetMapping("/list")
    public AjaxResult list(Integer tid, String uid, String agentId, Integer page, Integer size, String orderByColumn, String isAsc) {
        return agentService.getAgents(tid, uid, agentId, page, size, orderByColumn, isAsc);
    }

    /**
     * 每日收益
     */
    @PreAuthorize("@ss.hasPermi('data:agent:list')")
    @ApiOperation(value = "每日收益")
    @GetMapping("/day")
    public AjaxResult day(String uid, Integer page, Integer size, String orderByColumn, String isAsc) {
        return agentService.getCommissionDays(uid, page, size, orderByColumn, isAsc);
    }

    /**
     * 领取记录
     */
    @PreAuthorize("@ss.hasPermi('data:agent:list')")
    @ApiOperation(value = "领取记录")
    @GetMapping("/cash")
    public AjaxResult cash(String uid, Integer page, Integer size, String orderByColumn, String isAsc) {
        return agentService.getCashs(uid, page, size, orderByColumn, isAsc);
    }

    /**
     * 推广记录
     */
    @PreAuthorize("@ss.hasPermi('data:agent:list')")
    @ApiOperation(value = "推广记录")
    @GetMapping("/popularize")
    public AjaxResult popularize(String uid) {
        startPage();
        return AjaxResult.success("查询成功",getDataTable(playerService.getPopularizes(uid)));
    }
}

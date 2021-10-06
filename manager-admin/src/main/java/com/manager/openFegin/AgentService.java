package com.manager.openFegin;

import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.Summarize;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author marvin 2021/9/29
 */
@FeignClient(value = "data-manager")
public interface AgentService {

    @PostMapping(value = "/data/agent/list")
    AjaxResult getAgents(@RequestParam("tid") Integer tid, @RequestParam("uid") String uid, @RequestParam("agentId") String agentId, @RequestParam("page") Integer page,
                         @RequestParam("size") Integer size, @RequestParam("orderByColumn") String orderByColumn, @RequestParam("isAsc") String isAsc);


    @PostMapping(value = "/data/total/list", consumes = "application/json")
    AjaxResult getTotals(@RequestBody Summarize summarize);
}

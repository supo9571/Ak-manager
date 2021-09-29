package com.data.controller;

import com.data.service.AgentServive;
import com.manager.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author marvin 2021/9/29
 * 分佣
 */
@RestController
@RequestMapping("/data/agent")
public class AgentController extends BaseController{

    @Autowired
    private AgentServive agentServive;

    /**
     * 代理查询
     */
    @PostMapping("/list")
    public AjaxResult list(Integer tid,String uid,Integer page,Integer size,String orderByColumn,String isAsc) {
        startPage(page,size,orderByColumn,isAsc);
        List list = agentServive.getCommissionList(tid,uid);
        return AjaxResult.success("查询成功",getDataTable(list));
    }
}

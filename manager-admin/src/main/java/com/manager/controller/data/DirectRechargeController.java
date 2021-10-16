package com.manager.controller.data;

import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.AddUser;
import com.manager.common.core.domain.model.DirectRecharge;
import com.manager.common.utils.SecurityUtils;
import com.manager.common.utils.file.FileUtils;
import com.manager.common.utils.poi.ExcelUtil;
import com.manager.openFegin.DataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 直属玩家充值报表
 * @author sieGuang 2021/10/016
 */
@RestController
@Api(tags = "直属玩家充值报表")
@RequestMapping("/data/directRecharge")
public class DirectRechargeController {

    @Autowired
    private DataService dataService;


    /**
     * 查询直属玩家充值
     */
    @PreAuthorize("@ss.hasPermi('data:addUser:list')")
    @ApiOperation(value = "查询直属玩家充值")
    @PostMapping("/list")
    public AjaxResult getDirectRecharge(@RequestBody DirectRecharge directRecharge) {
        return dataService.getDirectRecharge(directRecharge);
    }

    /**
     * 查询每日明细
     */
    @PreAuthorize("@ss.hasPermi('data:addUser:list')")
    @ApiOperation(value = "查询每日明细")
    @PostMapping("/subList")
    public AjaxResult getSubDirectRecharge(@RequestBody DirectRecharge directRecharge) {
        return dataService.getSubDirectRecharge(directRecharge);
    }



}

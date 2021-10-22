package com.manager.controller.data;

import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.AddUser;
import com.manager.common.core.domain.model.OnlineDataDay;
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
 * 实时在线数据
 * @author sieGuang 2021/10/21
 */
@RestController
@Api(tags = "实时在线数据")
@RequestMapping("/data/onlineDataDay")
public class OnlineDataDayController {

    @Autowired
    private DataService dataService;


    @PreAuthorize("@ss.hasPermi('data:onlineDataDay:list')")
    @ApiOperation(value = "查询在线玩家数据")
    @PostMapping("/getOnlineUserData")
    public AjaxResult getOnlineUserData(@RequestBody OnlineDataDay onlineDataDay) {
        return dataService.getOnlineUserData(onlineDataDay);
    }

    @PreAuthorize("@ss.hasPermi('data:onlineDataDay:list')")
    @ApiOperation(value = "查询PCU周期数据")
    @PostMapping("/getPcuData")
    public AjaxResult getPcuData(@RequestBody OnlineDataDay onlineDataDay) {
        return dataService.getPcuData(onlineDataDay);
    }

    @PreAuthorize("@ss.hasPermi('data:onlineDataDay:list')")
    @ApiOperation(value = "查询在线人数")
    @PostMapping("/getOnlineUserNum")
    public AjaxResult getOnlineUserNum(@RequestBody OnlineDataDay onlineDataDay) {
        return dataService.getOnlineUserNum(onlineDataDay);
    }



}

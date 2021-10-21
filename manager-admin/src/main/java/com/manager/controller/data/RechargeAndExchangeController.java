package com.manager.controller.data;

import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.AddUser;
import com.manager.common.core.domain.model.RechargeAndExchange;
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
 * 充值和提现统计
 * @author sieGuang 2021/10/19
 */
@RestController
@Api(tags = "充值和提现统计")
@RequestMapping("/data/rechargeAndExchange")
public class RechargeAndExchangeController {

    @Autowired
    private DataService dataService;

    /**
     * 查询充值和提现统计
     */
    @PreAuthorize("@ss.hasPermi('data:rechargeAndExchange:list')")
    @ApiOperation(value = "查询充值和提现统计")
    @PostMapping("/list")
    public AjaxResult getRechargeAndExchange(@RequestBody RechargeAndExchange rechargeAndExchange) {
        rechargeAndExchange.setTid2(SecurityUtils.getUserId());
        return dataService.getRechargeAndExchange(rechargeAndExchange);
    }

    @PreAuthorize("@ss.hasPermi('data:rechargeAndExchange:list')")
    @ApiOperation(value = "充值和提现统计导出")
    @PostMapping("/export")
    public void export(@RequestBody RechargeAndExchange rechargeAndExchange, HttpServletResponse response) throws IOException {
        rechargeAndExchange.setTid2(SecurityUtils.getUserId());
        List list = (List) dataService.getRechargeAndExchange(rechargeAndExchange).get("data");
        ExcelUtil<RechargeAndExchange> util = new ExcelUtil(RechargeAndExchange.class);
        String fileName = "充值和提现统计导出";
        response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
        FileUtils.setFileName(response, fileName + ".xlsx");
        util.downloadExcel(list, fileName, response.getOutputStream());
    }

}

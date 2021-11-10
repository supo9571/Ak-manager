package com.data.controller;

import com.data.service.ActivityReportService;
import com.manager.common.core.domain.AjaxResult;
import com.manager.common.core.domain.model.Coins;
import com.manager.common.core.domain.model.vo.ActivityReportVO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author jason
 * @date 2021-10-07
 */
@RestController
@RequestMapping("/data/activity/report")
public class ActivityReportController extends BaseController {

    @Resource
    private ActivityReportService activityReportService;

    /**
     * 获取账变记录列表
     */
    @PostMapping("/list")
    public AjaxResult list(@RequestBody Coins coins) {
        List<ActivityReportVO> list = activityReportService.selectActivityList(coins);
        list.forEach(obj->{
            if(obj.getAmount().compareTo(BigDecimal.ZERO)>0){
                obj.setAmount(obj.getAmount().divide(new BigDecimal(10000),2,BigDecimal.ROUND_HALF_UP));
            }
        });
        return AjaxResult.success("查询成功", list);
    }

}

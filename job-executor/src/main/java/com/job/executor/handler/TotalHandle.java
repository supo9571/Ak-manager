package com.job.executor.handler;

import com.job.core.handler.annotation.XxlJob;
import com.job.executor.service.OrderService;
import com.job.executor.service.TotalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

@Component
@Slf4j
public class TotalHandle {

    @Autowired
    private TotalService totalService;

    @Autowired
    private OrderService orderService;
    /**
     * 计算 总览
     */
    @XxlJob("total")
    public void total(){
        //获取开始时间
        long startTime = System.currentTimeMillis();
        //获取总览最后计算时间
        long orderTime = totalService.getOrderTime();
        //获取新增充值总额
        Map map = orderService.getNewRecharge(orderTime);
        BigDecimal addMoney = (BigDecimal) map.get("addMoney");
        long time = (long) map.get("orderTime");
        if(addMoney.compareTo(new BigDecimal(0))>0){
            //更新 总充值金额 total_recharge
            totalService.updateTotalRecharge(addMoney,time);
        }
        //获取结束时间
        long endTime = System.currentTimeMillis();
        log.info("total 运行时间：{}",(endTime - startTime) + "ms");
    }
}

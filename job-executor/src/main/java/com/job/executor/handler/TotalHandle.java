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
        Map timeMap = totalService.getTimes();
        //获取新增充值总额
        Map orderMap = orderService.getNewRecharge((String) timeMap.get("orderTime"));
        //获取新增提现总额
        Map mapWithdraw = orderService.getNewWithdraw((String) timeMap.get("withdrawTime"));

        BigDecimal addOrderMoney = orderMap==null ? new BigDecimal(0) : (BigDecimal) orderMap.get("addOrderMoney");
        long orderTime = orderMap==null ? Long.valueOf((String) timeMap.get("orderTime")) : (long) orderMap.get("orderTime");

        BigDecimal addWithdrawMoney = mapWithdraw==null ? new BigDecimal(0) : (BigDecimal) mapWithdraw.get("addWithdrawMoney");
        long withdrawTime =mapWithdraw==null ? Long.valueOf((String) timeMap.get("withdrawTime")) : (long) mapWithdraw.get("withdrawTime");

        if(addOrderMoney.compareTo(new BigDecimal(0))>0 || addWithdrawMoney.compareTo(new BigDecimal(0))>0){
            //更新 总充值金额 total_recharge，总提现金额 total_withdraw
            totalService.updateTotal(addOrderMoney,orderTime,addWithdrawMoney,withdrawTime);
        }
        //获取结束时间
        long endTime = System.currentTimeMillis();
        log.info("total 运行时间：{}",(endTime - startTime) + "ms");
    }
}

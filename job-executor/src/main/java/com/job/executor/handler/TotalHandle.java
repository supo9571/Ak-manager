package com.job.executor.handler;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.job.core.handler.annotation.XxlJob;
import com.job.core.util.HttpUtils;
import com.job.executor.config.GlobalConfig;
import com.job.executor.service.OrderService;
import com.job.executor.service.TotalService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 用于统计 总览数据
 */
@Component
@Slf4j
public class TotalHandle {

    @Autowired
    private TotalService totalService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private GlobalConfig globaConfig;

    /**
     * 计算 总览
     */
    @XxlJob("total")
    public void total() {
        //获取开始时间
        long startTime = System.currentTimeMillis();
        //获取总览最后计算时间
        Map timeMap = totalService.getTimes();
        //获取新增充值总额
        Map orderMap = orderService.getNewRecharge((String) timeMap.get("orderTime"));
        //获取新增提现总额
        Map mapWithdraw = orderService.getNewWithdraw((String) timeMap.get("withdrawTime"));

        BigDecimal addOrderMoney = orderMap == null ? new BigDecimal(0) : (BigDecimal) orderMap.get("addOrderMoney");
        long orderTime = orderMap == null ? Long.valueOf((String) timeMap.get("orderTime")) : (long) orderMap.get("orderTime");

        BigDecimal addWithdrawMoney = mapWithdraw == null ? new BigDecimal(0) : (BigDecimal) mapWithdraw.get("addWithdrawMoney");
        long withdrawTime = mapWithdraw == null ? Long.valueOf((String) timeMap.get("withdrawTime")) : (long) mapWithdraw.get("withdrawTime");

        if (addOrderMoney.compareTo(new BigDecimal(0)) > 0 || addWithdrawMoney.compareTo(new BigDecimal(0)) > 0) {
            //更新 总充值金额 total_recharge，总提现金额 total_withdraw
            totalService.updateTotal(addOrderMoney, orderTime, addWithdrawMoney, withdrawTime);
        }
        //获取结束时间
        long endTime = System.currentTimeMillis();
        log.info("total 运行时间：{}", (endTime - startTime) + "ms");
    }


    /**
     * 在线玩家人数
     */
    @XxlJob("online_play")
    public void onlinePlay() {
        String domain = globaConfig.getDomain();
        String onlinePlay = globaConfig.getOnlinePlay();
        String result = HttpUtils.sendPost(domain + onlinePlay, null);
        JSONObject jsonObject = JSONObject.parseObject(result);
        if ("0".equals(jsonObject.getString("code"))) {
            //更新玩家在线人数
            JSONArray jsonArray = jsonObject.getJSONArray("play_info_list");
            totalService.updateOnlinePlay(jsonArray.size());
            log.info("玩家在线人数更新--->{}", jsonArray.size());
        } else {
            log.error(result);
        }

    }
}

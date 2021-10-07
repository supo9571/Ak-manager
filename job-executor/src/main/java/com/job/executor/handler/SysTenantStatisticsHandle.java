//package com.job.executor.handler;
//
//import com.job.core.handler.annotation.XxlJob;
//import com.job.core.util.DateUtil;
//import com.job.executor.domain.SysTenantChannelStatistics;
//import com.job.executor.domain.SysTenantChannelStatisticsDay;
//import com.job.executor.domain.SysTenantStatistics;
//import com.job.executor.mapper.SysTenantChannelStatisticsDayMapper;
//import com.job.executor.mapper.SysTenantChannelStatisticsMapper;
//import com.job.executor.mapper.SysTenantStatisticsMapper;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Component;
//import org.springframework.util.CollectionUtils;
//
//import javax.annotation.Resource;
//import java.math.BigDecimal;
//import java.util.*;
//
///**
// * 平台 总代 渠道汇总
// *
// * @author jason
// * @date 2021-10-01
// */
//@Component
//@Slf4j
//public class SysTenantStatisticsHandle {
//
//    @Resource
//    private SysTenantChannelStatisticsDayMapper channelStatisticsDayMapper;
//    @Resource
//    private SysTenantChannelStatisticsMapper channelStatisticsMapper;
//    @Resource
//    private SysTenantStatisticsMapper tenantStatisticsMapper;
//    @Resource
//    private RedisCache redisCache;
//
//    String NEXT_DATE_KEY = "date_hour_key";
//
//    public static void main(String[] args) {
//        String dateHour = DateUtil.formatDateHour(new Date(), DateUtil.DATE_FORMAT_HOUR);
//        String date = DateUtil.formatDateHour(new Date(), DateUtil.DATE_FORMAT_HOUR_MINUTE) + ":00";
//        String datetime = "2021-10-02 21:57:00";
//        String next = DateUtil.formatDateHour(DateUtil.addMinutes(DateUtil.parseDateTime(date), 5), DateUtil.DATETIME_FORMAT);
//        System.out.printf("next=" + next + ",  dateHour=" + DateUtil.addMinutes(DateUtil.parseDateTime(datetime), 5) + "dateMinute==" + date);
//        System.out.printf("getStartTime==" + DateUtil.formatDateTime(DateUtil.getStartTime()));
//        System.out.printf("getEndTime==" + DateUtil.formatDateTime(DateUtil.getEndTime()));
//
//    }
//
//    /**
//     * 每分钟执行增加坐标5分钟时间
//     *
//     * @return
//     */
//    private String getNextDataHour() {
//        Object object = redisCache.getCacheObject(NEXT_DATE_KEY);
//        if (Objects.isNull(object)) {
//            // 第一次存储时间
//            String date = DateUtil.formatDateHour(new Date(), DateUtil.DATE_FORMAT_HOUR_MINUTE).concat(":00");
//            redisCache.setCacheObject(NEXT_DATE_KEY, date);
//            return date;
//        }
//        return redisCache.getCacheObject(NEXT_DATE_KEY);
//    }
//
//    /**
//     * 5分钟统计一次数据
//     * 定时处理总代渠道汇总数据
//     */
//    @XxlJob("tenant_statistics")
//    public void tenantStatistics() {
//        List<Map<String, Object>> sysTenantList = tenantStatisticsMapper.getSysTenantList();
//        if (CollectionUtils.isEmpty(sysTenantList)) {
//            log.info("sysTenantList is empty!");
//            return;
//        }
//
//        try {
//            String getDateTime = getNextDataHour();
//            String nextDateTime = DateUtil.formatDateHour(DateUtil.addMinutes(DateUtil.parseDateTime(getDateTime), 5), DateUtil.DATETIME_FORMAT);
//
//            //开始时间 结束时间 当天 00:00:00 ~ 23:59:59
//            String beginTime = DateUtil.formatDateTime(DateUtil.getStartTime());
//            String endTime = DateUtil.formatDateTime(DateUtil.getEndTime());
//
//            log.info("当前正在 开始时间：{} 下一次时间：{}", beginTime, endTime);
//
//            List<SysTenantStatistics> tenantStatisticsList = new ArrayList<>();
//            List<SysTenantChannelStatisticsDay> channelStatisticsDayList = new ArrayList<>();
//            List<SysTenantChannelStatistics> channelStatisticsList = new ArrayList<>();
//            sysTenantList.forEach(map -> {
//
//                String tid = String.valueOf(map.get("tid"));
//                Integer tType = Integer.parseInt(map.get("tType").toString());
//                List<Long> userIds = tenantStatisticsMapper.getUserByChannelIdList(tid);
//                // 获取总充值人数
//                Map<String, Object> getRechargeAmount = tenantStatisticsMapper.getRechargeAmount(tid, beginTime, endTime);
//                // 获取提现金额
//                Map<String, Object> geWithdrawAmount = tenantStatisticsMapper.geWithdrawAmount(tid, beginTime, endTime);
//                // 获取线上金额
//                Map<String, BigDecimal> getUpAwardAmount = tenantStatisticsMapper.getLowerAwardAmount(tid, beginTime, endTime);
//                // 获取线下金额
//                Map<String, BigDecimal> getLowerAwardAmount = tenantStatisticsMapper.getLowerAwardAmount(tid, beginTime, endTime);
//                // 活跃人数
//                int getUserLoginCount = tenantStatisticsMapper.getUserLoginCount(userIds, beginTime, endTime);
//                // 收充人数
//                Map<String, Object> getFirstRechargeAmount = tenantStatisticsMapper.getFirstRechargeAmount(tid, beginTime, endTime);
//
//                // 统计表
//                SysTenantStatistics statistics = new SysTenantStatistics();
//                statistics.setTId(tid);
//                statistics.setRechargeNum(Integer.parseInt(String.valueOf(getRechargeAmount.get("count"))));
//                statistics.setRechargeAmount(new BigDecimal(String.valueOf(getRechargeAmount.get("amount"))));
//                statistics.setRechargeNum(Integer.parseInt(String.valueOf(geWithdrawAmount.get("count"))));
//                statistics.setWithdrawAmount(new BigDecimal(String.valueOf(geWithdrawAmount.get("amount"))));
//                statistics.setUpAwardAmount(getUpAwardAmount.get("amount"));
//                statistics.setLowerAwardAmount(getLowerAwardAmount.get("amount"));
//                tenantStatisticsList.add(statistics);
//
//                // 汇总总代渠道日
//                SysTenantChannelStatisticsDay statisticsDay = new SysTenantChannelStatisticsDay();
//                statisticsDay.setTId(tid);
//                statisticsDay.setDay(DateUtil.formatDate(new Date()));
//                statisticsDay.setRechargeNum(Integer.parseInt(String.valueOf(getRechargeAmount.get("count"))));
//                statisticsDay.setRechargeAmount(new BigDecimal(String.valueOf(getRechargeAmount.get("amount"))));
//                statisticsDay.setRechargeNum(Integer.parseInt(String.valueOf(geWithdrawAmount.get("count"))));
//                statisticsDay.setWithdrawAmount(new BigDecimal(String.valueOf(geWithdrawAmount.get("amount"))));
//                statisticsDay.setRechargeFirst(Integer.parseInt(String.valueOf(getFirstRechargeAmount.get("count"))));
//                statisticsDay.setRechargeAmountFirst(new BigDecimal(String.valueOf(getFirstRechargeAmount.get("amount"))));
//                statisticsDay.setActiveNum(getUserLoginCount);
//                channelStatisticsDayList.add(statisticsDay);
//
//                // 汇总精确小时
//                String dateHour = DateUtil.formatDateHour(new Date(), DateUtil.DATE_FORMAT_HOUR) + ":00:00";
//                SysTenantChannelStatistics channelStatistics = new SysTenantChannelStatistics();
//                channelStatistics.setTId(tid);
//                channelStatistics.setDay(dateHour);
//                channelStatistics.setRechargeNum(Integer.parseInt(String.valueOf(getRechargeAmount.get("count"))));
//                channelStatistics.setRechargeAmount(new BigDecimal(String.valueOf(getRechargeAmount.get("amount"))));
//                channelStatistics.setRechargeNum(Integer.parseInt(String.valueOf(geWithdrawAmount.get("count"))));
//                channelStatistics.setWithdrawAmount(new BigDecimal(String.valueOf(geWithdrawAmount.get("amount"))));
//                channelStatistics.setRechargeFirst(Integer.parseInt(String.valueOf(getFirstRechargeAmount.get("count"))));
//                channelStatistics.setRechargeAmountFirst(new BigDecimal(String.valueOf(getFirstRechargeAmount.get("amount"))));
//                statisticsDay.setActiveNum(getUserLoginCount);
//                channelStatisticsList.add(channelStatistics);
//            });
//            // 汇总表
//            tenantStatisticsMapper.saveSysTenantStatistics(tenantStatisticsList);
//            // 汇总-天
//            channelStatisticsDayMapper.saveChannelStatisticsDay(channelStatisticsDayList);
//            // 汇总-小s
//            channelStatisticsMapper.saveChannelStatistics(channelStatisticsList);
//            redisCache.setCacheObject(NEXT_DATE_KEY, nextDateTime);
//        } catch (Exception e) {
//            log.error("===tenantStatistics==> 执行定job异常：{}", e.getMessage());
//        }
//    }
//
//}

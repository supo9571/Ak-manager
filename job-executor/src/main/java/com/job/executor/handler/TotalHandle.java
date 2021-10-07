package com.job.executor.handler;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.job.core.handler.annotation.XxlJob;
import com.job.core.util.DateUtil;
import com.job.core.util.HttpUtils;
import com.job.executor.config.GlobalConfig;
import com.job.executor.domain.OnlinePlayer;
import com.job.executor.domain.Summarize;
import com.job.executor.mapper.TotalMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
@Slf4j
public class TotalHandle {

    @Autowired
    private GlobalConfig globaConfig;


    @Autowired
    private TotalMapper totalMapper;

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
            try {
                JSONArray jsonArray = jsonObject.getJSONArray("play_info_list");
                //清空 data_online
                totalMapper.cleanOnline();
                List list = new ArrayList();
                jsonArray.forEach(j -> {
                    list.add(JSONObject.toJavaObject((JSON) j, OnlinePlayer.class));
                });
                totalMapper.insertOnline(list);
                log.info("玩家在线人数更新--->{}", jsonArray.size());
            } catch (Exception e) {
                log.error("在线玩家人数出错：{}", e.getMessage());
            }
        } else {
            log.error(result);
        }
    }

    /**
     * 今日登录统计
     */
//    @PostConstruct
    @XxlJob("login_count")
    public void login() {
        String date = new SimpleDateFormat("yyyy/MM/dd").format(new Date());
        Long time = DateUtil.getTodayTimes();
        int num = totalMapper.selectTodayLogins(time);
        totalMapper.saveTodayLogins(date, num);
    }

    /**
     * 重置 今日数据
     * 清理 流水表
     */
    @XxlJob("reset_today")
    public void resetToday() {
        Long time = DateUtil.getTodayTimes() - (60 * 60 * 24 * 3);//三天前时间戳
        totalMapper.updateRegister();
        totalMapper.deleteWater(time);
    }

    /**
     * 计算 总览
     */
    @XxlJob("summarize_today")
    public void summarize() {
        String date = DateUtil.formatDate(new Date());//当天日期
        Long time = DateUtil.getTodayTimes();//当天零点 时间戳
        List summarizeList = new ArrayList();
        //查询 渠道列表
        List<String> channelList = totalMapper.getChannelList();
        channelList.forEach(channel->{
            try {
                Summarize summarize = new Summarize();
                summarize.setDay(date);
                summarize.setChannel(channel);
                //设置 充值数据
                setRechargeInfo(summarize,channel,time);
                //设置 提现数据
                setExchangeInfo(summarize,channel,time);
                //设置 新增玩家 当日余额 活跃玩家
                setNewPlayerInfo(summarize,channel,time);
                //设置 流水 派奖
                setWaterInfo(summarize,channel,time);
                //设置 代理 直属返佣 团队返佣
                setCommissionInfo(summarize,channel,date);
                //设置 投注金额 游戏税收 赢家人数占比
                setBetInfo(summarize,channel,time);
                //设置 赠送 奖励 游戏输赢
                setGiveInfo(summarize,channel,time);
                summarizeList.add(summarize);
            }catch (Exception e){
                log.error("计算总览出错，channel：{},msg:{}",channel,e.getMessage());
            }

        });
        if (summarizeList.size() > 0)
            totalMapper.saveSummarize(summarizeList);
    }

    /**
     * 总览 充值相关数据
     */
    private void setRechargeInfo(Summarize summarize,String channel,Long time){
        Map rechargeMap = totalMapper.getRechargeInfo(channel, time);
        Long sucessNum = (Long) rechargeMap.get("successNum");
        Long rechargeNum = (Long) rechargeMap.get("rechargeNum");
        double onlinePayRate = sucessNum == 0 ? 0 : rechargeNum / sucessNum;
        summarize.setOnlinePayRate(onlinePayRate);
        BigDecimal successCount = checkDecimal((BigDecimal) rechargeMap.get("successCount"));
        BigDecimal rechargeCount = (BigDecimal) rechargeMap.get("rechargeCount");
        double offlinePayRate = rechargeCount == null ? 0 : successCount.divide(rechargeCount).doubleValue();
        summarize.setOfflinePayRate(offlinePayRate);
        summarize.setRechargeNum(rechargeNum);
        summarize.setRechargeCount(checkDecimal(rechargeCount));
        summarize.setNewRechargeNum((Long) rechargeMap.get("newRechargeNum"));
        summarize.setNewRechargeCount(checkDecimal((BigDecimal) rechargeMap.get("newRechargeCount")));
        summarize.setRechargeGiveCount(checkDecimal((BigDecimal) rechargeMap.get("rechargeGiveCount")));
        summarize.setOfflineGiveCount(checkDecimal((BigDecimal) rechargeMap.get("offlineGiveCount")));
    }

    /**
     * 总览 提现相关数据
     */
    private void setExchangeInfo(Summarize summarize,String channel,Long time){
        Map exchangeMap = totalMapper.getExchangeInfo(channel, time);
        summarize.setExchangeNum((Long) exchangeMap.get("exchangeNum"));
        summarize.setExchangeCount(checkDecimal((BigDecimal) exchangeMap.get("exchangeCount")));
        summarize.setActualExchangeCount(checkDecimal((BigDecimal) exchangeMap.get("actualExchangeCount")));
        summarize.setConfiscateCount(checkDecimal((BigDecimal) exchangeMap.get("confiscateCount")));
        summarize.setNewExchangeCount(checkDecimal((BigDecimal) exchangeMap.get("newExchangeCount")));
    }

    /**
     * 总览 新增玩家 当日余额 活跃玩家
     */
    private void setNewPlayerInfo(Summarize summarize,String channel,Long time){
        Map newPlayerInfoMap = totalMapper.getNewPlayerInfo(channel,time);
        summarize.setNewNum((Long) newPlayerInfoMap.get("newNum"));
        BigDecimal balanceCount = checkDecimal((BigDecimal) newPlayerInfoMap.get("balanceCount"));
        summarize.setBalanceCount(balanceCount.divide(new BigDecimal(10000)));
        summarize.setActiveNum(totalMapper.getActiveNum(channel,time));
    }

    /**
     * 总览 新增充值玩家流水  返奖金额 游戏业绩
     */
    private void setWaterInfo(Summarize summarize, String channel, Long time) {
        Map waterInfoMap = totalMapper.getWaterInfo(channel,time);
        if(waterInfoMap!=null){
            BigDecimal b = new BigDecimal(10000);
            BigDecimal newWater = checkDecimal((BigDecimal) waterInfoMap.get("newWater"));
            BigDecimal rewardCount = checkDecimal((BigDecimal) waterInfoMap.get("rewardCount"));
            BigDecimal performanceCount = checkDecimal((BigDecimal) waterInfoMap.get("performanceCount"));
            summarize.setNewWater(newWater.divide(b));
            summarize.setRewardCount(rewardCount.divide(b));
            summarize.setPerformanceCount(performanceCount.divide(b));
        }
    }

    /**
     * 总览 代理 直属返佣 团队返佣
     */
    private void setCommissionInfo(Summarize summarize, String channel, String date) {
        Map CommissionInfoMap = totalMapper.getCommissionInfo(channel,date);
        if(CommissionInfoMap!=null){
            summarize.setCommissionSubCount(checkDecimal((BigDecimal) CommissionInfoMap.get("commissionSubCount")));
            summarize.setCommissionTeamCount(checkDecimal((BigDecimal) CommissionInfoMap.get("commissionTeamCount")));
        }
    }
    /**
     * 总览 投注金额 游戏税收 赢家人数占比
     */
    private void setBetInfo(Summarize summarize, String channel, Long time) {
        Long endtime = DateUtil.getTodayTimes() + (60 * 60 * 24);
        Map betInfoMap = totalMapper.getBetInfo(channel,time+"000",endtime+"000");
        if(betInfoMap!=null){
            BigDecimal b = new BigDecimal(10000);
            BigDecimal betCount = checkDecimal((BigDecimal) betInfoMap.get("betCount"));
            BigDecimal feeCount = checkDecimal((BigDecimal) betInfoMap.get("feeCount"));
            summarize.setBetCount(betCount.divide(b));
            summarize.setFeeCount(feeCount.divide(b));
        }
        Integer winNum = totalMapper.getWinNum(channel);
        Integer totalNum = totalMapper.getTotalNum(channel,time+"000",endtime+"000");
        double winRate = totalNum==0?0:winNum/totalNum;
        summarize.setWinRate(winRate);
    }

    /**
     * 总览 投注金额 游戏税收 赢家人数占比
     */
    private void setGiveInfo(Summarize summarize, String channel, Long time) {
        String beginTime = time + "000";
        String endTime = System.currentTimeMillis() + "";
        BigDecimal b = new BigDecimal(10000);

        BigDecimal activityCount = checkDecimal(totalMapper.getActivityCount(channel, beginTime, endTime));
        BigDecimal bindGiveCount = checkDecimal(totalMapper.getBindGiveCount(channel, beginTime, endTime));
        BigDecimal vipLevelCount = checkDecimal(totalMapper.getVipLevelCount(channel, beginTime, endTime));
        BigDecimal vipCount = checkDecimal(totalMapper.getVipCount(channel, beginTime, endTime));
        BigDecimal almsCount = checkDecimal(totalMapper.getAlmsCount(channel, beginTime, endTime));

        summarize.setActivityCount(activityCount.divide(b));
        summarize.setBindGiveCount(bindGiveCount.divide(b));
        summarize.setVipLevelCount(vipLevelCount.divide(b));
        summarize.setVipCount(vipCount.divide(b));
        summarize.setAlmsCount(almsCount.divide(b));

        Map map = totalMapper.getGiveCount(channel, beginTime, endTime);
        Long giveNum = (Long) map.get("giveNum");
        BigDecimal giveCount = checkDecimal((BigDecimal) map.get("giveCount"));
        summarize.setGiveNum(giveNum);
        summarize.setGiveCount(giveCount.divide(b));

        //昨日账户余额
        String yesterday = DateUtil.formatDate(DateUtil.addDays(new Date(),1));//当天日期
        BigDecimal yesterdayBalance = checkDecimal(totalMapper.getYertodayBalance(channel, yesterday));
        BigDecimal rechargeCount = checkDecimal(summarize.getRechargeCount());
        BigDecimal exchangeCount = checkDecimal(summarize.getExchangeCount());
        BigDecimal balanceCount = checkDecimal(summarize.getBalanceCount());
        BigDecimal systemWin = rechargeCount.subtract(exchangeCount).add(yesterdayBalance).subtract(balanceCount);
        summarize.setSystemWin(systemWin);
    }

    private BigDecimal checkDecimal(BigDecimal bigDecimal){
        return bigDecimal==null?new BigDecimal(0):bigDecimal;
    }
}

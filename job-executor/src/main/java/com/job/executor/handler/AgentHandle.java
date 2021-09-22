package com.job.executor.handler;

import com.job.core.handler.annotation.XxlJob;
import com.job.core.util.DateUtil;
import com.job.executor.domain.AgentCommission;
import com.job.executor.mapper.AgentMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author marvin 2021/9/17
 * 计算 代理 返佣
 */
@Component
@Slf4j
public class AgentHandle {

    @Autowired
    private AgentMapper agentMapper;

    /**
     * 计算 每日 代理分佣
     */
    @XxlJob("agent_day_income")
    @PostConstruct
    public void dayIncome() {
        String date = DateUtil.formatDate(new Date());
        Long endTime = System.currentTimeMillis()/1000;
        //查询 今日登录 玩家id列表
        List<AgentCommission> agentCommissions = agentMapper.selectUids();
        List<AgentCommission> agents = new ArrayList<>();

        agentCommissions.forEach(agentCommission->{
            agentCommission.setDay(date);
            agentCommission.setEndTime(DateUtil.getTodayTimes());
            getAgentMoney(agentCommission,endTime);
            agents.add(agentCommission);
        });
        if(agents.size()>0)
            agentMapper.saveAgentDayIncome(agents);

    }

    /**
     * 计算 代理分佣
     */
    @XxlJob("agent_count_income")
    public void agentIncome() {
        String date = DateUtil.formatDate(DateUtil.addDays(new Date(),-1));
        List<AgentCommission> agents = agentMapper.selectDayIncome(date);
        if(agents.size()>0)
            agentMapper.saveAgentIncome(agents);
    }

    /**
     * 初始化 佣金数据
     */
    private void getAgentMoney(AgentCommission agentCommission,Long endTime){
        Long beginTime = agentCommission.getEndTime();
        Long uid = agentCommission.getUid();
        String channel = agentCommission.getChannel();

        Integer teamNum = getTeam(uid);//团队人数
        Integer subNum = agentMapper.selectSubNum(uid);//直属人数

        Long subRatio = getSubRatio(uid,beginTime,endTime) ;//直属业绩
        Long otherRatio = getOtherRatio(uid,beginTime,endTime) ;//下属业绩

        BigDecimal subRatioBig = new BigDecimal(subRatio).divide(new BigDecimal(10000));//直属业绩
        BigDecimal otherRatioBig = new BigDecimal(otherRatio).divide(new BigDecimal(10000));//下属业绩

        /**
         * 直属佣金=直属玩家总流水×此代理线的总流水对应的返佣比例
         */
        BigDecimal totalRatioBig = subRatioBig.add(otherRatioBig);//总业绩
        Integer rebate = agentMapper.selectRebate(totalRatioBig,channel);//佣金比例
        rebate = rebate==null?0:agentMapper.selectRebate(totalRatioBig,channel);

        BigDecimal subIncome = subRatioBig.multiply(new BigDecimal(rebate)).divide(new BigDecimal(10000));//直属佣金
        BigDecimal otherIncome = getOtherIncome(uid,beginTime,endTime,channel,rebate);//下属佣金

        agentCommission.setTeamNum(teamNum);
        agentCommission.setSubNum(subNum);
        agentCommission.setOtherNum(teamNum-subNum);
        agentCommission.setSubRatio(subRatioBig);
        agentCommission.setSubIncome(subIncome);
        agentCommission.setOtherRatio(otherRatioBig);
        agentCommission.setOtherIncome(otherIncome);
        agentCommission.setTotalIncome(subIncome.add(otherIncome));
        agentCommission.setEndTime(endTime);
    }

    /**
     * 计算 团队人数
     */
    private Integer getTeam(Long uid) {
       AtomicReference<Integer> i = new AtomicReference<>(0);
        List<Long> list = agentMapper.selectSubUid(uid);
        if(list!=null){
            list.forEach(id->{
                i.updateAndGet(v -> v + getTeam(id));
            });
            i.updateAndGet(v -> v + list.size());
        }
        return i.get();
    }

    /**
     * 计算 直属业绩
     */
    private Long getSubRatio(Long uid,Long beginTime,Long endTime) {
        AtomicReference<Long> i = new AtomicReference<>(0l);
        List<Long> list = agentMapper.selectSubUid(uid);
        if(list!=null){
            list.forEach(id->{
                Long ratio = agentMapper.selectSubRatio(id,beginTime,endTime);
                Long finalRatio = ratio==null?0l:ratio;
                i.updateAndGet(v -> v + finalRatio);
            });
        }
        return i.get();
    }

    /**
     * 计算 下属业绩
     */
    private Long getOtherRatio(Long uid,Long beginTime,Long endTime) {
        AtomicReference<Long> i = new AtomicReference<>(0l);
        List<Long> list = agentMapper.selectSubUid(uid);
        if(list!=null){
            list.forEach(id->{
                Long ratio = getSubRatio(id,beginTime,endTime);
                i.updateAndGet(v -> v + ratio);
            });
        }
        return i.get();
    }


    /**
     * 计算 下属佣金
     * 下属佣金 = ∑直属玩家对应的下属玩家总流水*（此代理线的总流水对应的返佣比例-直属玩家对应的下属玩家总流水对应的返佣比例）
     */
    private BigDecimal getOtherIncome(Long uid,Long beginTime,Long endTime,String channel,Integer rebate){
        AtomicReference<BigDecimal> i = new AtomicReference<>(new BigDecimal(0));
        List<Long> list = agentMapper.selectSubUid(uid);
        if(list!=null){
            list.forEach(id->{
                List<Long> ids = agentMapper.selectSubUid(id);
                if(ids != null){
                    Long subRatio = getSubRatio(id,beginTime,endTime);// 直属业绩
                    Long otherRatio = getOtherRatio(id,beginTime,endTime);//下属业绩
                    BigDecimal subRatioBig = new BigDecimal(subRatio).divide(new BigDecimal(10000));//直属业绩
                    BigDecimal otherRatioBig = new BigDecimal(otherRatio).divide(new BigDecimal(10000));//下属业绩

                    BigDecimal totalRatioBig = subRatioBig.add(otherRatioBig);//总业绩
                    Integer otherRebate = agentMapper.selectRebate(totalRatioBig,channel);//佣金比例
                    otherRebate = otherRebate==null?0:agentMapper.selectRebate(totalRatioBig,channel);

                    BigDecimal subIncome = subRatioBig.multiply(new BigDecimal(rebate-otherRebate)).divide(new BigDecimal(10000));//直属佣金
                    BigDecimal otherIncome = getOtherIncome(id,beginTime,endTime,channel,otherRebate);//下属佣金
                    i.updateAndGet(v -> v .add(subIncome).add(otherIncome));
                }
            });
        }
        return i.get();
    }
}

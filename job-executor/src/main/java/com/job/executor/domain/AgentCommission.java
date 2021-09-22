package com.job.executor.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author marvin 2021/9/17
 */
@Data
public class AgentCommission {
    /**
     * 日期
     */
    private String day;

    /**
     * 玩家id
     */
    private Long uid;

    /**
     * 上级id
     */
    private Long agentId;

    /**
     * 渠道
     */
    private String channel;

    /**
     * 团队人数
     */
    private Integer teamNum;

    /**
     * 直属人数
     */
    private Integer subNum;

    /**
     * 直属业绩
     */
    private BigDecimal subRatio;

    /**
     * 直属佣金
     */
    private BigDecimal subIncome;

    /**
     * 下属人数
     */
    private Integer otherNum;

    /**
     * 下属业绩
     */
    private BigDecimal otherRatio;

    /**
     * 下属佣金
     */
    private BigDecimal otherIncome;

    /**
     * 累计佣金
     */
    private BigDecimal totalIncome;

    /**
     * 已提佣金
     */
    private BigDecimal cashIncome;

    /**
     * 待提佣金
     */
    private BigDecimal waitIncome;

    /**
     * 历史佣金
     */
    private BigDecimal historyIncome;

    /**
     * 上次计算 截至时间
     */
    private Long endTime;

}

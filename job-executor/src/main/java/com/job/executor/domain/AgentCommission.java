package com.job.executor.domain;

import lombok.Data;

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
    private Long subRatio;

    /**
     * 直属佣金
     */
    private Long subIncome;

    /**
     * 下属人数
     */
    private Integer otherNum;

    /**
     * 下属业绩
     */
    private Long otherRatio;

    /**
     * 下属佣金
     */
    private Long otherIncome;

    /**
     * 累计佣金
     */
    private Long totalIncome;

    /**
     * 已提佣金
     */
    private Long cashIncome;

    /**
     * 待提佣金
     */
    private Long waitIncome;

    /**
     * 历史佣金
     */
    private Long historyIncome;

    /**
     * 上次计算 截至时间
     */
    private Long endTime;

}

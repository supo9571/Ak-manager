package com.job.executor.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 子游戏实时数据
 * @author sieGuang 2021/09/30
 */
@Data
public class CountSubGame {

    /**
     * 平台id
     */
    private String tid;

    /**
     * 父id
     */
    private String parentId;

    /**
     * 游戏id
     */
    private String gameId;

    /**
     * 游戏名
     */
    private String gameName;

    /**
     * 游戏状态
     */
    private String gameType;

    /**
     * 投注金额
     */
    private BigDecimal betRecharge;

    /**
     * 返奖金额
     */
    private BigDecimal rewardRecharge;

    /**
     * 税收
     */
    private BigDecimal taxRevenue;

    /**
     * 盈亏
     */
    private BigDecimal profitAndLoss;

    /**
     * 盈亏比
     */
    private BigDecimal profitAndLossRatio;

    /**
     * 参与人数
     */
    private int parCount;

    /**
     * 人局数
     */
    private int humBurCount;

    /**
     * 初始化时间
     */
    private String initTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 创建时间1
     */
    private String createTime1;

    /**
     * 创建时间2
     */
    private String createTime2;

}

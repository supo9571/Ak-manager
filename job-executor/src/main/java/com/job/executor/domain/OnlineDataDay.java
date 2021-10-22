package com.job.executor.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 实时在线数据
 * @author sieGuang 2021/10/21
 */
@Data
public class OnlineDataDay {

    /**
     * 日期
     */
    private String day;

    /**
     * 玩家id
     */
    private String uid;

    /**
     * 玩家名称
     */
    private String name;

    /**
     * 游戏名
     */
    private String gameName;

    /**
     * 游戏类型
     */
    private String gameType;

    /**
     * 游戏id
     */
    private BigDecimal tableType;

    /**
     * 渠道
     */
    private BigDecimal channel;

    /**
     * 创建时间
     */
    private String createTime;

}

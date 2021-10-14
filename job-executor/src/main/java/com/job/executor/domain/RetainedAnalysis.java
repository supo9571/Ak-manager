package com.job.executor.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 计算留存分析数据
 * @author sieGuang 2021/10/11
 */
@Data
public class RetainedAnalysis {

    /**
     * 日期
     */
    private String day;

    /**
     *  用户id
     */
    private String uids;

    /**
     *  用户数
     */
    private Integer userCount;

    /**
     * 次日留存
     */
    private String retained1;

    /**
     * 留存2
     */
    private String retained2;

    /**
     * 留存3
     */
    private String retained3;

    /**
     * 留存4
     */
    private String retained4;

    /**
     * 留存5
     */
    private String retained5;

    /**
     * 留存6
     */
    private String retained6;

    /**
     * 留存7
     */
    private String retained7;

    /**
     * 留存15
     */
    private String retained15;

    /**
     * 留存30
     */
    private String retained30;

    /**
     * 留存45
     */
    private String retained45;

    /**
     * 渠道
     */
    private String channel;

    /**
     * 状态 1.新增用户数 2.活跃留存 3.付费留存 4.新增充值留存 5.回流用户留存
     */
    private Integer type;

}

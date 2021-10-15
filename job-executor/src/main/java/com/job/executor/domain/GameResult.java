package com.job.executor.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author marvin 2021/10/15
 */
@Data
public class GameResult {

    private String day;

    private String strategyId;

    private String channel;

    private String strategyName;

    private BigDecimal addScore;

    private BigDecimal betCoins;

    private String up;

    private String down;

    private Long sha;

    private Long song;

    private Long games;

    private Long controls;

    private String endTime;
}

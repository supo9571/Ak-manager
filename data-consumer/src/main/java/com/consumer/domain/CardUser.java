package com.consumer.domain;

import lombok.Data;

/**
 * @author marvin 2021/8/26
 */
@Data
public class CardUser {

    private Long uid;

    private String tableGid;
    private Long mstime;
    private Long beginTime;
    private Long endTime;
    private int gameType;
    private int tableType;

    private Long addScore;
    private Long payFee;
    private Long betCoins;
    private Long waterCoins;
    private Long leftScore;
    private String isRobot;

    public void setCardInfo(Card card) {
        this.tableGid = card.getTableGid();
        this.mstime = card.getMstime();
        this.beginTime = card.getBeginTime();
        this.endTime = card.getEndTime();
        this.gameType = card.getGameType();
        this.tableType = card.getTableType();
    }
}

package com.consumer.domain;

import lombok.Data;

/**
 * @author marvin 2021/8/24
 */
@Data
public class Card {
    private String key;
    private String op;
    private Long time;
    private Long mstime;

    private String address;
    private Long beginTime;
    private Long endTime;

    private int gameType;
    private String tableGid;
    private int tableType;

    private String sideList;
    private String loserList;
    private String winnerList;
    private String exinfo;
    private int totalNum;
    private Long systemWin;

    private Long addScore;
    private Long payFee;
    private Long betCoins;

    private String uid;
    private int aiNum;

    private String channel;
}

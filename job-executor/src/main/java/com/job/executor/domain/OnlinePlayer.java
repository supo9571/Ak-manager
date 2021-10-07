package com.job.executor.domain;

import lombok.Data;

/**
 * @author marvin 2021/8/20
 */
@Data
public class OnlinePlayer {

    private Long uid;
    private String name;
    private int gameType;
    private int tableType;
    private Long coins;

    private String channel;

}

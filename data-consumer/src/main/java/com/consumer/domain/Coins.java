package com.consumer.domain;

import lombok.Data;

/**
 * @author marvin 2021/8/17
 */
@Data
public class Coins {

    private String key;
    private String op;
    private int r; //加钱原因
    private Long uid;
    private Long curr;//改变之后的金额
    private Long time;
    private Long value;// 改变数量
    private Long before;//之前的金额
    private Long mstime;
    private String channel;
    private String deviceId;
    private Long gameType;
    private Long tableType;
    private String curChannel;
    private String deviceBrand;
    private String clientVersion;

    private Long safeBox;//保险箱余额
}

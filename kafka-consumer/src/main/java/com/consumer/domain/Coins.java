package com.consumer.domain;

import lombok.Data;

/**
 * @author marvin 2021/8/17
 */
@Data
public class Coins {
    /**
     *    "r":2,
     *     "op":"addcoins",
     *     "key":"basesvr_1_1629010311_9",
     *     "uid":113876,
     *     "curr":11045300,
     *     "time":1629010311,
     *     "value": 2332600,
     *     "before":8712700,
     *     "mstime":1629010311017,
     *     "channel":"debug_channel",
     *     "device_id":"2A96D53293C810A9845B180E4213C9A9",
     *     "game_type":1,
     *     "table_type":100,
     *     "cur_channel":"guanwang",
     *     "device_brand":"MuMu",
     *     "client_version":"1.0.0"
     *
     *   {
     *     "r":1,
     *     "op":"reducecoins",
     *     "key":"basesvr_1_1629010284_3",
     *     "uid":113876,
     *     "curr":9864000,
     *     "time":1629010284,
     *     "value":10000,
     *     "before":9874000,
     *     "mstime":1629010284516,
     *     "channel":"debug_channel",
     *     "safe_box":0, //保险箱
     *     "device_id":"2A96D53293C810A9845B180E4213C9A9",
     *     "game_type":1,
     *     "table_type":100,
     *     "cur_channel":"guanwang",
     *     "device_brand":"MuMu",
     *     "client_version":"1.0.0"
     *  }
     *
     *
     */
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

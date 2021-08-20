package com.consumer.domain;

import lombok.Data;

/**
 * @author marvin 2021/8/20
 */
@Data
public class Login {

    private String op;

    private String key;

    private Long uid;

    private int sex;

    private String phone;

    private int r;

    private Long ip;

    private Long time;

    private int level;

    private Long mstime;

    private String channel;

    private String deviceId;

    private String nickName;

    private int vipLevel;

    private String curChannel;

    private String registerIp;

    private String deviceBrand;

    private String clientVersion;
}

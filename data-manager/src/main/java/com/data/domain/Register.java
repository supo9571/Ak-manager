package com.data.domain;

import com.data.domain.common.Params;
import lombok.Data;

/**
 * @author marvin 2021/8/17
 */
@Data
public class Register extends Params {

    private String key;
    private String op;
    private Long uid;
    private Long ip;
    private String name;
    private Long time;
    private Long mstime;
    private String channel;
    private int agentId;
    private String deviceId;
    private String curChannel;
    private String registerIp;
    private String deviceBrand;
    private String clientVersion;
    private String registerMachine;

}

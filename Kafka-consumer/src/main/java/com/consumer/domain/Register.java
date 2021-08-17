package com.consumer.domain;

import lombok.Data;

/**
 * @author marvin 2021/8/17
 */
@Data
public class Register {
    /**
     * {
     *     "ip":170328397,
     *     "op":"register",
     *     "key":"hallsvr_1_1629123391_959",
     *     "uid":113897,
     *     "name":"会员113897",
     *     "time":1629123391,
     *     "mstime":1629123391929,
     *     "channel":"10001",
     *     "agent_id":0,
     *     "device_id":"2E99D1E366351B53C56CA9E1E23F0CE3",
     *     "cur_channel":"10001",
     *     "register_ip":"10.39.1.77",
     *     "device_brand":"MuMu",
     *     "client_version":"1.1.0",
     *     "register_machine":"2E99D1E366351B53C56CA9E1E23F0CE31629123375"
     * }
     */
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

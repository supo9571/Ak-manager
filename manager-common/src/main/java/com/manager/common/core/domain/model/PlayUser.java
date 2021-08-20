package com.manager.common.core.domain.model;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author marvin 2021/8/17
 */
@Data
public class PlayUser extends BaseEntity {


    @ApiModelProperty("玩家id")
    private String uid;

    @ApiModelProperty("玩家姓名")
    private String name;

    @ApiModelProperty("注册时间")
    private String time;

    @ApiModelProperty("渠道名称")
    private String channel;

    @ApiModelProperty("上级id")
    private int agentId;

    @ApiModelProperty("设备id")
    private String deviceId;

    private String curChannel;

    @ApiModelProperty("注册ip")
    private String registerIp;

    @ApiModelProperty("设备名称")
    private String deviceBrand;

    private String clientVersion;

    @ApiModelProperty("机器码")
    private String registerMachine;

}

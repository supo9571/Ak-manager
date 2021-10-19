package com.manager.common.core.domain.model;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 直属玩家充值报表
 * @author sieGuang 2021/10/016
 */
@Data
public class DirectRecharge extends BaseEntity {

    @ApiModelProperty("平台id")
    private String tid;

    @ApiModelProperty("玩家id")
    private String uid;

    @ApiModelProperty("上级id")
    private String agentId;

    @ApiModelProperty("渠道")
    private String channel;

    @ApiModelProperty("绑定时间")
    private String agentTime;

    @ApiModelProperty("绑定时间1")
    private String agentTime1;

    @ApiModelProperty("绑定时间2")
    private String agentTime2;

    @ApiModelProperty("充值金额")
    private String rechargeAmount = "0";

    public void setRechargeAmount(String rechargeAmount) {
        if(rechargeAmount == null || rechargeAmount.length() == 0){
            this.rechargeAmount = "0";
        }else{
            this.rechargeAmount = rechargeAmount;
        }
    }

    @ApiModelProperty("充值时间1")
    private String rechargeTime1;

    @ApiModelProperty("充值时间1")
    private String rechargeTime2;

    private Integer page2;

}

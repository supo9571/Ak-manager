package com.manager.common.core.domain.model;

import com.manager.common.annotation.Excel;
import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 充值和提现统计
 * @author sieGuang 2021/10/19
 */
@Data
public class RechargeAndExchange extends BaseEntity {

    @ApiModelProperty("平台id")
    private Integer tid;

    private Long tid2;

    @Excel(name = "日期")
    @ApiModelProperty("日期")
    private String day;

    @Excel(name = "活跃人数")
    @ApiModelProperty("活跃人数")
    private String activeUser;

    @Excel(name = "新增用户")
    @ApiModelProperty("新增用户")
    private String uid;

    @Excel(name = "新增用户充值人数")
    @ApiModelProperty("新增用户充值人数")
    private String rechargeCount;

    @Excel(name = "新增用户充值总额")
    @ApiModelProperty("新增用户充值总额")
    private double rechargeSum;

    @Excel(name = "新增用户提现总额")
    @ApiModelProperty("新增用户提现总额")
    private String exchangeSum;

    @Excel(name = "总充值人数")
    @ApiModelProperty("总充值人数")
    private String rechargeTotalCount;

    @Excel(name = "总充值金额")
    @ApiModelProperty("总充值金额")
    private String rechargeTotalSum;

    @Excel(name = "总提现人数")
    @ApiModelProperty("总提现人数")
    private String exchangeTotalCount;

    @Excel(name = "总提现金额")
    @ApiModelProperty("总提现金额")
    private String exchangeTotalSum;

    @ApiModelProperty("渠道")
    private String channel;

    @ApiModelProperty("时间1")
    private String time1;

    @ApiModelProperty("时间2")
    private String time2;

}

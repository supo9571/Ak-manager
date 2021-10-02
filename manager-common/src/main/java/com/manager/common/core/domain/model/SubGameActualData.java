package com.manager.common.core.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 子游戏实时数据
 * @author sieGuang 2021/09/30
 */
@Data
public class SubGameActualData extends BaseEntity {

    @ApiModelProperty("平台id")
    private String tid;

    @ApiModelProperty("父id")
    private String parentId;

    @ApiModelProperty("游戏id")
    private String gameId;

    @ApiModelProperty("游戏名")
    private String gameName;

    @ApiModelProperty("游戏状态")
    private String gameType;

    @ApiModelProperty("投注金额")
    private BigDecimal betRecharge;

    @ApiModelProperty("返奖金额")
    private BigDecimal rewardRecharge;

    @ApiModelProperty("税收")
    private BigDecimal taxRevenue;

    @ApiModelProperty("盈亏")
    private BigDecimal profitAndLoss;

    @ApiModelProperty("盈亏比")
    private BigDecimal profitAndLossRatio;

    @ApiModelProperty("初始化时间")
    private String initTime;

    @ApiModelProperty("创建时间1")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String createTime1;

    @ApiModelProperty("创建时间2")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String createTime2;

}

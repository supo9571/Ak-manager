package com.manager.common.core.domain.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.manager.common.annotation.Excel;
import com.manager.common.annotation.Excel.ColumnType;
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

    @ApiModelProperty("日期")
    private String day;

    @ApiModelProperty("父id")
    private String parentId;

    @ApiModelProperty("游戏id")
    private String gameId;

    @Excel(name = "游戏名", cellType = ColumnType.STRING)
    @ApiModelProperty("游戏名")
    private String gameName;

    @Excel(name = "参与人数", cellType = ColumnType.STRING)
    @ApiModelProperty("参与人数")
    private int parCount;

    @Excel(name = "人局数", cellType = ColumnType.STRING)
    @ApiModelProperty("人局数")
    private int humBurCount;

    @Excel(name = "投注金额")
    @ApiModelProperty("投注金额")
    private BigDecimal betRecharge;

    @Excel(name = "返奖金额")
    @ApiModelProperty("返奖金额")
    private BigDecimal rewardRecharge;

    @Excel(name = "税收")
    @ApiModelProperty("税收")
    private BigDecimal taxRevenue;

    @Excel(name = "盈亏")
    @ApiModelProperty("盈亏")
    private BigDecimal profitAndLoss;

    @Excel(name = "盈亏比")
    @ApiModelProperty("盈亏比")
    private BigDecimal profitAndLossRatio;

    @Excel(name = "初始化时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty("初始化时间")
    private String initTime;

    @ApiModelProperty("创建时间1")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String createTime1;

    @ApiModelProperty("创建时间2")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private String createTime2;

}

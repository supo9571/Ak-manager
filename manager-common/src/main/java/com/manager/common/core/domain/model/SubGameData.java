package com.manager.common.core.domain.model;

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
public class SubGameData extends BaseEntity {

    @ApiModelProperty("平台id")
    private String tid;

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
    private BigDecimal betCount;

    @Excel(name = "返奖金额")
    @ApiModelProperty("返奖金额")
    private BigDecimal rewardCount;

    @Excel(name = "税收")
    @ApiModelProperty("税收")
    private BigDecimal feeCount;

    @Excel(name = "盈亏")
    @ApiModelProperty("盈亏")
    private BigDecimal win;

    @Excel(name = "盈亏比")
    @ApiModelProperty("盈亏比")
    private double winRatio;

    public BigDecimal getWin() {
        //投注金额-返奖金额+税收；
        win = betCount.subtract(rewardCount).add(feeCount);
        return win;
    }

    public void setWin(BigDecimal win) {
        this.win = win;
    }

    public double getWinRatio() {
        // 游戏盈亏/投注金额；
        winRatio = betCount.intValue()==0?0: win.divide(betCount,4).multiply(new BigDecimal(100)).doubleValue();
        return winRatio;
    }

    public void setWinRatio(double winRatio) {
        this.winRatio = winRatio;
    }
}

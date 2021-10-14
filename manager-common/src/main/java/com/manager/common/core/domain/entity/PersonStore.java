package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author marvin 2021/10/13
 * 个人策略配置
 * control_person_config
 */
@Data
public class PersonStore extends BaseEntity {

    @ApiModelProperty("策略id")
    private int strategyId;

    @ApiModelProperty("策略名称")
    private String strategyName;

    @ApiModelProperty("策略标签id")
    private int strategyTagId;

    @ApiModelProperty("描述说明")
    private String strategyDesc;

    @ApiModelProperty("策略状态 0关闭 1开启")
    private int strategyStatus;

    @ApiModelProperty("捕鱼类执行次数")
    private int fishMaxRunTimes;

    @ApiModelProperty("单机及PVP执行次数")
    private int danjiPvpMaxRunTimes;

    @ApiModelProperty("玩家筛选条件")
    private String conditionList;

    @ApiModelProperty("游戏列表")
    private String gameList;

    @ApiModelProperty("执行结果 关联个人属性id")
    private int runResult;

    @ApiModelProperty("执行概率")
    private int runRate;
}

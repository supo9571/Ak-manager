package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author marvin 2021/10/13
 * 游戏策略配置
 * control_game_config
 */
@Data
public class GameStore extends BaseEntity {

    @ApiModelProperty(value = "id")
    private int id;

    @ApiModelProperty(value = "策略名称")
    private String strategyName;

    @ApiModelProperty(value = "所属策略标签id")
    private int strategyTagId;

    @ApiModelProperty(value = "状态0关闭 1开启")
    private int strategyStatus;

    @ApiModelProperty(value = "杀概率")
    private int playShaRate;

    @ApiModelProperty(value = "送概率")
    private int playSongRate;

    @ApiModelProperty(value = "游戏列表")
    private String gameList;

    @ApiModelProperty(value = "基础库存")
    private int baseStore;

    @ApiModelProperty(value = "返奖库存")
    private int rewardStore;

    @ApiModelProperty(value = "风控下限 风控上限 百人阀值 pvp阀值")
    private String limitList;

    @ApiModelProperty(value = "玩家杀阀值")
    private int playShaLimit;

    @ApiModelProperty(value = "玩家送阀值")
    private int playSongLimit;

    @ApiModelProperty(value = "单机杀概率")
    private int danjiShaRate;

    @ApiModelProperty(value = "单机送概率")
    private int danjiSongRate;

}

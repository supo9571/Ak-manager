package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author marvin 2021/10/14
 */
@Data
public class PlatformStore extends BaseEntity {

    @ApiModelProperty("所有盘口ID")
    private int platformId;

    @ApiModelProperty("所属盘口名称")
    private String platformName;

    @ApiModelProperty("游戏库存标签")
    private String gameStoreList;

    @ApiModelProperty("个人库存标签")
    private String personStoreList;

}

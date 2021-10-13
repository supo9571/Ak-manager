package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author marvin 2021/10/12
 * control_tags_config
 */
@Data
public class Tags extends BaseEntity {

    private int id;

    @ApiModelProperty(value = "标签类型 1游戏库存 2个人库存")
    private int tagType;

    @ApiModelProperty(value = "标签名称")
    private String tagName;

    @ApiModelProperty(value = "执行优先级")
    private int runIndex;

    @ApiModelProperty(value = "备注说明")
    private String tagDesc;
}

package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author marvin 2021/10/13
 * 个人属性配置
 * control_person_property
 */
@Data
public class PersonProperty extends BaseEntity {

    @ApiModelProperty("id")
    private Integer propertyId;

    @ApiModelProperty("执行结果名称")
    private String propertyName;

    @ApiModelProperty("PVP类-属性及概率")
    private String pvpPropertyList;

    @ApiModelProperty("捕鱼类-属性及概率")
    private String fishPropertyList;

    @ApiModelProperty("单机类-属性及概率")
    private String danjiPropertyList;

    @ApiModelProperty("描述说明")
    private String propertyDesc;
}

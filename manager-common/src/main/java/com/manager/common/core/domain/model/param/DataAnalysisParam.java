package com.manager.common.core.domain.model.param;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author jason
 * @date 2021-10-08
 */
@Data
public class DataAnalysisParam extends BaseEntity {

    @ApiModelProperty("平台id")
    private Integer tid;

    @ApiModelProperty(value = "uid",hidden = true)
    private Integer uid;

    @ApiModelProperty(value = "当前userIds",hidden = true)
    private Long currentUserId;

}

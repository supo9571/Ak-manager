package com.manager.common.core.domain.model.vo;

import com.manager.common.annotation.Excel;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author jason
 * @date 2021-11-01
 */
@Data
public class ActivityReportVO {

    @Excel(name = "活动ID")
    private Integer rId;

    @Excel(name = "活动名称")
    private Integer rName;

    @Excel(name = "所属平台")
    private String platformName;

    @Excel(name = "活动奖利")
    private BigDecimal amount;

}

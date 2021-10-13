package com.manager.common.core.domain.model;

import com.manager.common.annotation.Excel;
import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 留存分析
 * @author sieGuang 2021/10/13
 */
@Data
public class RetainedAnalysis extends BaseEntity {

    @ApiModelProperty("平台id")
    private String tid;

    private Long tid2;

    @Excel(name = "日期")
    @ApiModelProperty("日期")
    private String day;

    @ApiModelProperty("用户id")
    private String uids;

    @Excel(name = "用户数")
    @ApiModelProperty("用户数")
    private Integer userCount;

    @Excel(name = "次日留存")
    @ApiModelProperty("次日留存")
    private String retained1;

    @Excel(name = "留存2")
    @ApiModelProperty("留存2")
    private String retained2;

    @Excel(name = "留存3")
    @ApiModelProperty("留存3")
    private String retained3;

    @Excel(name = "留存4")
    @ApiModelProperty("留存4")
    private String retained4;

    @Excel(name = "留存5")
    @ApiModelProperty("留存5")
    private String retained5;

    @Excel(name = "留存6")
    @ApiModelProperty("留存6")
    private String retained6;

    @Excel(name = "留存7")
    @ApiModelProperty("留存7")
    private String retained7;

    @Excel(name = "留存15")
    @ApiModelProperty("留存15")
    private String retained15;

    @Excel(name = "留存30")
    @ApiModelProperty("留存30")
    private String retained30;

    @Excel(name = "留存45")
    @ApiModelProperty("留存45")
    private String retained45;

    @ApiModelProperty("渠道")
    private String channel;

    @ApiModelProperty("状态 1.新增用户数 2.活跃留存 3.付费留存 4.新增充值留存 5.回流用户留存")
    private Integer type;
}

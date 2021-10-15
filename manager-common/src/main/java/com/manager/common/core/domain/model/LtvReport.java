package com.manager.common.core.domain.model;

import com.manager.common.annotation.Excel;
import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Ltv报表
 * @author sieGuang 2021/10/14
 */
@Data
public class LtvReport extends BaseEntity {

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

    @Excel(name = "平均数")
    @ApiModelProperty("平均数")
    private String averageNum;

    @Excel(name = "第1天")
    @ApiModelProperty("第1天")
    private String recharge1;

    @Excel(name = "第2天")
    @ApiModelProperty("第2天")
    private String recharge2;

    @Excel(name = "第3天")
    @ApiModelProperty("第3天")
    private String recharge3;

    @Excel(name = "第4天")
    @ApiModelProperty("第4天")
    private String recharge4;

    @Excel(name = "第5天")
    @ApiModelProperty("第5天")
    private String recharge5;

    @Excel(name = "第6天")
    @ApiModelProperty("第6天")
    private String recharge6;

    @Excel(name = "第7天")
    @ApiModelProperty("第7天")
    private String recharge7;

    @Excel(name = "第8天")
    @ApiModelProperty("第8天")
    private String recharge8;

    @Excel(name = "第9天")
    @ApiModelProperty("第9天")
    private String recharge9;

    @Excel(name = "第10天")
    @ApiModelProperty("第10天")
    private String recharge10;

    @Excel(name = "第11天")
    @ApiModelProperty("第11天")
    private String recharge11;

    @Excel(name = "第12天")
    @ApiModelProperty("第12天")
    private String recharge12;

    @Excel(name = "第13天")
    @ApiModelProperty("第13天")
    private String recharge13;

    @Excel(name = "第14天")
    @ApiModelProperty("第14天")
    private String recharge14;

    @Excel(name = "第30天")
    @ApiModelProperty("第30天")
    private String recharge30;

    @Excel(name = "第45天")
    @ApiModelProperty("第45天")
    private String recharge45;

    @ApiModelProperty("渠道")
    private String channel;

    @ApiModelProperty("创建时间1")
    private String createTime1;

    @ApiModelProperty("创建时间1")
    private String createTime2;
}

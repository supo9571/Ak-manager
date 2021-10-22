package com.job.executor.domain;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author jason
 * @date 2021-09-30
 */
@Data
public class SysTenantChannelStatistics {

    private static final long serialVersionUID = 1L;

    private String day;

    private String tId;

    private String totalAmount;

    private int rechargeNum;

    private BigDecimal rechargeAmount;

    private int withdrawNum;

    private BigDecimal withdrawAmount;

    private BigDecimal upAwardAmount;

    private BigDecimal lowerAwardAmount;

    private BigDecimal underCommission;

    private BigDecimal agentCommission;

    private int installNum;

    private int increasedBinding;

    private int activeNum;

    private int increasedNum;

    private int rechargeFirst;

    private BigDecimal rechargeAmountFirst;

    private int rechargeIncreasedNum;

    private BigDecimal rechargeIncreasedAmount;

    private BigDecimal topUpWithdrawal;

    private BigDecimal appu;

    private BigDecimal arpu;

    private BigDecimal betAmount;

    private BigDecimal rebate;

    private BigDecimal performance;

    private Date createTime;

    private Date updateTime;

}

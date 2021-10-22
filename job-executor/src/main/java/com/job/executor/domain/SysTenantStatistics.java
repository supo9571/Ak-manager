package com.job.executor.domain;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 总代渠道
 */
@Data
public class SysTenantStatistics {

    private static final long serialVersionUID = 1L;

    private String tId;

    private BigDecimal totalAmount;

    private int rechargeNum;

    private BigDecimal rechargeAmount;

    private int withdrawNum;

    private BigDecimal withdrawAmount;

    private BigDecimal upAwardAmount;

    private BigDecimal lowerAwardAmount;

    private BigDecimal underCommission;

    private BigDecimal agentCommission;

    private int registerNum;

}

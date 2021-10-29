package com.manager.common.core.domain.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author marvin 2021/9/17
 * 提现 订单
 * config_exchange_order
 */
@Data
public class ExchangeOrder {
    private int id;

    private int tid;

    private String uid;

    //提现金额
    private BigDecimal withdrawMoney;
    //玩家余额
    private BigDecimal currMoney;
    //需转账金额
    private BigDecimal transferAmount;
    //手续费
    private BigDecimal poundage;
    //提现次数
    private Integer withdrawNumber;
    //渠道
    private String channel;
    //提现ip
    private String withdrawIp;
    //提现订单号
    private String withdrawOrderNumber;
    //玩家名称
    private String uname;
    //历史提现金额
    private BigDecimal historyWithdrawMoney;
    //累计流水
    private BigDecimal accumulateWater;
    //累计充值
    private BigDecimal accumulateRecharge;
    //累计赠送
    private BigDecimal accumulateExcoins;
    //充值赠送
    private BigDecimal rechargeExcoins;
    //活动奖励(非推广)
    private BigDecimal activityRewards;
    //线下赠送
    private BigDecimal offLineExcoins;
    //注册IP
    private String registerIp;
    //提现类型 0支付宝 1银行卡
    private String withdrawType;
    //审核状态  1待审批 2待款中 3打款中 4已退款 5已打款 6打款失败 7已没收
    private String exaaStatus;
    //申请时间
    private Date createTime;

    //申请时间
    private String matchineId;

    public ExchangeOrder() {
    }

    public ExchangeOrder(String uid, BigDecimal withdrawMoney, BigDecimal currMoney, String channel, String withdrawIp, String withdrawType) {
        this.uid = uid;
        this.withdrawMoney = withdrawMoney;
        this.currMoney = currMoney;
        this.channel = channel;
        this.withdrawIp = withdrawIp;
        this.withdrawType = withdrawType;
    }
}

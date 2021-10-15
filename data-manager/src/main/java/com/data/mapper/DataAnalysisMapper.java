package com.data.mapper;

import com.manager.common.core.domain.model.param.DataAnalysisParam;
import com.manager.common.core.domain.model.vo.DataAnalysisVO;
import com.manager.common.core.domain.model.vo.DataWaterTopVO;
import com.manager.common.core.domain.model.vo.EarningsTopVO;
import com.manager.common.core.domain.model.vo.RechargeTopVO;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author jason
 * @date 2021-10-08
 */
@Mapper
public interface DataAnalysisMapper {


    /**
     * 提现top100
     * @param param
     * @return
     */
    List<DataAnalysisVO> withdrawTopList(DataAnalysisParam param);


    /**
     * 获取玩家当前余额
     * @param param
     * @return
     */
    BigDecimal getCurrentAmount(DataAnalysisParam param);

    /**
     * 充值金额 时间段
     * @param param
     * @return
     */
    BigDecimal rechargeAmount(DataAnalysisParam param);

    /**
     * 总充值金额
     * @param param
     * @return
     */
    BigDecimal rechargeAmountTotal(DataAnalysisParam param);


    /**
     * 提现金额 时间段
     * @param param
     * @return
     */
    BigDecimal withdrawAmount(DataAnalysisParam param);


    /**
     * 总提现金额
     * @param param
     * @return
     */
    BigDecimal withdrawAmountTotal(DataAnalysisParam param);


    /**
     * 流水top100 -时间段
     * @param param
     * @return
     */
    List<DataWaterTopVO> getDataWaterTopList(DataAnalysisParam param);

    /**
     * 获取当前玩家总局数列表 -时间段
     * @param param
     * @return
     */
    List<Map> getUserTableList(DataAnalysisParam param);


    /**
     * 获取充值top100 -时间段
     * @param param
     * @return
     */
    List<RechargeTopVO> getRechargeTopList(DataAnalysisParam param);

    /**
     * 获取充值top100 最后支付时间、充值方式、携带金币
     * @param param
     * @return
     */
    RechargeTopVO getLastPayTime(DataAnalysisParam param);


    /**
     * 获取当前玩家最近常玩游戏名称
     * @param param
     * @return
     */
    List<RechargeTopVO> getPlayGameList(DataAnalysisParam param);

    /**
     * 净盈利top100 -时间段 提现
     * @param param
     * @return
     */
    List<EarningsTopVO> getEarningsWithdrawList(DataAnalysisParam param);


    /**
     * 净盈利top100 -时间段 充值
     * @param param
     * @return
     */
    List<EarningsTopVO> getEarningsRechargeList(DataAnalysisParam param);

    /**
     * 净盈利top100
     * 起始时间的余额
     * 起始时间的余额小于等于 起始时间的第一条记录的余额
     * @param param
     * @return
     */
    Integer getDataCoinsStartTimeAmount(DataAnalysisParam param);

    /**
     * 净盈利top100
     * 结束时间的余额
     * 结束时间的余额  大于等于 结束时间的第一条记录的余额
     * @param param
     * @return
     */
    Integer getDataCoinsEndTimeAmount(DataAnalysisParam param);


    /**
     * 获取用户的相关信息
     * @param param
     * @return
     */
    EarningsTopVO getUserInfo(DataAnalysisParam param);


    /**
     * 获取用户投注总流水-时间段
     * @param param
     * @return
     */
    Long getUserBetTotal(DataAnalysisParam param);



}

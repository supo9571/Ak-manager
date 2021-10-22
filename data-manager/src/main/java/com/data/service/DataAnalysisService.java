package com.data.service;

import com.manager.common.core.domain.model.param.DataAnalysisParam;
import com.manager.common.core.domain.model.param.PlayerReportParam;
import com.manager.common.core.domain.model.vo.*;

import java.util.List;
import java.util.Map;

/**
 * @author jason
 * @date 2021-10-08
 */
public interface DataAnalysisService {


    List<DataAnalysisVO> withdrawTopList(DataAnalysisParam param);

    List<DataWaterTopVO> getDataWaterTopList(DataAnalysisParam param);

    /**
     * 获取充值top100
     * @param param
     * @return
     */
    List<RechargeTopVO> getRechargeTopList(DataAnalysisParam param);

    /**
     * 净盈利top100
     * @param param
     * @return
     */
    List<EarningsTopVO> getEarningsTopList(DataAnalysisParam param);


    /**
     * 全民代理top100
     * @param param
     * @return
     */
    List<AgentTopVO> getAgentTopList(DataAnalysisParam param);

    /**
     * 付费习惯
     * @param param
     * @return
     */
    List<PayInfoVO> getPayInfoList(DataAnalysisParam param);

    /**
     * 玩家报表count
     * @param param
     * @return
     */
    int getPlayerReportCount(PlayerReportParam param);

    /**
     * 玩家报表
     * @param param
     * @return
     */
    List<PlayerReportVO> getPlayerReportList(PlayerReportParam param);

    /**
     * 玩家报表-求和
     * @param param
     * @return
     */
    List<PlayerReportVO> getPlayerReportSum(PlayerReportParam param);

    /**
     * 玩家报表-游戏明细
     * @param param
     * @return
     */
    List<PlayerGameReportVO> getPlayerGameReportList(PlayerReportParam param);

    /**
     * 玩家报表-日期明细
     * @param param
     * @return
     */
    List<PlayerDayReportVO> getPlayerDayReportList(PlayerReportParam param);

}

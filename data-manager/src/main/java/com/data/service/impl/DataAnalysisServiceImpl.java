package com.data.service.impl;

import com.data.mapper.DataAnalysisMapper;
import com.data.service.DataAnalysisService;
import com.manager.common.core.domain.model.param.DataAnalysisParam;
import com.manager.common.core.domain.model.param.PlayerReportParam;
import com.manager.common.core.domain.model.vo.*;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author jason
 * @date 2021-10-08
 */
@Service
@Slf4j
public class DataAnalysisServiceImpl implements DataAnalysisService {

    @Resource
    private DataAnalysisMapper mapper;

    @Override
    public List<DataAnalysisVO> withdrawTopList(DataAnalysisParam param) {
        List<DataAnalysisVO> list = mapper.withdrawTopList(param);
        list.forEach(vo -> {
            param.setUid(vo.getUid());
            vo.setAmount(mapper.getCurrentAmount(param));
            vo.setRechargeAmount(mapper.rechargeAmount(param));
            vo.setRechargeAmountTotal(mapper.rechargeAmountTotal(param));
            vo.setWithdrawAmountTotal(mapper.withdrawAmountTotal(param));
        });
        return list;
    }

    @Override
    public List<DataWaterTopVO> getDataWaterTopList(DataAnalysisParam param) {
        List<DataWaterTopVO> list = mapper.getDataWaterTopList(param);
        if (!CollectionUtils.isEmpty(list)) {
            List<Map> userList = mapper.getUserTableList(param);
            Map<String, Integer> map = userList.stream().collect(Collectors.toMap(v -> String.valueOf(v.get("uid")),
                    v -> Integer.valueOf(v.get("count").toString())));
            for (DataWaterTopVO vo : list) {
                param.setUid(vo.getUid());
                vo.setGameTableNum(map.get(vo.getUid()));
                vo.setRechargeAmountTotal(mapper.rechargeAmountTotal(param));
                vo.setWithdrawAmountTotal(mapper.withdrawAmountTotal(param));
            }
        }
        return list;
    }

    @Override
    public List<RechargeTopVO> getRechargeTopList(DataAnalysisParam param) {
        List<RechargeTopVO> list = mapper.getRechargeTopList(param);
        if (!CollectionUtils.isEmpty(list)) {
            list.forEach(vo -> {
                param.setUid(vo.getUid());
                vo.setWithdrawAmount(mapper.withdrawAmountTotal(param));
                RechargeTopVO r = mapper.getLastPayTime(param);
                if (r != null) {
                    vo.setAmount(r.getAmount());
                    vo.setLastPayTime(r.getLastPayTime());
                    vo.setRechargeType(r.getRechargeType());
                }
                List<RechargeTopVO> gameList = mapper.getPlayGameList(param);
                String gameNames = gameList.stream().map(RechargeTopVO::getGameNames).collect(Collectors.joining("、"));
                vo.setGameNames(gameNames);
            });
        }
        return list;
    }

    @Override
    public List<EarningsTopVO> getEarningsTopList(DataAnalysisParam param) {
        List<EarningsTopVO> list = mapper.getEarningsWithdrawList(param);
        if (!CollectionUtils.isEmpty(list)) {
            //玩家净盈利=兑换金额-充值金额+结束时间的余额-起始时间的余额+时间段内还未处理完成的兑换金额（未处理、待打款、打款中、打款失败）
            list.forEach(vo -> {
                param.setUid(vo.getUid());
                //兑换金额-充值金额+时间段内还未处理完成的兑换金额（未处理、待打款、打款中、打款失败）
                BigDecimal withdrawAmount = vo.getWithdrawAmount();
                BigDecimal rechargeAmount = mapper.rechargeAmount(param);
                //结束时间的余额
                Integer endTimeAmount = mapper.getDataCoinsEndTimeAmount(param);
                if (endTimeAmount == null) {
                    endTimeAmount = 0;
                }
                //起始时间的余额
                Integer startTimeAmount = mapper.getDataCoinsStartTimeAmount(param);
                if (startTimeAmount == null) {
                    startTimeAmount = 0;
                }
                BigDecimal totalAmount = withdrawAmount.subtract(rechargeAmount).add(new BigDecimal(endTimeAmount - startTimeAmount));
                vo.setAmount(totalAmount);
                EarningsTopVO userInfo = mapper.getUserInfo(param);
                if (userInfo != null) {
                    vo.setChannel(userInfo.getChannel());
                    vo.setName(userInfo.getName());
                    vo.setTime(userInfo.getTime());
                    vo.setLastLoginTime(userInfo.getLastLoginTime());
                }
                RechargeTopVO r = mapper.getLastPayTime(param);
                if (r != null) {
                    vo.setLastPayTime(r.getLastPayTime());
                }
                vo.setRechargeAmount(rechargeAmount);
                vo.setWithdrawAmount(mapper.withdrawAmount(param));
                vo.setBetTotal(mapper.getUserBetTotal(param));
                List<RechargeTopVO> gameList = mapper.getPlayGameList(param);
                String gameNames = gameList.stream().map(RechargeTopVO::getGameNames).collect(Collectors.joining("、"));
                vo.setGameNames(gameNames);
            });
        }
        return list;
    }

    @Override
    public List<AgentTopVO> getAgentTopList(DataAnalysisParam param) {
        return mapper.getAgentTopList(param);
    }

    @Override
    public List<PayInfoVO> getPayInfoList(DataAnalysisParam param) {
        List<PayInfoVO> list = new ArrayList<>();
        Map<String, BigDecimal> map = mapper.getPayInfoList(param);
        if (map != null) {
            BigDecimal amountTotal = mapper.rechargeAmount(param);
            for (int i = 1; i <= 12; i++) {
                PayInfoVO vo = new PayInfoVO();
                vo.setAmountName(getPayAmountName(i));
                vo.setCount(map.get("count" + i).intValue());
                if (amountTotal.compareTo(BigDecimal.ZERO) > 0) {
                    BigDecimal percentage = amountTotal;
                    if (vo.getCount() != 0) {
                        percentage = amountTotal.divide(new BigDecimal(vo.getCount()), 2, BigDecimal.ROUND_HALF_UP);
                    }
                    vo.setPercentage(percentage.setScale(2, RoundingMode.HALF_UP) + "%");
                }
                list.add(vo);
            }
        }
        return list;
    }

    @Override
    public int getPlayerReportCount(PlayerReportParam param) {
        return mapper.getPlayerReportCount(param);
    }

    @Override
    public List<PlayerReportVO> getPlayerReportList(PlayerReportParam param) {
        List<PlayerReportVO> list = mapper.getPlayerReportList(param);
        return list;
    }

    @Override
    public List<PlayerGameReportVO> getPlayerGameReportList(PlayerReportParam param) {
        List<PlayerGameReportVO> list = mapper.getPlayerGameReportList(param);
        return list;
    }

    @Override
    public List<PlayerDayReportVO> getPlayerDayReportList(PlayerReportParam param) {
        List<PlayerDayReportVO> list = mapper.getPlayerDayReportList(param);
        return list;
    }


    private static String getPayAmountName(Integer i) {
        String name = "";
        switch (i) {
            case 1:
                name = "0-50";
                break;
            case 2:
                name = "50-100";
                break;
            case 3:
                name = "100-200";
                break;
            case 4:
                name = "200-500";
                break;
            case 5:
                name = "500-1000";
                break;
            case 6:
                name = "1000-3000";
                break;
            case 7:
                name = "3000-5000";
                break;
            case 8:
                name = "5000-10000";
                break;
            case 9:
                name = "10000-20000";
                break;
            case 10:
                name = "20000-50000";
                break;
            case 11:
                name = "50000-100000";
                break;
            case 12:
                name = "≥100000";
                break;
        }
        return name;
    }

    public static void main(String[] args) {
        BigDecimal amount = new BigDecimal(0);
        System.out.println(new BigDecimal(52704.4400000000).setScale(2, BigDecimal.ROUND_DOWN));
    }
}

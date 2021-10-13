package com.data.service.impl;

import com.data.mapper.DataAnalysisMapper;
import com.data.service.DataAnalysisService;
import com.manager.common.core.domain.model.param.DataAnalysisParam;
import com.manager.common.core.domain.model.vo.DataAnalysisVO;
import com.manager.common.core.domain.model.vo.DataWaterTopVO;
import com.manager.common.core.domain.model.vo.RechargeTopVO;
import com.manager.common.utils.bean.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author jason
 * @date 2021-10-08
 */
@Service
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


}

package com.data.service.impl;

import com.data.mapper.DataAnalysisMapper;
import com.data.service.DataAnalysisService;
import com.manager.common.core.domain.model.param.DataAnalysisParam;
import com.manager.common.core.domain.model.vo.DataAnalysisVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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
        list.forEach(vo->{
            param.setUid(vo.getUid());
            vo.setAmount(mapper.getCurrentAmount(param));
            vo.setRechargeAmount(mapper.rechargeAmount(param));
            vo.setRechargeAmountTotal(mapper.rechargeAmountTotal(param));
            vo.setWithdrawAmountTotal(mapper.withdrawAmountTotal(param));
        });
        return list;
    }
}

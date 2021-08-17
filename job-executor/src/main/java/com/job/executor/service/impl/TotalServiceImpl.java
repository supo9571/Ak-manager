package com.job.executor.service.impl;

import com.job.executor.datasource.DataSource;
import com.job.executor.datasource.DataSourceType;
import com.job.executor.mapper.TotalMapper;
import com.job.executor.service.TotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 操作 sys_manager库 sys_totle表
 */
@Service
public class TotalServiceImpl implements TotalService {
    @Autowired
    private TotalMapper totalMapper;

    @Override
    @DataSource(DataSourceType.Master)
    public Map getTimes() {
        return totalMapper.getTimes();
    }

    @Override
    @DataSource(DataSourceType.Master)
    public void updateTotal(BigDecimal addOrderMoney, long orderTime,BigDecimal addWithdrawMoney, long withdrawTime) {
        totalMapper.updateTotalRecharge(addOrderMoney,orderTime,addWithdrawMoney,withdrawTime);
    }

    @Override
    @DataSource(DataSourceType.Master)
    public void updateOnlinePlay(int size) {
        totalMapper.updateOnlinePlay(size);
    }
}

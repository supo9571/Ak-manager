package com.job.executor.service.impl;

import com.job.executor.datasource.DataSourceType;
import com.job.executor.datasource.DataSource;
import com.job.executor.mapper.OrderMapper;
import com.job.executor.mapper.TotalMapper;
import com.job.executor.service.TotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class TotalServiceImpl implements TotalService {
    @Autowired
    private TotalMapper totalMapper;

    @Autowired
    private OrderMapper orderMapper;
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
}

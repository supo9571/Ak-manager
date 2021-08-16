package com.job.executor.service;

import java.math.BigDecimal;
import java.util.Map;

public interface TotalService {
    Map getTimes();

    void updateTotal(BigDecimal addOrderMoney, long orderTime,BigDecimal addWithdrawMoney, long withdrawTime);
}

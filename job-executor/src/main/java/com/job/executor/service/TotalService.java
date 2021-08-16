package com.job.executor.service;

import java.math.BigDecimal;

public interface TotalService {
    int getOrderTime();

    void updateTotalRecharge(BigDecimal addMoney, long time);
}

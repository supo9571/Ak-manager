package com.job.executor.service;

import java.util.Map;

public interface OrderService {

    Map getNewRecharge(String orderTime);

    Map getNewWithdraw(String withdrawTime);
}

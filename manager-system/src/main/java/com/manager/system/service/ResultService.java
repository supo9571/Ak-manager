package com.manager.system.service;

import java.util.List;

/**
 * @author marvin 2021/10/15
 */
public interface ResultService {

    List getGameResult(int tid, int strategyId, String day);

    List getPersonResult(int tid, int strategyId, int uid, String day);

    List getPersonResultInfo(int uid, String strategyFlag, String day);
}

package com.data.service;

import java.util.List;

/**
 * @author marvin 2021/9/29
 */
public interface AgentServive {
    List getCommissionList(Integer tid, String uid, String agentId);

    List getCommissionDays(String uid);

    List getCashs(String uid);

    List getPopularizes(String uid);
}

package com.data.service.impl;

import com.data.mapper.AgentMapper;
import com.data.service.AgentServive;
import com.manager.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author marvin 2021/9/29
 */
@Service
public class AgentServiveImpl implements AgentServive {
    @Autowired
    private AgentMapper agentMapper;

    @Override
    public List getCommissionList(Integer tid, String uid) {
        String date = DateUtils.getDate();
        return agentMapper.getCommissionList(tid,uid,date);
    }
}

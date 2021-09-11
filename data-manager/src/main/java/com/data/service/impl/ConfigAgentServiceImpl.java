package com.data.service.impl;

import com.data.mapper.ConfigAgentMapper;
import com.data.mapper.TenantMapper;
import com.data.service.ConfigAgenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/9/11
 */
@Service
public class ConfigAgentServiceImpl implements ConfigAgenService {

    @Autowired
    private ConfigAgentMapper configAgentMapper;

    @Autowired
    private TenantMapper tenantMapper;

    @Override
    public List<Map> getConfigAgentList(String cid) {
        Integer tid = tenantMapper.getTidByCid(cid);
        return configAgentMapper.getConfigAgentList(tid);
    }
}

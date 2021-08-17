package com.manager.system.service.impl;

import com.manager.system.mapper.SysTenantMapper;
import com.manager.system.service.SysTenantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SysTenantServiceImpl implements SysTenantService {

    @Autowired
    private SysTenantMapper sysTenantMapper;

    @Override
    public List selectTenants(String tid, String tType) {
        return sysTenantMapper.selectTenants(tid,tType);
    }
}

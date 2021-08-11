package com.manager.system.service.impl;

import com.manager.common.core.domain.entity.SysIpBlack;
import com.manager.common.core.domain.entity.SysIpWhite;
import com.manager.system.mapper.SysIpBlackMapper;
import com.manager.system.mapper.SysIpWhiteMapper;
import com.manager.system.service.SysIpBlackService;
import com.manager.system.service.SysIpWhiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class SysIpBlackServiceImpl implements SysIpBlackService {

    @Autowired
    private SysIpBlackMapper sysIpBlackMapper;

    @Override
    public void addIpBlack(long deptId, long userId, String ips,long createUserId) {
        List<SysIpBlack> list = new ArrayList<>();
        List<String> ipList = Arrays.asList(ips.split(","));
        ipList.forEach(ip->{
            list.add(new SysIpBlack(deptId,userId,createUserId,ip));
        });
        sysIpBlackMapper.insertIpBlack(list);
    }

    @Override
    public void delIpBlack(long id) {
        sysIpBlackMapper.delIpBlack(id);
    }

    @Override
    public List selectIpBlackList(String deptId, String userId, String ip) {
        return sysIpBlackMapper.selectIpBlackList(deptId,userId,ip);
    }
}

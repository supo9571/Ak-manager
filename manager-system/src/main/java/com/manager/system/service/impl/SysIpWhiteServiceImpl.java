package com.manager.system.service.impl;

import com.manager.common.core.domain.entity.SysIpWhite;
import com.manager.system.mapper.SysIpWhiteMapper;
import com.manager.system.service.SysIpWhiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Service
public class SysIpWhiteServiceImpl implements SysIpWhiteService {

    @Autowired
    private SysIpWhiteMapper sysIpWhiteMapper;

    @Override
    public void addIpWhite(long tId, long userId, String ips,long createUserId) {
        List<SysIpWhite> list = new ArrayList<>();
        List<String> ipList = Arrays.asList(ips.split(","));
        ipList.forEach(ip->{
            list.add(new SysIpWhite(tId,userId,createUserId,ip));
        });
        sysIpWhiteMapper.insertIpWhite(list);
    }

    @Override
    public void delIpWhite(long id) {
        sysIpWhiteMapper.delIpWhite(id);
    }

    @Override
    public List selectIpWhiteList(String tId, String userId, String ip) {
        return sysIpWhiteMapper.selectIpWhiteList(tId,userId,ip);
    }

    @Override
    public String selectIpByUserId(String userId) {
        StringBuffer userIp = new StringBuffer();
        List ips = sysIpWhiteMapper.selectIpByUserId(userId);
        ips.forEach(ip -> userIp.append(((Map)ip).get("ip")+","));
        return userIp.length()>1?userIp.substring(0,userIp.length()-1):"";
    }
}

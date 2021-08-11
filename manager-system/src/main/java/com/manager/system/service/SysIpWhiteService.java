package com.manager.system.service;

import com.manager.common.core.domain.entity.SysUser;

import java.util.List;

public interface SysIpWhiteService {
    void addIpWhite(long deptId, long userId, String ips,long createUserId);

    void delIpWhite(long id);

    List selectIpWhiteList(String deptId, String userId, String ip);
}

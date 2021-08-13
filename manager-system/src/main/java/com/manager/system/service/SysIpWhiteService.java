package com.manager.system.service;

import java.util.List;

public interface SysIpWhiteService {
    void addIpWhite(long deptId, long userId, String ips,long createUserId);

    void delIpWhite(long id);

    List selectIpWhiteList(String tId, String userId, String ip);
}

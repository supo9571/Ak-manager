package com.manager.system.service;

import java.util.List;

public interface SysIpWhiteService {
    void addIpWhite(long deptId, Long userId, String ips,long createUserId,String userName);

    void delIpWhite(long id);

    List selectIpWhiteList(String tId, String userId, String ip,String userName);

    String selectIpByUserId(String userId);
}

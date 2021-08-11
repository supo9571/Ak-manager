package com.manager.system.service;

import java.util.List;

public interface SysIpBlackService {
    void addIpBlack(long deptId, long userId, String ips,long createUserId);

    void delIpBlack(long id);

    List selectIpBlackList(String deptId, String userId, String ip);
}

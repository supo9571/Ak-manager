package com.data.service;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/28
 */
public interface UserService {
    Integer findByphone(String phone);

    List<Map> selectPackage(String ip, String channelId, String versionId, String platform);
}

package com.data.service;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/30
 */
public interface HotUpdateService {


    List<Map> selectPackage(String ip, String channelId, String versionId, String platform);

    List<Map> selectConsumer();
}

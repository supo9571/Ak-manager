package com.data.service;

import com.manager.common.core.domain.model.Allupdate;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/30
 */
public interface UpdateService {

    List<Map> selectPackage(String ip, String channelId, String versionId, String platform);

    List<Map> selectConsumer();

    Integer addAllUpdate(Allupdate allupdate);

    List findAllUpdate();

    List findAllUpdateHistory(String tid);

    Integer editAllUpdate(Allupdate allupdate);

    Integer deleteAllupdate(String id);
}

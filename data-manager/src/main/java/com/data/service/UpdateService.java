package com.data.service;

import com.manager.common.core.domain.model.Allupdate;
import com.manager.common.core.domain.model.Hotupdate;

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

    List findAllUpdateHistory(Integer tid);

    Integer editAllUpdate(Allupdate allupdate);

    Integer deleteAllupdate(String id);

    String selectAllupdate(String channelId, String versionId);

    int addHotUpdate(Hotupdate hotUpdate);

    int editHotUpdate(Hotupdate hotUpdate);

    Integer delHotupdate(String id);

    List findHotupdate(Integer id);
}

package com.data.service.impl;

import com.data.mapper.UpdateMapper;
import com.data.service.UpdateService;
import com.manager.common.core.domain.model.Allupdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/30
 * 包更新 service
 */
@Service
public class UpdateServiceImpl implements UpdateService {

    @Autowired
    private UpdateMapper updateMapper;

    @Override
    public List<Map> selectPackage(String ip, String channelId, String versionId, String platform) {
        List list = updateMapper.selectPackage(ip,channelId,versionId,platform);
        List list1 = updateMapper.selectPackage1(ip,channelId,versionId,platform);
        list.addAll(list1);
        return list;
    }

    @Override
    public List<Map> selectConsumer() {
        return updateMapper.selectConsumer();
    }

    /**
     * 添加 整包更新
     */
    @Override
    public Integer addAllUpdate(Allupdate allupdate) {
        return updateMapper.addAllUpdate(allupdate);
    }
}

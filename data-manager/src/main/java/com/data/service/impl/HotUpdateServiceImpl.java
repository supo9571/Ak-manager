package com.data.service.impl;

import com.data.mapper.HotUpdateMapper;
import com.data.service.HotUpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/30
 */
@Service
public class HotUpdateServiceImpl implements HotUpdateService {

    @Autowired
    private HotUpdateMapper hotUpdateMapper;

    @Override
    public List<Map> selectPackage(String ip, String channelId, String versionId, String platform) {
        List list = hotUpdateMapper.selectPackage(ip,channelId,versionId,platform);
        List list1 = hotUpdateMapper.selectPackage1(ip,channelId,versionId,platform);
        list.addAll(list1);
        return list;
    }

    @Override
    public List<Map> selectConsumer() {
        return hotUpdateMapper.selectConsumer();
    }
}

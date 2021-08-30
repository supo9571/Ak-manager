package com.data.service.impl;

import com.data.mapper.UserMapper;
import com.data.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/28
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public Integer findByphone(String phone) {
        return userMapper.findByphone(phone);
    }

    @Override
    public List<Map> selectPackage(String ip, String channelId, String versionId, String platform) {
        return userMapper.selectPackage(ip,channelId,versionId,platform);
    }
}

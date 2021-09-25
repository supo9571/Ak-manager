package com.data.service.impl;

import com.data.mapper.MailMapper;
import com.data.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author marvin 2021/9/25
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private MailMapper mailMapper;

    @Override
    public List getTips(String channelId) {
        return mailMapper.getTips(channelId);
    }
}

package com.data.service.impl;

import com.data.mapper.OnlineMaper;
import com.data.service.OnlineService;
import com.manager.common.core.domain.model.OnlinePlayer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author marvin 2021/8/21
 */
@Service
public class OnlineServiceImpl implements OnlineService {
    @Autowired
    private OnlineMaper onlineMaper;

    @Override
    public List selectOnline(OnlinePlayer onlinePlayer) {
        return onlineMaper.selectOnline(onlinePlayer);
    }
}

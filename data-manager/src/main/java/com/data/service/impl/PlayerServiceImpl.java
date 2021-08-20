package com.data.service.impl;

import com.data.mapper.PlayerMapper;
import com.data.service.PlayerService;
import com.manager.common.core.domain.model.PlayUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author marvin 2021/8/20
 */
@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerMapper playerMapper;

    @Override
    public List selectPlayer(PlayUser playUser) {
        return playerMapper.selectPlayer(playUser);
    }
}

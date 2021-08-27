package com.data.service.impl;

import com.data.mapper.GameMapper;
import com.data.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author marvin 2021/8/27
 */
@Service
public class GameServiceImpl implements GameService {
    @Autowired
    private GameMapper gameMapper;
    @Override
    public List getGames() {
        return gameMapper.getGames();
    }
}

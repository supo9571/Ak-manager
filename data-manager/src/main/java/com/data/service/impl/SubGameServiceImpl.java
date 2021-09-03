package com.data.service.impl;

import com.data.mapper.SubGameMapper;
import com.data.service.SubGameService;
import com.manager.common.core.domain.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 子游戏管理
 * @author sieGuang 2021/09/03
 */
@Service
public class SubGameServiceImpl implements SubGameService {

    @Autowired
    private SubGameMapper subGameMapper;

    @Override
    public List getSubGameList(Game game) {
        return subGameMapper.getSubGameList(game);
    }

    @Override
    public int editSubGame(Game game) {
        return subGameMapper.editSubGame(game);
    }

    @Override
    public Integer getIpCount() {
        return subGameMapper.getIpCount();
    }

}

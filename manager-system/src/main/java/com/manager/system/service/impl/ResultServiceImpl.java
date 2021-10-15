package com.manager.system.service.impl;

import com.manager.system.mapper.ResultMapper;
import com.manager.system.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author marvin 2021/10/15
 */
@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultMapper resultMapper;
    @Override
    public List getGameResult(int tid, int strategyId, String day) {
        return resultMapper.getGameResult(tid, strategyId, day);
    }
}

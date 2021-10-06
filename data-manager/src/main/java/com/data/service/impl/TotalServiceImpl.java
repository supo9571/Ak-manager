package com.data.service.impl;

import com.data.mapper.TotalMapper;
import com.data.service.TotalService;
import com.manager.common.core.domain.model.Summarize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author marvin 2021/10/6
 */
@Service
public class TotalServiceImpl implements TotalService {
    @Autowired
    private TotalMapper totalMapper;
    @Override
    public List getTotals(Summarize summarize) {
        return totalMapper.getTotals(summarize);
    }
}

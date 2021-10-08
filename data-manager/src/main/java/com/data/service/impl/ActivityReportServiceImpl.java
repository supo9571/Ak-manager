package com.data.service.impl;

import com.data.mapper.ActivityReportMapper;
import com.data.service.ActivityReportService;
import com.manager.common.core.domain.model.Coins;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jason
 * @date 2021-10-07
 */
@Service
public class ActivityReportServiceImpl implements ActivityReportService {

    @Resource
    private ActivityReportMapper mapper;

    @Override
    public List selectActivityList(Coins coins) {
        return mapper.selectActivityList(coins);
    }


}

package com.data.service.impl;

import com.data.mapper.ActivityReportMapper;
import com.data.service.ActivityReportService;
import com.manager.common.core.domain.entity.Activity;
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

    @Override
    public List selectActivityDay(Activity activity) {
        activity.setActivityType(changeType(activity.getActivityType()));
        return mapper.selectActivityDay(activity);
    }

    private String changeType(String type){
            switch (type) {
                case "113114":
                    return "110003,110001";
                case "109":
                    return "110003";
                case "123":
                    return "110007";
                case "122":
                    return "110004";
                case "115":
                    return "110006";
                case "112":
                    return "100063";
                case "111":
                    return "100000";
                case "116":
                    return "110005";
                default:
                    return "-1";
            }
    }

}

package com.data.service;

import com.manager.common.core.domain.model.Coins;

import java.util.List;

/**
 * @author jason
 * @date 2021-10-07
 */
public interface ActivityReportService {

    List selectActivityList(Coins coins);

}

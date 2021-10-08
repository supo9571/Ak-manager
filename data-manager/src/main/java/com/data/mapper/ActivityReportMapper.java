package com.data.mapper;

import com.manager.common.core.domain.model.Coins;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author jason
 * @date 2021-10-07
 */
@Mapper
public interface ActivityReportMapper {

    /**
     *
     */
    List selectActivityList(Coins coins);


}

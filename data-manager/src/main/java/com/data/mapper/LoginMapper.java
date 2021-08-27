package com.data.mapper;

import com.manager.common.core.domain.model.Login;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/21
 */
@Mapper
public interface LoginMapper {
    List selectLogin(Login login);

    @Select("select num from data_login_count where date = #{date}")
    Integer selectTodayLogins(@Param("date") String date);

    @Select("select * from data_login_count where date >=#{beginDate} AND #{endDate}>= date order by date")
    List<Map> selectLoginCounts(@Param("beginDate") String beginDate, @Param("endDate")String endDate);
}

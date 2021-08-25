package com.data.mapper;

import com.manager.common.core.domain.model.PlayUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/20
 */
@Mapper
public interface PlayerMapper {

    List selectPlayer(PlayUser playUser);

    @Select("select curr,FROM_UNIXTIME(time) time from data_coins where uid = #{uid}")
    List<Map> selectPlayerCurr(@Param("uid") Long uid);
}

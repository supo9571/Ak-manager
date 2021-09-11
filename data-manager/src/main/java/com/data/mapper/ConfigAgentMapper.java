package com.data.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/9/11
 */
@Mapper
public interface ConfigAgentMapper {

    /**
     * 查询
     */
    @Select("select a.id, a.lvl lvl,a.lvl_name lvlName,a.min,a.max,a.rebate rebate,a.promotion_domain promotionDomain from config_agent a where a.tid = #{tid} order by a.lvl asc")
    List<Map> getConfigAgentList(@Param("tid") Integer tid);
}

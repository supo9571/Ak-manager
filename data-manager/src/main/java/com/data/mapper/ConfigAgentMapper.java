package com.data.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    @Select("select a.lvl lvl,a.lvl_name lvlname,a.min,a.max,a.rebate rata  from config_agent a where a.tid = #{tid} order by a.lvl asc")
    List<Map> getConfigAgentList(@Param("tid") Integer tid);

    @Select("select time from data_register d left join sys_tenant t on d.channel = t.t_id where t.tenant = #{tid} and uid = #{agentId}")
    Long selectAgent(@Param("tid") Integer tid, @Param("agentId") String agentId);

    @Update("update data_register set agent_id = #{agentId},agent_time = #{agentTime} where uid = #{uid} and time>#{time}")
    Integer setAgentId(@Param("agentId") String agentId, @Param("uid")String uid, @Param("time")Long time,@Param("agentTime") Long agentTime);

    @Select("SELECT d.uid," +
            "ROUND(IF(c.sub_ratio IS NULL,d.sub_ratio,d.sub_ratio+c.sub_ratio),2)  sub_water," +
            "ROUND(IF(c.other_ratio IS NULL,d.other_ratio,d.other_ratio+c.other_ratio),2)  water," +
            "ROUND(IF(c.total_income IS NULL,d.total_income,d.total_income+c.total_income),2)  commission_all," +
            "d.team_num teamNum " +
            "FROM agent_commission_day d " +
            "LEFT JOIN agent_commission c ON d.uid = c.uid " +
            "WHERE d.agent_id = #{uid} and d.day=#{day} limit #{beginNum},#{limit}")
    List<Map> selectSubinfo(@Param("beginNum") int beginNum,@Param("limit") Integer limit,@Param("uid") String uid,@Param("day") String day);

    @Select("select count(1) from agent_commission_day where agent_id = #{uid} and day=#{day}")
    Integer selectSubinfoCount(@Param("uid") String uid,@Param("day") String day);

    @Select("select case_income out_golds,DATE_FORMAT(create_time,'%Y-%m-%d %H:%i::%s') out_time from agent_case_income " +
            "where uid = #{uid} order by create_time desc limit #{beginNum},#{limit}")
    List<Map> getWithdrawHistory(@Param("beginNum") int beginNum,@Param("limit")int limit, @Param("uid") Long uid);

    @Select("select count(1) from agent_case_income where uid = #{uid}")
    Integer getWithdrawHistoryCount(@Param("uid") Long uid);
}

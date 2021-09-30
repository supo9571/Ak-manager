package com.job.executor.mapper;

import com.job.executor.domain.AgentCommission;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author marvin 2021/9/17
 */
@Mapper
public interface AgentMapper {

    List<AgentCommission> selectUids();

    @Select("select count(1) from data_register where agent_id = #{uid}")
    Integer selectSubNum(@Param("uid") Long uid);

    @Select("select uid from data_register where agent_id = #{uid}")
    List<Long> selectSubUid(@Param("uid") Long uid);

    void saveAgentIncome(List<AgentCommission> agents);

    @Select("SELECT SUM(VALUE) FROM data_water WHERE uid = #{uid} AND  time >= #{beginTime} AND time < #{endTime} " +
            "AND mstime > (SELECT agent_time FROM data_register WHERE uid = #{uid}) ")
    Long selectSubRatio(@Param("uid") Long uid, @Param("beginTime") Long beginTime, @Param("endTime") Long endTime);

    @Select("SELECT rebate FROM config_agent c LEFT JOIN sys_tenant t ON c.tid = t.tenant WHERE c.min <=#{value} AND c.max>#{value} AND t.t_id = #{channel} ")
    Integer selectRebate(@Param("value") BigDecimal value, @Param("channel") String channel);

    void saveAgentDayIncome(List<AgentCommission> agents);

    List<AgentCommission> selectDayIncome(String date);

    @Select("select count(1) from data_register where agent_id = #{uid} and agent_time>=#{beginTime} and agent_time<=#{endTime}")
    Integer getTodaySubnum(@Param("uid") Long uid, @Param("beginTime") Long beginTime, @Param("endTime") Long endTime);

    @Select("select uid from data_register where agent_id = #{uid} and agent_time>=#{beginTime} and agent_time<=#{endTime}")
    List<Long> selectTodaySubUid(@Param("uid") Long uid, @Param("beginTime") Long beginTime, @Param("endTime") Long endTime);

    void saveAgentPopularize(List<AgentCommission> agents);
}

package com.data.mapper;

import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
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
    @Select("select a.lvl lv,a.lvl_name lvname,a.min,a.max,a.rebate rata  from config_agent a where a.tid = #{tid} order by a.lvl asc")
    List<Map> getConfigAgentList(@Param("tid") Integer tid);

    @Select("select time from data_register d left join sys_tenant t on d.channel = t.t_id where t.tenant = #{tid} and uid = #{agentId}")
    Long selectAgent(@Param("tid") Integer tid, @Param("agentId") String agentId);

    @Update("update data_register set agent_id = #{agentId},agent_time = #{agentTime} where uid = #{uid} and time>#{time}")
    Integer setAgentId(@Param("agentId") String agentId, @Param("uid") String uid, @Param("time") Long time, @Param("agentTime") Long agentTime);

    @Select("SELECT d.uid," +
            "d.sub_ratio+IF(c.sub_ratio IS NULL,0,c.sub_ratio) sub_water," +
            "d.other_ratio+IF(c.other_ratio IS NULL,0,c.other_ratio) team_water," +
            "d.total_income+IF(c.total_income IS NULL,0,c.total_income) commission_all," +
            "d.total_income today_income," +
            "d.team_num teamNum " +
            "FROM agent_commission_day d " +
            "LEFT JOIN agent_commission c ON d.uid = c.uid " +
            "WHERE d.agent_id = #{uid} and d.day=#{day} limit #{beginNum},#{limit}")
    List<Map> selectSubinfo(@Param("beginNum") int beginNum, @Param("limit") Integer limit, @Param("uid") String uid, @Param("day") String day);

    @Select("select count(1) from agent_commission_day where agent_id = #{uid} and day=#{day}")
    Integer selectSubinfoCount(@Param("uid") String uid, @Param("day") String day);

    @Select("select case_income out_golds,DATE_FORMAT(create_time,'%Y-%m-%d %H:%i::%s') out_time from agent_case_income " +
            "where uid = #{uid} order by create_time desc limit #{beginNum},#{limit}")
    List<Map> getWithdrawHistory(@Param("beginNum") int beginNum, @Param("limit") int limit, @Param("uid") Long uid);

    @Select("select count(1) from agent_case_income where uid = #{uid}")
    Integer getWithdrawHistoryCount(@Param("uid") Long uid);

    @Select("SELECT d.uid,d.agent_id pid,d.team_num team_num_with_new,d.sub_num first_proxy_num_with_new," +
            "d.total_income+c.total_income commission_all," +
            "c.wait_income - d.cash_income commission_pre_all," +
            "d.total_income todayRate " +
            "FROM agent_commission_day d " +
            "LEFT JOIN agent_commission c " +
            "ON d.uid = c.uid " +
            "WHERE d.uid = #{uid} AND d.day = #{day}")
    Map getInfo(@Param("uid") String uid, @Param("day") String day);

    @Select("select promotion_domain from config_agent where tid=#{tid} limit 0,1")
    String getSpreatUrl(@Param("tid") Integer tid);

    @Select("SELECT day date,sub_ratio+other_ratio teamwater," +
            "sub_ratio subwater,other_ratio next_water,total_income count_rebate FROM agent_commission_day " +
            "WHERE uid = #{uid} limit #{beginNum},#{limit}")
    List<Map> getIncome(@Param("beginNum") int beginNum, @Param("limit") int limit, @Param("uid") Long uid);

    @Select("select count(*) from agent_commission_day where uid = #{uid}")
    Integer getIncomeCount(Long uid);

    @Select("select c.wait_income-d.cash_income from agent_commission c left join agent_commission_day d " +
            "ON d.uid = c.uid where c.uid = #{uid} and d.day = #{day} ")
    BigDecimal getWaitIncom(@Param("uid") String uid, @Param("day") String day);

    @Insert("insert into agent_case_income (uid,case_income,create_time) values (#{uid},#{cash},sysdate())")
    void saveWithdarw(@Param("uid") String uid, @Param("cash") BigDecimal cash);

    @Update("update agent_commission_day set cash_income = cash_income+#{cash},wait_income = wait_income-#{cash} where uid = #{uid} and day = #{day} ")
    void updateWaitIncome(@Param("uid") String uid, @Param("cash") BigDecimal cash, @Param("day") String day);

    @Select("SELECT begin_time act_begin_time,end_time act_end_time,id act_id,title act_name,IF(`type`=1,'122','123') act_type,'true' open_state,'true' show_icon," +
            "IF(`type`=1,'',content) bg_url,IF(`type`=2,'',content) act_desc,sort sort_index FROM sys_propaganda WHERE state = '2' and tid = #{tid} ")
    List<Map> getActList(@Param("tid") Integer tid);

    @Select("select distinct activity_type from config_activity where activity_begin <=sysdate() <= activity_end and tid = #{tid} ")
    List<Integer> getActivitys(@Param("tid") Integer tid);

    @Select("select count(1) from config_month_recharge where status = '1' and tid = #{tid}")
    int getMonthConfig(@Param("tid") Integer tid);

    @Select("SELECT promotion_domain FROM config_agent where tid = #{tid} limit 0,1")
    String getBeifen(@Param("tid") Integer tid);
}

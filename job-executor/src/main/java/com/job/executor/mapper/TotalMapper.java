package com.job.executor.mapper;

import org.apache.ibatis.annotations.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Mapper
public interface TotalMapper {

    @Delete("delete from data_online")
    void cleanOnline();

    void insertOnline(List list);

    @Select("select COUNT(DISTINCT(uid)) from data_login where time >= #{time}")
    int selectTodayLogins(Long time);

    void saveTodayLogins(@Param("date") String date, @Param("num") int num);

    @Update("update data_register set today_add = '0',today_red = '0',today_give = '0',today_water = '0',today_win = '0'")
    void updateRegister();

    @Delete("delete from data_water where time <= #{time} ")
    void deleteWater(@Param("time") Long time);

    @Select("select t_id from sys_tenant where t_type = '2' ")
    List<String> getChannelList();

    Map getRechargeInfo(@Param("channel") String channel,@Param("time") Long time);

    void saveSummarize(List summarizeList);

    Map getExchangeInfo(@Param("channel") String channel,@Param("time") Long time);

    @Select("SELECT a.newNum, b.balanceCount FROM (select COUNT(DISTINCT(uid)) newNum FROM data_register WHERE channel = #{channel} AND TIME>#{time}) a " +
            "LEFT JOIN (SELECT SUM(safe_box+curr) balanceCount FROM data_register WHERE channel = #{channel}) b on 1=1")
    Map getNewPlayerInfo(@Param("channel") String channel,@Param("time") Long time);

    @Select("SELECT COUNT(DISTINCT uid) FROM data_register WHERE channel = #{channel} AND TIME>#{time} ")
    Integer getActiveNum(@Param("channel") String channel,@Param("time") Long time);

    Map getWaterInfo(@Param("channel") String channel,@Param("time") Long time);

    @Select("select sum(sub_income) commissionSubCount,sum(total_income) commissionTeamCount from agent_commission_day WHERE channel = #{channel} AND day>#{date} ")
    Map getCommissionInfo(@Param("channel") String channel,@Param("date") String date);

    @Select("select sum(bet_coins) betCount,SUM(pay_fee) feeCount from data_card_user where channel = #{channel} and mstime>=#{time} and mstime<#{endtime}")
    Map getBetInfo(@Param("channel") String channel,@Param("time") String time,@Param("endtime") String endtime);

    @Select("SELECT count(distinct uid) winNum FROM data_register where today_win>0 and channel = #{channel} ")
    Integer getWinNum(@Param("channel") String channel);

    @Select("SELECT count(distinct uid) totalNum FROM data_card_user where channel = #{channel} and mstime >=#{time} and mstime<#{endtime}")
    Integer getTotalNum(@Param("channel") String channel,@Param("time") String time,@Param("endtime") String endtime);

    @Select("select sum(value) from data_coins where r in (110001,110002,110003,110004,110005,110006) and channel = #{channel} and mstime >=#{time} and mstime<#{endtime}")
    BigDecimal getActivityCount(@Param("channel") String channel,@Param("time") String time,@Param("endtime") String endtime);

    @Select("select sum(value) from data_coins where r = '100041' and channel = #{channel} and mstime >=#{time} and mstime<#{endtime}")
    BigDecimal getBindGiveCount(@Param("channel") String channel,@Param("time") String time,@Param("endtime") String endtime);

    @Select("select sum(value) from data_coins where r = '100068' and channel = #{channel} and mstime >=#{time} and mstime<#{endtime}")
    BigDecimal getVipLevelCount(@Param("channel") String channel,@Param("time") String time,@Param("endtime") String endtime);

    @Select("select sum(value) from data_coins where r = '100065' and channel = #{channel} and mstime >=#{time} and mstime<#{endtime}")
    BigDecimal getVipCount(@Param("channel") String channel,@Param("time") String time,@Param("endtime") String endtime);

    @Select("select sum(value) from data_coins where r = '100000' and channel = #{channel} and mstime >=#{time} and mstime<#{endtime}")
    BigDecimal getAlmsCount(@Param("channel") String channel,@Param("time") String time,@Param("endtime") String endtime);

    @Select("select SUM(VALUE) giveCount,COUNT(DISTINCT uid) giveNum from data_coins where r IN (100001,100041,100075) and channel = #{channel} and mstime >=#{time} and mstime<#{endtime}")
    Map getGiveCount(@Param("channel") String channel,@Param("time") String time,@Param("endtime") String endtime);

    @Select("select balance_count from data_total_day where channel=#{channel} and day = #{date}")
    BigDecimal getYertodayBalance(@Param("channel")String channel,@Param("date") String yertoday);
}

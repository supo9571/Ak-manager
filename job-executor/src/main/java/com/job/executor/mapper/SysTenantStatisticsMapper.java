package com.job.executor.mapper;

import com.job.executor.domain.SysTenantStatistics;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * @author jason
 * @date 2021-09-28
 */
@Mapper
public interface SysTenantStatisticsMapper {

    /**
     * 获取当前总代和渠道列表
     *
     * @return
     */
    List getSysTenantList();


    /**
     * 根据渠道id获取所有渠道下的用户
     *
     * @param tid
     * @return
     */
    @Select("SELECT user_id from sys_user WHERE t_id = #{tid}")
    List<Long> getUserByChannelIdList(@Param("tid") String tid);


    /**
     * 保存
     *
     * @param statistics
     * @return
     */
    int saveSysTenantStatistics(List<SysTenantStatistics> statistics);

    /**
     * 获取时间端内总充值人数
     *
     * @param tid
     * @param beginTime
     * @param endTime
     * @return
     */
    Map<String, Object> getRechargeAmount(@Param("tid") String tid, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 获取线上金额
     *
     * @param tid
     * @param beginTime
     * @param endTime
     * @return
     */
    Map<String, BigDecimal> getUpAwardAmount(@Param("tid") String tid, @Param("beginTime") String beginTime, @Param("endTime") String endTime);


    /**
     * 获取线下 金额
     *
     * @param tid
     * @param beginTime
     * @param endTime
     * @return
     */
    Map<String, BigDecimal> getLowerAwardAmount(@Param("tid") String tid, @Param("beginTime") String beginTime, @Param("endTime") String endTime);


    /**
     * 获提款金额
     *
     * @param tid
     * @param beginTime
     * @param endTime
     * @return
     */
    Map<String, Object> geWithdrawAmount(@Param("tid") String tid, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 活跃人数
     *
     * @param userList
     * @param beginTime
     * @param endTime
     * @return
     */
    int getUserLoginCount(@Param("userList") List userList, String beginTime, @Param("endTime") String endTime);

    /**
     * 获取首充人数和首充金额
     *
     * @param tid
     * @param beginTime
     * @param endTime
     */
    Map<String, Object> getFirstRechargeAmount(@Param("tid") String tid, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
}

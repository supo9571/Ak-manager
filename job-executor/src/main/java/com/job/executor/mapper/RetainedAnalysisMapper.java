package com.job.executor.mapper;

import com.job.executor.domain.RetainedAnalysis;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 计算留存分析数据
 * @author sieGuang 2021/10/11
 */
@Mapper
public interface RetainedAnalysisMapper {

    // 清空表数据
    @Update("delete from data_retained_analysis")
    void emptyReport();

    // 获取新增留存
    List<RetainedAnalysis> getAddUserList();
    // 活跃留存： （牌局）
    List<RetainedAnalysis> getCardRecordList();
    // 付费留存: (有充值的用户)
    List<RetainedAnalysis> getRechargeUserList();
    // 新增充值留存: (有史以来第一次充值的用户)
    List<RetainedAnalysis> getOneRechargeUserList();
    // 回流用户留存: (回流用户（7天及以上没有登录的玩家）)
    List<RetainedAnalysis> getFlowBackUserList();
    // 获取 获取 次日留存 - 45留数据，通过dayType来区分
    String getRetained(@Param("dto") RetainedAnalysis dto,@Param("dayType") Integer dayType);
    // 结果插入 留存分析表
    void addRetainedAnalysis(List<RetainedAnalysis> list);
}
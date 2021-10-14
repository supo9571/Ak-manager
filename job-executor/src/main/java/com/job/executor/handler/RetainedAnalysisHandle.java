package com.job.executor.handler;

import com.job.core.handler.annotation.XxlJob;
import com.job.executor.domain.RetainedAnalysis;
import com.job.executor.mapper.RetainedAnalysisMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;


/**
 * 计算留存分析数据
 * @author sieGuang 2021/10/11
 */
@Component
@Slf4j
public class RetainedAnalysisHandle {

    @Autowired
    private RetainedAnalysisMapper retainedAnalysisMapper;

    /**
     * 每天00:00执行一次
     */
    @XxlJob("retained_analysis")
    // @PostConstruct
    public void retainedAnalysis() {

        // 每次都清空表数据
        retainedAnalysisMapper.emptyReport();

        // 获取新增留存
        List<RetainedAnalysis> addUserList = retainedAnalysisMapper.getAddUserList();
        // 活跃留存： （牌局）
        List<RetainedAnalysis> cardRecordList = retainedAnalysisMapper.getCardRecordList();
        if(cardRecordList != null){
            for (RetainedAnalysis analysis : cardRecordList) {
                if(analysis.getUids() != null && analysis.getUids().length() >= 0){
                    analysis.setUserCount(analysis.getUids().split(",").length);
                }
            }
        }

        // 付费留存: (有充值的用户)
        List<RetainedAnalysis> rechargeUserList = retainedAnalysisMapper.getRechargeUserList();
        // 新增充值留存: (有史以来第一次充值的用户)
        List<RetainedAnalysis> oneRechargeUserList = retainedAnalysisMapper.getOneRechargeUserList();
        // 回流用户留存: (回流用户（7天及以上没有登录的玩家）)
        List<RetainedAnalysis> flowBackUserList = retainedAnalysisMapper.getFlowBackUserList();

        extracted(addUserList ,1);
        extracted(cardRecordList ,2);
        extracted(rechargeUserList ,3);
        extracted(oneRechargeUserList ,4);
        extracted(flowBackUserList ,5);
    }

    /**
     * 获取 次日留存 - 45留数据
     * 状态 1.新增用户数 2.活跃留存 3.付费留存 4.新增充值留存 5.回流用户留存
     */
    private void extracted(List<RetainedAnalysis> retainedAnalysisList,Integer type) {
        if(retainedAnalysisList != null && !retainedAnalysisList.isEmpty()){
            retainedAnalysisList.forEach(retainedAnalysis ->{
                if(retainedAnalysis.getUids() != null && retainedAnalysis.getUids().length() > 0){
                    retainedAnalysis.setRetained1(retainedAnalysisMapper.getRetained(retainedAnalysis,1));
                    retainedAnalysis.setRetained2(retainedAnalysisMapper.getRetained(retainedAnalysis,2));
                    retainedAnalysis.setRetained3(retainedAnalysisMapper.getRetained(retainedAnalysis,3));
                    retainedAnalysis.setRetained4(retainedAnalysisMapper.getRetained(retainedAnalysis,4));
                    retainedAnalysis.setRetained5(retainedAnalysisMapper.getRetained(retainedAnalysis,5));
                    retainedAnalysis.setRetained6(retainedAnalysisMapper.getRetained(retainedAnalysis,6));
                    retainedAnalysis.setRetained7(retainedAnalysisMapper.getRetained(retainedAnalysis,7));
                    retainedAnalysis.setRetained15(retainedAnalysisMapper.getRetained(retainedAnalysis,15));
                    retainedAnalysis.setRetained30(retainedAnalysisMapper.getRetained(retainedAnalysis,30));
                    retainedAnalysis.setRetained45(retainedAnalysisMapper.getRetained(retainedAnalysis,45));
                    retainedAnalysis.setType(type);
                }
            });
            retainedAnalysisMapper.addRetainedAnalysis(retainedAnalysisList);
        }
    }
}

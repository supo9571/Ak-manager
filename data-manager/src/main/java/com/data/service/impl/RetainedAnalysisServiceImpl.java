package com.data.service.impl;

import com.data.mapper.AddUserMapper;
import com.data.mapper.RetainedAnalysisMapper;
import com.data.service.RetainedAnalysisService;
import com.manager.common.core.domain.model.AddUser;
import com.manager.common.core.domain.model.RetainedAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

/**
 * 留存分析
 * @author sieGuang 2021/10/13
 */
@Service
public class RetainedAnalysisServiceImpl implements RetainedAnalysisService {

    @Autowired
    private RetainedAnalysisMapper retainedAnalysisMapper;

    @Autowired
    private AddUserMapper addUserMapper;

    @Override
    public List getList(RetainedAnalysis retainedAnalysis) {

        // 默认查看当前身份的总数据
        Long tid = addUserMapper.getTid(retainedAnalysis.getTid2());
        retainedAnalysis.setTid2(tid);

        List<RetainedAnalysis> list = retainedAnalysisMapper.getList(retainedAnalysis);
        Integer userCount;

        for (RetainedAnalysis analysis : list) {
            if(analysis.getUserCount() > 0){
                userCount = analysis.getUserCount();
                analysis.setRetained1(extracted(analysis.getRetained1(),userCount));
                analysis.setRetained2(extracted(analysis.getRetained2(),userCount));
                analysis.setRetained3(extracted(analysis.getRetained3(),userCount));
                analysis.setRetained4(extracted(analysis.getRetained4(),userCount));
                analysis.setRetained5(extracted(analysis.getRetained5(),userCount));
                analysis.setRetained6(extracted(analysis.getRetained6(),userCount));
                analysis.setRetained7(extracted(analysis.getRetained7(),userCount));
                analysis.setRetained15(extracted(analysis.getRetained15(),userCount));
                analysis.setRetained30(extracted(analysis.getRetained30(),userCount));
                analysis.setRetained45(extracted(analysis.getRetained45(),userCount));
            }
        }
        return list;
    }

    // 算百分比
    private String extracted(String retained,Integer userCount) {
        Double d = (Double.parseDouble(retained) / userCount.doubleValue()) * 100;

        // 处理小数位，保留两位小数
        DecimalFormat df = new DecimalFormat("#.00");
        String str = df.format(d);
        return retained + "(" + str + "%)";
    }
}


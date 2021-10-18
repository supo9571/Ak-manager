package com.data.service.impl;

import com.data.mapper.AddUserMapper;
import com.data.mapper.LtvReportMapper;
import com.data.service.LtvReportService;
import com.manager.common.core.domain.model.LtvReport;
import com.manager.common.core.domain.model.RetainedAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Ltv报表
 * @author sieGuang 2021/10/15
 */
@Service
public class LtvReportServiceImpl implements LtvReportService {

    @Autowired
    private LtvReportMapper ltvReportMapper;

    @Autowired
    private AddUserMapper addUserMapper;

    @Override
    public List getList(LtvReport ltvReport) {

        // 默认查看当前身份的总数据
        Long tid = addUserMapper.getTid(ltvReport.getTid2());
        ltvReport.setTid2(tid);

        List<LtvReport> list = ltvReportMapper.getList(ltvReport);

        // 处理小数位，保留两位小数
        DecimalFormat df = new DecimalFormat("#.00");
        String recharge45;
        for (LtvReport ltvReport1 : list) {
            if(ltvReport1.getUserCount() > 0){
                ltvReport1.setRecharge1(extracted(ltvReport1,1));
                ltvReport1.setRecharge2(extracted(ltvReport1,2));
                ltvReport1.setRecharge3(extracted(ltvReport1,3));
                ltvReport1.setRecharge4(extracted(ltvReport1,4));
                ltvReport1.setRecharge5(extracted(ltvReport1,5));
                ltvReport1.setRecharge6(extracted(ltvReport1,6));
                ltvReport1.setRecharge7(extracted(ltvReport1,7));
                ltvReport1.setRecharge8(extracted(ltvReport1,8));
                ltvReport1.setRecharge9(extracted(ltvReport1,9));
                ltvReport1.setRecharge10(extracted(ltvReport1,10));
                ltvReport1.setRecharge11(extracted(ltvReport1,11));
                ltvReport1.setRecharge12(extracted(ltvReport1,12));
                ltvReport1.setRecharge13(extracted(ltvReport1,13));
                ltvReport1.setRecharge14(extracted(ltvReport1,14));
                ltvReport1.setRecharge30(extracted(ltvReport1,30));

                // 处理平均数（第45天 / 用户数）
                recharge45 = extracted(ltvReport1,45);
                if(Double.parseDouble(recharge45) > 0){
                    ltvReport1.setRecharge45(recharge45);
                    ltvReport1.setAverageNum(df.format((Double.parseDouble(recharge45) / ltvReport1.getUserCount()) * 100));
                }
            }
        }
        return list;
    }

    // 通过新增日和新增用户 获取第X天的数据
    private String extracted(LtvReport ltvReport,Integer recharge) {
        String Recharge = ltvReportMapper.getRecharge(ltvReport.getDay(),ltvReport.getUids(),recharge);
        return Recharge;
    }
}


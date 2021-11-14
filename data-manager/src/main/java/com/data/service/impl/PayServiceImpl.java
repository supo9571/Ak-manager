package com.data.service.impl;

import com.data.mapper.PayMapper;
import com.data.mapper.TenantMapper;
import com.data.service.PayService;
import com.manager.common.utils.uuid.IdUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author marvin 2021/9/10
 */
@Service
public class PayServiceImpl implements PayService {

    @Autowired
    private PayMapper payMapper;
    @Autowired
    private TenantMapper tenantMapper;

    /**
     * 添加 银行卡充值 申请
     *
     * @param uid
     * @param name
     * @param money
     * @param channel
     * @return
     */
    @Override
    public Integer saveBankReg(String uid, String name, Integer money, String channel) {
        return payMapper.saveBankReg(uid, name, money, channel, IdUtils.getOrderId(), tenantMapper.getTidByCid(channel));
    }

    /**
     * 判断是否重复
     */
    @Override
    public Integer isNoRepeat(String uid, String channel) {
        return payMapper.isNoRepeat(uid,tenantMapper.getTidByCid(channel));
    }
}

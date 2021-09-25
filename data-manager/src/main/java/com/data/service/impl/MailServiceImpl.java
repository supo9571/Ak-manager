package com.data.service.impl;

import com.data.mapper.MailMapper;
import com.data.mapper.TenantMapper;
import com.data.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author marvin 2021/9/25
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private MailMapper mailMapper;

    @Autowired
    private TenantMapper tenantMapper;

    @Override
    public List getTips(String channelId,String uid) {
        return mailMapper.getTips(channelId,uid,tenantMapper.getTidByCid(channelId));
    }

    @Override
    public List getMailList(String channelId, String uid) {
        Integer tid = tenantMapper.getTidByCid(channelId);
        saveMailRecord(channelId,uid,tid);
        return mailMapper.getMailList(uid,tid);
    }

    @Override
    public void readMail(String ids) {
        mailMapper.readMail(ids);
    }

    /**
     * 生成邮件记录
     */
    private void saveMailRecord(String channelId, String uid,Integer tid){
        List list = mailMapper.getMailConfig(channelId,uid,tid);
        if(list!=null && list.size()>0){
            mailMapper.saveMailRecord(list,uid);
        }
    }
}

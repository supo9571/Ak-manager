package com.data.service.impl;

import com.data.mapper.TenantMapper;
import com.data.mapper.UserMapper;
import com.data.service.UserService;
import com.manager.common.core.domain.entity.DataUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author marvin 2021/8/28
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TenantMapper tenantMapper;

    @Override
    public Integer findByphone(String phone) {
        return userMapper.findByphone(phone);
    }

    @Override
    public Map getConfigRegisterConstraint(Integer tid) {
        return userMapper.getConfigRegisterConstraint(tid);
    }


    @Override
    public int getIpUserCount(Integer tid, String ip) {
        return userMapper.getIpUserCount(tid, ip);
    }

    @Override
    public int getSeedTokenUserCount(Integer tid, String seedToken) {
        return userMapper.getSeedTokenUserCount(tid, seedToken);
    }

    @Override
    public Integer insertToDataUser(DataUser dataUser) {
        return userMapper.insertToDataUser(dataUser);
    }

    @Override
    public Integer loadDataUserName(DataUser dataUser) {
        return userMapper.loadDataUserName(dataUser);
    }

    @Override
    public DataUser findUserBySeedToken(String seedToken) {
        return userMapper.findUserBySeedToken(seedToken);
    }

    @Override
    public DataUser findByPassword(String phoneNumber, String password,String channel) {
        return userMapper.findByPassword(phoneNumber, password,tenantMapper.getTidByCid(channel));
    }

    @Override
    public DataUser findByPhone(String phoneNumber) {
        return userMapper.findByPhone(phoneNumber);
    }

    @Override
    public void updatePassword(String phoneNumber, String password) {
        userMapper.updatePassword(phoneNumber, password);
    }

    @Override
    public int updateDataUser(DataUser dataUser) {
        return userMapper.updateDataUser(dataUser);
    }

    @Override
    public Map selectLock(String uid) {
        return userMapper.selectLock(uid);
    }

    @Override
    public Map becomeAgent(String accountId) {
        return userMapper.becomeAgent(accountId);
    }

    @Override
    public int checkBlack(String uid, String matchineId, String ip,String channel) {
        Integer tid = tenantMapper.getTidByCid(channel);
        //查询是否 符合黑名单
        Map blackMap = userMapper.checkBlack(uid,matchineId,ip);
        if(blackMap!=null){
            userMapper.saveBlackInfo(tid,uid,blackMap.get("blackType"),blackMap.get("blackNum"),blackMap.get("handleType"));
            if(1 ==(Integer) blackMap.get("handleType")){//预警
                return 0;
            }else{
                return 1;
            }
        }
        return 0;
    }

}

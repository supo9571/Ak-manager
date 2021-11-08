package com.data.service;

import com.manager.common.core.domain.entity.DataUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/28
 */
public interface UserService {
    Integer findByphone(String phone);

    Map getConfigRegisterConstraint(Integer tid);

    int getIpUserCount(Integer tid,String ip);

    int getSeedTokenUserCount(Integer tid, String seedToken);

    Integer insertToDataUser(DataUser dataUser);

    Integer loadDataUserName(DataUser dataUser);

    DataUser findUserBySeedToken(String seedToken);

    DataUser findByPassword(String phoneNumber, String password,String channel);

    DataUser findByPhone(String phoneNumber);

    void updatePassword(String phoneNumber, String password);

    int updateDataUser(DataUser dataUser);

    Map selectLock(String uid);

    Map becomeAgent(String accountId);

    int checkBlack(String uid, String matchineId, String ip,String channel);
}

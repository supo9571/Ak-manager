package com.data.service;

import com.manager.common.core.domain.entity.DataUser;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/28
 */
public interface UserService {
    Integer findByphone(String phone);

    Integer insertToDataUser(DataUser dataUser);

    Integer loadDataUserName(DataUser dataUser);

    DataUser findUserBySeedToken(String seedToken);

    DataUser findByPassword(String phoneNumber, String password);

    DataUser findByPhone(String phoneNumber);

    void updatePassword(String phoneNumber, String password);

    int updateDataUser(DataUser dataUser);

    Map selectLock(String uid);
}

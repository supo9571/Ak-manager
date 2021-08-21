package com.data.service;

import com.manager.common.core.domain.model.Login;

import java.util.List;

/**
 * @author marvin 2021/8/21
 */
public interface Loginservice {
    List selectLogin(Login login);

    int selectTodayLogins();

    List selectLoginCounts(String type);
}

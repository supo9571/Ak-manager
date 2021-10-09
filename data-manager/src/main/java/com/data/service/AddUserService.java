package com.data.service;

import com.manager.common.core.domain.model.AddUser;
import com.manager.common.core.domain.model.SubGameData;

import java.util.List;

/**
 * 新增用户
 * @author sieGuang 2021/10/08
 */
public interface AddUserService {

    /**
     * 查询
     */
    List<AddUser> getList(AddUser addUser);
}

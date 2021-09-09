package com.manager.common.core.domain.entity;

import lombok.Data;

/**
 * @author LV
 * @date 20:13 2021/9/8
 * @return
 **/
@Data
public class DataUser {
    private long account_id;
    private String phone;
    private String password;
}

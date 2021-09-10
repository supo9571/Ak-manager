package com.manager.common.core.domain.entity;

import lombok.Data;

/**
 * @author LV
 * @date 20:13 2021/9/8
 * @return
 **/
@Data
public class DataUser {
    private long accountId;
    private String phone;
    private String password;
    private String client_ip;
    private String seed_token;
    private String package_channel;
}

package com.manager.common.core.domain.entity;

import lombok.Data;

/**
 * @author LV
 * @date 20:13 2021/9/8
 * @return
 **/
@Data
public class DataUser{
    private Long accountId;
    private String phone;
    private String password;
    private String client_ip;
    private String seed_token;
    private String package_channel;

    public DataUser(Long accountId, String phone, String password) {
        this.accountId = accountId;
        this.phone = phone;
        this.password = password;
    }

    public DataUser() {
    }

    public DataUser(String phone, String password, String client_ip, String seed_token, String package_channel) {
        this.phone = phone;
        this.password = password;
        this.client_ip = client_ip;
        this.seed_token = seed_token;
        this.package_channel = package_channel;
    }
}

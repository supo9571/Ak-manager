package com.data.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;


@Component
@ConfigurationProperties(prefix = "global-config")
@Data
public class GlobalConfig {

    private boolean verSwitch;

    private String apiUrl;

    private String errUploadUrl;

    private String headUrl;

    private String resourcesUrl;

    private Map<String, String> gameConfig;

    private String profile;

    //游戏服 发送配置地址
    private String domain;
    //发送游戏配置
    private String gameSend;

    //游戏服 通讯地址
    private String reportDomain;
    //操作金币
    private String changeCoins;
    //发邮件
    private String mail;
    //驳回提现请求用户返金币
    private String returnBack;

    private String onlinePlay;
}

package com.manager.common.core.domain.model;

import lombok.Data;

/**
 * @author marvin 2021/8/30
 * 热更新 config_package
 */
@Data
public class Hotupdate {

    private Integer id;

    private String version;

    private Integer verInt;

    private String allowChannel;

    private String denyChannel;

    private String allowVersion;

    private String denyVersion;

    private String isPublic;

    private String gameInfo;

    private String platform;

    private String status;

    private String releaseTime;

    private String updateTime;

    private String createdTime;

    private String apkUpdateUrl;

    private String size;
}

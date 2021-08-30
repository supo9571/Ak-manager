package com.manager.common.core.domain.model;

import lombok.Data;

/**
 * @author marvin 2021/8/30
 * 整包更新 config_update
 */
@Data
public class Allupdate {

    private int id;

    private String tid;//平台id

    private String status;

    private String version;

    private Integer verInt;

    private String apkUpdateUrl;

    private String updateTime;

    private String size;
}

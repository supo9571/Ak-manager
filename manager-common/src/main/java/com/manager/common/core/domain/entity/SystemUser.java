package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户对象 sys_user
 *
 * @author marvin
 */
@Data
public class SystemUser extends BaseEntity {

    @ApiModelProperty(value = "用户id")
    private String userId;

    @ApiModelProperty(value = "用户名")
    private String userName;

    @ApiModelProperty(value = "姓名")
    private String nickName;

    @ApiModelProperty(value = "平台id")
    private String tId;

    @ApiModelProperty(value = "账号状态")
    private String status;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "google验证开关")
    private String googleSwitch;

    @ApiModelProperty(value = "google密钥")
    private String googleKey;

    @ApiModelProperty(value = "IP白名单")
    private String ips;

    @ApiModelProperty(value = "角色id")
    private String roleId;

    public static boolean isAdmin(Long userId) {
        return true?userId==1:false;
    }

    public boolean getGoogleSwitch() {
        return true?"1".equals(googleSwitch):false;
    }


}

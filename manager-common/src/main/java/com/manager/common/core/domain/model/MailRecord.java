package com.manager.common.core.domain.model;

import lombok.Data;

/**
 * 邮件
 */
@Data
public class MailRecord {

    /**
     * 平台id
     */
    private String tid;

    /**
     * 署名
     */
    private String signature;

    /**
     * 收件人
     */
    private String addressee;

    /**
     * 邮件标题
     */
    private String mailTitle;

    /**
     * 邮件道具
     */
    private String mailProp;

    /**
     * 邮箱内容
     */
    private String mailContent;

    /**
     * 邮件状态 1已读 2未读 3已经领取 4未领取 5待发送 6撤回
     */
    private String mailState;

    /**
     * 个人邮箱id
     */
    private String personalMailId;

    /**
     * 删除标志（0代表存在 2代表删除）
     */
    private String delFlag;

}

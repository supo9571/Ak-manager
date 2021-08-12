package com.manager.common.core.domain.entity;

import com.manager.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 白名单 sys_ip_while
 *
 * @author marvin
 */
@Data
public class SysIpWhite extends BaseEntity {
    private long id;

    private long tId;

    private long userId;

    private String ip;

    private long createUserId;

    private String creatTime;

    public SysIpWhite() {
    }

    public SysIpWhite(long tId, long userId, long createUserId, String ip) {
        this.tId = tId;
        this.userId = userId;
        this.ip = ip;
        this.createUserId = createUserId;
    }
}

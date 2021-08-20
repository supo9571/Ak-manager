package com.manager.common.core.domain.model;

import com.manager.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * @author marvin 2021/8/20
 */
@Data
public class OnlinePlayer extends BaseEntity {

    private Long uid;
    private String name;
    private int gameType;
    private int tableType;
    private Long coins;

}

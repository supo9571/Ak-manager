package com.manager.common.core.domain.model;

import com.manager.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * @author marvin 2021/8/18
 */
@Data
public class CardRecord extends BaseEntity {

    private String key;
    private String op;
    private Long time;
    private Long mstime;
}

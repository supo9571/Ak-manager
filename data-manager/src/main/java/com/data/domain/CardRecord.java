package com.data.domain;

import com.data.domain.common.Params;
import lombok.Data;

/**
 * @author marvin 2021/8/18
 */
@Data
public class CardRecord extends Params {

    private String key;
    private String op;
    private Long time;
    private Long mstime;
}

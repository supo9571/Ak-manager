package com.consumer.domain;

import lombok.Data;

/**
 * @author marvin 2021/8/18
 */
@Data
public class CardRecord {

    private String key;
    private String op;
    private Long time;
    private Long mstime;
}

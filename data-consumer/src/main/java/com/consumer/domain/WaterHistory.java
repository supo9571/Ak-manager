package com.consumer.domain;

import lombok.Data;

/**
 * @author marvin 2021/9/17
 */
@Data
public class WaterHistory {

    private String op;

    private String key;

    private Long uid;

    private Long time;

    private Long mstime;

    private int r; //加钱原因

    private Long value;// 下注流水

    private Long reward;// 返奖流水

}

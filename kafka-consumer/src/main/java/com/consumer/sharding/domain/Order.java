package com.consumer.sharding.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author marvin 2021/8/18
 */
@Data
public class Order implements Serializable {

    private static final long serialVersionUID = -8759492936340749287L;
    private String orderNo;

    private String sysTime;
}

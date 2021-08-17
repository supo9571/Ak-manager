package com.consumer.enums;

/**
 * @author marvin 2021/8/17
 */
public enum OpEnum {

    REGISTER("register"),
    REDUCECOINS("reducecoins"),
    ADDCOINS("addcoins")
    ;

    OpEnum(String opName) {
        this.opName = opName;
    }

    private String opName;

    public String getOpName() {
        return opName;
    }

}

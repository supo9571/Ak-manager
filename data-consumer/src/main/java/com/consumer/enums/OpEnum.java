package com.consumer.enums;

/**
 * @author marvin 2021/8/17
 */
public enum OpEnum {

    //注册
    REGISTER("register"),
    //减金币
    REDUCECOINS("reducecoins"),
    //加金币
    ADDCOINS("addcoins"),
    //登录
    LOGIN("login"),
    //登出
    LOGOUT("logout"),
    //牌局记录
    CARD_RECORD("card_record"),
    //流水记录
    WATER_HISTORY("water_history");

    OpEnum(String opName) {
        this.opName = opName;
    }

    private String opName;

    public String getOpName() {
        return opName;
    }

    public static OpEnum getByValue(String opName) {
        for (OpEnum opEnum : values()) {
            if (opEnum.getOpName().equalsIgnoreCase(opName)) {
                return opEnum;
            }
        }
        return null;
    }
}

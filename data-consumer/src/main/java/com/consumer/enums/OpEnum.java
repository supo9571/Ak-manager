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
    //
    TAKE_OUT_SAFE_BOX("take_out_safe_box"),
    //
    DESPOSIT_SAFE_BOX("desposit_safe_box"),
    //
    REDUCE_ITEM("reduce_item"),
    //
    LOGOUT("logout"),
    //登录
    LOGIN("login"),
    //
    ONLINE_PLAYER("online_player"),
    //
    PLAYER_BIND_PHONE("player_bind_phone"),
    //
    RMBBUY("rmbbuy"),
    //
    USER_LEAVE_GAME("user_leave_game"),
    //牌局记录
    CARD_RECORD("card_record"),
    //
    STOP_TABLE("stop_table"),
    //
    CLOSE_TABLE("close_table"),
    //
    DESKHELPER_CLOSE_TABLE("deskHelper.close_table"),
    //
    ENTER("enter"),
    //
    WATER_HISTORY("water_history"),

    BACK_COINS("back_coins"),

    YKCHARGE_PRO3("ykcharge_pro3"),

    ADD_ITEM("add_item"),

    SYNC_WINLOSE("sync_winlose");

    OpEnum(String opName) {
        this.opName = opName;
    }

    private String opName;

    public String getOpName() {
        return opName;
    }

    public static OpEnum getByValue(String opName) {
        for (OpEnum opEnum : values()) {
            if (opEnum.getOpName() .equalsIgnoreCase(opName)) {
                return opEnum;
            }
        }
        return null;
    }
}

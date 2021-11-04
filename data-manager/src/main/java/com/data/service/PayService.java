package com.data.service;

/**
 * @author marvin 2021/9/10
 */
public interface PayService {

    Integer saveBankReg(String uid, String name, Integer money, String channel);

    Integer isNoRepeat(String uid);
}

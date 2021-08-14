package com.consumer.service;

public interface DataService {

    void updateUserOnline(String value,String time);

    void insertMsg(String key, String op, String msg);
}

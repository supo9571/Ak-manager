package com.data.service;

import java.util.List;

/**
 * @author marvin 2021/8/27
 */
public interface GameService {
    List getGames();

    void saveIp(String ip, String createBy);

    List findIp(String ip, String createBy, String beginTime, String endTime);

    void delIp(Integer id);
}

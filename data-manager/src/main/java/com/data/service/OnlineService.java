package com.data.service;

import com.manager.common.core.domain.model.OnlinePlayer;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/21
 */
public interface OnlineService {
    List selectOnline(OnlinePlayer onlinePlayer);

    Map selectOnlineCount(OnlinePlayer onlinePlayer);
}

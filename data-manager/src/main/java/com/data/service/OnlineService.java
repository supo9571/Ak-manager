package com.data.service;

import com.manager.common.core.domain.model.OnlinePlayer;

import java.util.List;

/**
 * @author marvin 2021/8/21
 */
public interface OnlineService {
    List selectOnline(OnlinePlayer onlinePlayer);
}

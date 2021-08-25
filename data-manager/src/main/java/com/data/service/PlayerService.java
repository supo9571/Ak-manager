package com.data.service;

import com.manager.common.core.domain.model.PlayUser;

import java.util.List;

/**
 * @author marvin 2021/8/20
 */
public interface PlayerService {
    List selectPlayer(PlayUser playUser);

    List selectPlayerCurr(Long uid);
}

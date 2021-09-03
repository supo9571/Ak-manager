package com.data.service;

import com.manager.common.core.domain.model.Game;

import java.util.List;

/**
 * 子游戏管理
 * @author sieGuang 2021/09/03
 */
public interface SubGameService {

    /**
     * 查询
     * @param game 过滤条件
     */
    List getSubGameList(Game game);

    /**
     * 编辑
     * @param game 需要修改的内容
     */
    int editSubGame(Game game);
}

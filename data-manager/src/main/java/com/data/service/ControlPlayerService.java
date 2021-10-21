package com.data.service;

import com.manager.common.core.domain.model.ControlPlayer;
import com.manager.common.core.domain.model.ControlPlayerInfo;

import java.util.List;

/**
 * @author marvin 2021/10/20
 */
public interface ControlPlayerService {

    void add(ControlPlayer controlPlayer);

    boolean checkUid(ControlPlayer controlPlayer);

    List list(ControlPlayer controlPlayer);

    List infoList(ControlPlayerInfo controlPlayerInfo);

    void edit(ControlPlayer controlPlayer);

    void del(ControlPlayer controlPlayer);
}

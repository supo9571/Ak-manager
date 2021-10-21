package com.data.mapper;

import com.manager.common.core.domain.model.ControlPlayer;
import com.manager.common.core.domain.model.ControlPlayerInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/10/20
 */
@Mapper
public interface ControlPlayerMapper {

    void add(ControlPlayer controlPlayer);

    Integer checkUid(ControlPlayer controlPlayer);

    Integer isExist(ControlPlayer controlPlayer);

    void addInfo(ControlPlayerInfo controlPlayerInfo);

    List<ControlPlayer> list(ControlPlayer controlPlayer);

    List infoList(ControlPlayerInfo controlPlayerInfo);

    ControlPlayer select(@Param("uid") Long uid);

    void edit(ControlPlayer controlPlayer);

    void del(@Param("uid") Long uid);

    List<Map> selectBet(@Param("uid") Long uid);
}

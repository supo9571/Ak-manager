package com.data.mapper;

import com.manager.common.core.domain.model.OnlinePlayer;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/21
 */
@Mapper
public interface OnlineMaper {
    List selectOnline(OnlinePlayer onlinePlayer);

    @Delete("delete from data_online")
    void cleanOnline();

    void insertOnline(List list);

    Map selectOnlineCount(OnlinePlayer onlinePlayer);
}

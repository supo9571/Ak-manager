package com.data.mapper;

import com.manager.common.core.domain.model.OnlinePlayer;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author marvin 2021/8/21
 */
@Mapper
public interface OnlineMaper {
    List selectOnline(OnlinePlayer onlinePlayer);
}

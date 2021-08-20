package com.data.mapper;

import com.manager.common.core.domain.model.PlayUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author marvin 2021/8/20
 */
@Mapper
public interface PlayerMapper {

    List selectPlayer(PlayUser playUser);
}

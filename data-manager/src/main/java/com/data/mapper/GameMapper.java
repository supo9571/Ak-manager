package com.data.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/27
 */
@Mapper
public interface GameMapper {

    @Select("select game_id gameType,game_name gameName,parent_id parentId from config_game ")
    List<Map> getGames();
}

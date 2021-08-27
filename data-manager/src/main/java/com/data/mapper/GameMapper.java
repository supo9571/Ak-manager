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

    @Select("select game_id gameType,game_name gameName from config_game where parent_id in(0,-1) ")
    List<Map> getGames();
}

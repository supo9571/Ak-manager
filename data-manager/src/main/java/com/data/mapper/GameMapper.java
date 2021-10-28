package com.data.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/27
 */
@Mapper
public interface GameMapper {

    @Select("select game_id gameType,game_name gameName,parent_id parentId from config_game ")
    List<Map> getGames();

    @Insert("insert into config_ip(ip,create_by,create_time) values (#{ip},#{createBy},sysdate())")
    void saveIp(@Param("ip") String ip, @Param("createBy") String createBy);

    List<Map> findIp(@Param("ip") String ip, @Param("createBy") String createBy, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    @Delete("delete from config_ip where id = #{id}")
    void delIp(Integer id);

    @Select("SELECT tid, user_sort position,game_id game_type,1 shown_type,game_bs notice_type,status FROM config_game WHERE STATUS !='3' AND parent_id = '0' order by tid")
    List<Map> getGamesConfig();
}

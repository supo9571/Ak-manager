package com.data.mapper;

import com.manager.common.core.domain.model.Card;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/25
 */
@Mapper
public interface CardMapper {

    List<Map> selectCard(Card card);

    List<Map> selectCardUser(Card card);

    Map selectCardCount(Card card);

    List selectCardUserCount(Card card);

    Map findList(@Param("tableGid") String tableGid);

    List<Map> findUserInfo(@Param("tableGid") String tableGid);

    @Select("SELECT GROUP_CONCAT(t_id SEPARATOR ',') FROM sys_tenant WHERE t_type = '2' AND (ancestors LIKE CONCAT('%', #{tid}, '%') or t_id = #{tid})")
    String getChannel(@Param("tid") String tid);
}

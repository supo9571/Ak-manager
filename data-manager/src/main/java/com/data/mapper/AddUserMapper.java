package com.data.mapper;

import com.manager.common.core.domain.model.AddUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 新增用户
 * @author sieGuang 2021/10/08
 */
@Mapper
public interface AddUserMapper {

    /**
     * 通过userId获取Tid
     * @param userId
     * @return
     */
    @Select("SELECT su.t_id from sys_user su where su.user_id = #{userId} limit 1")
    Integer getTid(@Param("userId") Long userId);

    /**
     * 查询
     * @param addUser 过滤条件
     */
    List<AddUser> getList(AddUser addUser);


    /**
     * 获取某天的新增的用户id
     * @param day
     * @return
     */
    List<String> getUserList(@Param("day") String day);

    /**
     * 注册玩牌率/新增用户牌局数 需要从data_card_user表获取(不能用子查询)
     * @param day
     * @return
     */
    Integer getCardPlayingRate(@Param("list") List<String> list,@Param("day") String day);

    Integer getUserCardCount(@Param("list") List<String> list,@Param("day") String day);


}

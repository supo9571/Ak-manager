package com.data.mapper;

import com.manager.common.core.domain.model.AddUser;
import com.manager.common.core.domain.model.SubGameData;
import com.manager.common.core.domain.model.SubGameDataExcel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 新增用户
 * @author sieGuang 2021/10/08
 */
@Mapper
public interface AddUserMapper {

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
    AddUser getCardList(@Param("list") List<String> list,@Param("day") String day);


}

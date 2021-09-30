package com.data.mapper;

import com.manager.common.core.domain.model.Game;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * 子游戏管理
 *
 * @author sieGuang 2021/09/03
 */
@Mapper
public interface SubGameMapper {

    /**
     * 查询
     *
     * @param game 过滤条件
     */
    List<Map> getSubGameList(Game game);

    /**
     * 编辑
     *
     * @param game 需要修改的内容
     */
    int editSubGame(Game game);

    @Select("select count(0) from config_ip")
    Integer getIpCount();
}

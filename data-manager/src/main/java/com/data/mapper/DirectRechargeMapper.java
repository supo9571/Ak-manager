package com.data.mapper;

import com.manager.common.core.domain.model.AddUser;
import com.manager.common.core.domain.model.DirectRecharge;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 直属玩家充值报表
 * @author sieGuang 2021/10/016
 */
@Mapper
public interface DirectRechargeMapper {

    /**
     * 查询
     * @param directRecharge 过滤条件
     */
    List<DirectRecharge> getList(DirectRecharge directRecharge);

    DirectRecharge getSubList(DirectRecharge directRecharge);

    List<DirectRecharge> getSubList2(@Param("uid") String uid,@Param("agentTime") String agentTime);

}

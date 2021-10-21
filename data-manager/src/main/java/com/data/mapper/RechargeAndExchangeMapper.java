package com.data.mapper;

import com.manager.common.core.domain.model.AddUser;
import com.manager.common.core.domain.model.RechargeAndExchange;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 充值和提现统计
 * @author sieGuang 2021/10/19
 */
@Mapper
public interface RechargeAndExchangeMapper {

    /**
     * 查询
     * @param rechargeAndExchange 过滤条件
     */
    List<RechargeAndExchange> getList(RechargeAndExchange rechargeAndExchange);

}

package com.data.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author marvin 2021/9/10
 */
@Mapper
public interface PayMapper {

    Integer saveBankReg(@Param("uid") String uid, @Param("name") String name, @Param("money") Integer money,
                        @Param("channel") String channel, @Param("orderId") String orderId, @Param("tid") Integer tid);

    @Select("SELECT COUNT(1) from config_recharge_order cro where cro.recharge_type = '2' and cro.uid = #{uid} and cro.payment_status = '2'")
    Integer isNoRepeat(@Param("uid") String uid);
}

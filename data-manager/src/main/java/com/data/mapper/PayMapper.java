package com.data.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author marvin 2021/9/10
 */
@Mapper
public interface PayMapper {

    Integer saveBankReg(@Param("uid") String uid, @Param("name") String name, @Param("money") Integer money,
                        @Param("channel") String channel, @Param("orderId") String orderId, @Param("tid") Integer tid);
}

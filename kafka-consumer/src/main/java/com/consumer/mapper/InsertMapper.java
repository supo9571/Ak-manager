package com.consumer.mapper;

import com.consumer.domain.Coins;
import com.consumer.domain.Register;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * @author marvin 2021/8/17
 */
@Mapper
public interface InsertMapper {

    void insertReg(Register register);

    void insertAddcoins(Coins addCoins);

    void insertReduceCoins(Coins reduceCoins);

    @Update("update data_register set curr =#{curr} where uid = #{uid}")
    void updateCurr(@Param("uid") Long uid,@Param("curr") Long curr);
}

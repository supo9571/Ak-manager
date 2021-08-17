package com.consumer.mapper;

import com.consumer.domain.Coins;
import com.consumer.domain.Register;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author marvin 2021/8/17
 */
@Mapper
public interface InsertMapper {

    void insertReg(Register register);

    void insertAddcoins(Coins addCoins);

    void insertReduceCoins(Coins reduceCoins);
}

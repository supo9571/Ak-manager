package com.data.mapper;

import com.data.domain.Coins;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author marvin 2021/8/19
 */
@Mapper
public interface CoinsMapper {

//    @Select("select * from data_coins")
    List selectCoins(Coins coins);
}

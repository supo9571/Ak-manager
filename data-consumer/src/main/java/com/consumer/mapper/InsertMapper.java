package com.consumer.mapper;

import com.consumer.domain.Card;
import com.consumer.domain.Coins;
import com.consumer.domain.Login;
import com.consumer.domain.Register;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/17
 */
@Mapper
public interface InsertMapper {

    void insertReg(Register register);

    void insertAddcoins(Coins addCoins);

    void insertReduceCoins(Coins reduceCoins);

    @Update("update data_register set curr =#{curr} where uid = #{uid}")
    void updateCurrByAdd(@Param("uid") Long uid,@Param("curr") Long curr);

    @Update("update data_register set curr =#{curr},safe_box = #{safeBox} where uid = #{uid}")
    void updateCurrByRed(@Param("uid") Long uid,@Param("curr") Long curr,@Param("safeBox") Long safeBox);

    void insertLogin(Login login);

    @Update("update data_register set login_ip = #{ip},login_device_id = #{deviceId},login_device_brand = #{deviceBrand}," +
            "vip_level = #{vipLevel},login_time = #{time} where uid = #{uid}")
    void updateUser(@Param("uid") Long uid, @Param("ip") String ip,
                    @Param("deviceId") String deviceId, @Param("deviceBrand")String deviceBrand,@Param("vipLevel") int vipLevel,@Param("time") Long time);

    void insertLogout(Login login);

    @Select("select * from data_coins where mstime BETWEEN #{beginTime} AND #{endTime}")
    List<Map> selectCoins(@Param("beginTime") Long i, @Param("endTime") Long l);

    void insertCard(Card card);
}

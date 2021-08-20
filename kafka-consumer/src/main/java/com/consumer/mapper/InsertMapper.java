package com.consumer.mapper;

import com.consumer.domain.Coins;
import com.consumer.domain.Login;
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

    void insertLogin(Login login);

    @Update("update data_register set login_ip =#{ip},login_device_id=#{deviceId},login_device_brand = #{deviceBrand}," +
            "vip_level = #{vipLevel} where uid = #{uid}")
    void updateUser(@Param("uid") Long uid, @Param("ip") String ip,
                    @Param("deviceId") String deviceId, @Param("deviceBrand")String deviceBrand,@Param("vipLevel") int vipLevel);

    void insertLogout(Login login);
}

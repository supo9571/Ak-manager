package com.data.mapper;

import com.manager.common.core.domain.entity.DataUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.Map;

/**
 * @author marvin 2021/8/28
 */
@Mapper
public interface UserMapper {
    @Select("select count(phone) from data_user where phone = #{phone}")
    Integer findByphone(@Param("phone") String phone);

    Integer insertToDataUser(DataUser dataUser);

    Integer loadDataUserName(DataUser dataUser);

    @Select("select account_id accountId from data_user where seed_token = #{seedToken}")
    DataUser findUserBySeedToken(@Param("seedToken") String seedToken);

    @Select("select account_id accountId from data_user where phone = #{phoneNumber} and password = #{password} limit 0,1")
    DataUser findByPassword(@Param("phoneNumber") String phoneNumber, @Param("password") String password);

    @Select("select account_id accountId from data_user where phone = #{phoneNumber} limit 0,1")
    DataUser findByPhone(@Param("phoneNumber") String phoneNumber);

    @Update("update data_user set password = #{password} where phone = #{phone}")
    void updatePassword(@Param("phone") String phone, @Param("password") String password);

    int updateDataUser(DataUser dataUser);

    @Select("SELECT UNIX_TIMESTAMP(update_time) + lock_day*60*60*24 endTime,lock_mark lockMark FROM data_user_lock " +
            "WHERE uid = #{uid} AND UNIX_TIMESTAMP(NOW())<= UNIX_TIMESTAMP(update_time)+lock_day*60*60*24 and lock_type in ('0','1') ")
    Map selectLock(@Param("uid") String uid);

    @Select("SELECT u.phone,u.package_channel channel,r.register_ip,device_id dev_code FROM data_user u LEFT JOIN data_register r " +
            "ON r.account_id = u.account_id WHERE u.account_id = #{accountId} ")
    Map becomeAgent(@Param("accountId")String accountId);

    Map checkBlack(@Param("uid")String uid,@Param("matchineId") String matchineId,@Param("ip") String ip);

    void saveBlackInfo(@Param("tid")Integer tid, @Param("uid")String uid,@Param("blackType") Object blackType,@Param("blackNum") Object blackNum,@Param("handleType") Object handleType);
}

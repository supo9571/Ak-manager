package com.data.mapper;

import com.manager.common.core.domain.entity.DataUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
    void updatePassword(@Param("phone") String phone,@Param("password")  String password);

    int updateDataUser(DataUser dataUser);
}

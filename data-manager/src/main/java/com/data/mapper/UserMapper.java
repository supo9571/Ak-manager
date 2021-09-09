package com.data.mapper;

import com.manager.common.core.domain.entity.DataUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author marvin 2021/8/28
 */
@Mapper
public interface UserMapper {
    @Select("select * from data_user where phone = #{phone}")
    Integer findByphone(@Param("phone") String phone);

    Integer insertToDataUser(DataUser dataUser);

    Integer loadDataUserName(DataUser dataUser);
}

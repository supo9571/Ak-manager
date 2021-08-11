package com.manager.system.mapper;

import com.manager.common.core.domain.entity.SysIpBlack;
import com.manager.common.core.domain.entity.SysIpWhite;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SysIpBlackMapper {

    void insertIpBlack(List<SysIpBlack> list);

    @Delete("delete from sys_ip_black where id = #{id}")
    void delIpBlack(@Param("id") long id);

    List selectIpBlackList(@Param("deptId") String deptId,@Param("userId") String userId, @Param("ip")String ip);
}

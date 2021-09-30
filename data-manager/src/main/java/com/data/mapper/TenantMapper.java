package com.data.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author marvin 2021/9/11
 * 平台 mapper
 */
@Mapper
public interface TenantMapper {

    /**
     * 根据 渠道id 获取 平台id
     *
     * @param cid
     * @return
     */
    @Select("select tenant from sys_tenant where t_id = #{cid} limit 0,1 ")
    Integer getTidByCid(@Param("cid") String cid);
}

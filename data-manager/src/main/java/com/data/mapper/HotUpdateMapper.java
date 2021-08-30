package com.data.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/30
 */
@Mapper
public interface HotUpdateMapper {


    List<Map> selectPackage(@Param("ip") String ip, @Param("channelId") String channelId, @Param("versionId") String versionId, @Param("platform") String platform);

    List<Map> selectPackage1(@Param("ip") String ip, @Param("channelId") String channelId, @Param("versionId") String versionId, @Param("platform") String platform);

    @Select("select accord_addr,info,live_url,open_type from config_consumer where status = '1'")
    List<Map> selectConsumer();
}

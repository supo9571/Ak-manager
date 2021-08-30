package com.data.mapper;

import com.manager.common.core.domain.model.Allupdate;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/30
 */
@Mapper
public interface UpdateMapper {


    List<Map> selectPackage(@Param("ip") String ip, @Param("channelId") String channelId, @Param("versionId") String versionId, @Param("platform") String platform);

    List<Map> selectPackage1(@Param("ip") String ip, @Param("channelId") String channelId, @Param("versionId") String versionId, @Param("platform") String platform);

    @Select("select accord_addr,info,live_url,open_type from config_consumer where status = '1'")
    List<Map> selectConsumer();

    Integer addAllUpdate(Allupdate allupdate);

    @Select("SELECT id,VERSION,u.tid,STATUS,apk_update_url apkUpdateUrl,update_time updateTime FROM config_update u " +
            "LEFT JOIN (SELECT tid,MAX(ver_int) ver_int FROM config_update GROUP BY tid) a ON u.tid = a.tid AND u.ver_int = a.ver_int ORDER BY id DESC")
    List<Map> findAllUpdate();

    @Select("SELECT id,VERSION,tid,STATUS,apk_update_url apkUpdateUrl,update_time updateTime FROM config_update where tid= #{tid} order by ver_int desc")
    List findAllUpdateHistory(@Param("tid") String tid);

    Integer editAllUpdate(Allupdate allupdate);

    @Delete("delete from config_update where id = #{id}")
    Integer deleteAllupdate(@Param("id") String id);
}

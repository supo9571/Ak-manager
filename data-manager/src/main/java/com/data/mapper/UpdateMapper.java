package com.data.mapper;

import com.manager.common.core.domain.model.Allupdate;
import com.manager.common.core.domain.model.Hotupdate;
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

    @Select("select accord_addr,info,live_url,open_type from config_consumer where status = '1'")
    List<Map> selectConsumer();

    Integer addAllUpdate(Allupdate allupdate);

    @Select("SELECT id,version,u.tid,status,apk_update_url apkUpdateUrl,update_time updateTime FROM config_update u " +
            "RIGHT JOIN (SELECT tid,MAX(ver_int) ver_int FROM config_update GROUP BY tid) a ON u.tid = a.tid AND u.ver_int = a.ver_int ORDER BY id DESC")
    List<Map> findAllUpdate();

    @Select("SELECT id,version,tid,status,apk_update_url apkUpdateUrl,update_time updateTime,size pageSize FROM config_update where tid= #{tid} order by ver_int desc")
    List<Map> findAllUpdateHistory(@Param("tid") Integer tid);

    Integer editAllUpdate(Allupdate allupdate);

    @Delete("delete from config_update where id = #{id}")
    Integer deleteAllupdate(@Param("id") String id);

    @Select("SELECT c.id,c.version,c.tid,c.status,c.apk_update_url apkUpdateUrl,c.update_time updateTime FROM config_update c LEFT JOIN sys_tenant t ON c.tid = t.tenant" +
            " WHERE t.t_id= #{tid} and c.status = '1' ORDER BY ver_int DESC LIMIT 0,1")
    Map selectAllupdate(@Param("tid") String tid);

    //添加 热更新
    int addHotUpdate(Hotupdate hotUpdate);

    //修改 热更新
    int editHotUpdate(Hotupdate hotUpdate);

    @Delete("delete from config_package where id = #{id}")
    Integer delHotupdate(@Param("id") String id);

    List<Map> findHotupdate();

    List findHotupdateById(@Param("id") Integer id);

    @Select("select title,content info,signature inscribe,notice_time from sys_stop_taking_notice where tid=#{tid} and SYSDATE()>begin_time AND end_time>SYSDATE() order by update_time limit 0,1")
    Map selectStopNotice(@Param("tid") Integer tid);
}

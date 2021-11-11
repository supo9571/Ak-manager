package com.data.mapper;

import com.manager.common.core.domain.model.MailRecord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/9/25
 */
@Mapper
public interface MailMapper {
    List getTips(@Param("channel") String channelId, @Param("uid") String uid, @Param("tid") int tid);

    List getMailList(@Param("uid") String uid, @Param("tid") int tid);

    List getMailConfig(@Param("channel") String channelId, @Param("uid") String uid, @Param("tid") int tid);

    void saveMailRecord(@Param("list") List list, @Param("uid") String uid);

    void sendOutMail(MailRecord mail);
    @Update("update sys_mail_record set mail_state='1',update_time = sysdate() where id = #{id} ")
    void readMail(@Param("id") String id);

    @Select("select mail_prop coins,addressee uid from sys_mail_record where id = #{id} and mail_state NOT IN ('3','6') ")
    Map receiveMail(@Param("id") String id);

    @Update("update sys_mail_record set mail_state='3',update_time = sysdate() where id = #{id} ")
    void updateMailState(@Param("id") String id);

    @Update("update sys_mail_record set del_flag='2',update_time = sysdate() where id = #{id} ")
    void delMail(@Param("id") Integer mid);

    @Select("SELECT ident id,`type`,IF(`type`=3,url,'') url,IF(`type`=4,url,'') view_id,IF(`type`=5,url,'') game_id FROM sys_rotation_picture where state = '1' and tid = #{tid}")
    List<Map> getAdvert(@Param("tid")Integer tid);
}

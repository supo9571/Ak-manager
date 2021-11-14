package com.job.executor.mapper;

import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author marvin 2021/11/10
 */
@Mapper
public interface UnlockMapper {

    @Select("SELECT uid FROM `data_user_lock` WHERE lock_type IN ('0','1') AND UNIX_TIMESTAMP(NOW())>= UNIX_TIMESTAMP(update_time)+lock_day*60*60*24 ")
    List<Long> selectUnlock();

    @Update("update data_user_lock set lock_type = 2 where uid = #{uid}")
    void updateLock(@Param("uid") Long uid);

    @Insert("insert into data_lock_log (uid,lock_type,lock_mark,create_time,create_by) values(#{uid},2,'系统自动解封',sysdate(),'系统')")
    void saveLockInfo(@Param("uid") Long uid);
}

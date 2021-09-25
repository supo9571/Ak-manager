package com.data.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author marvin 2021/9/25
 */
@Mapper
public interface MailMapper {
    List getTips(@Param("channel") String channelId);
}

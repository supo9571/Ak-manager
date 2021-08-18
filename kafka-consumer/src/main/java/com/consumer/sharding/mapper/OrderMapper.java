package com.consumer.sharding.mapper;

import com.consumer.sharding.domain.Order;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/18
 */
@Mapper
public interface OrderMapper {
    @Select("select * from `t_order` where sys_time = #{sysTime}")
    List<Map> findAll(@Param("sysTime") String sysTime);

    @Insert("insert into `t_order` (`order_no`,`sys_time`) values ('dadsadsa','2019-07-15 00:00:00')")
    int insert();

    @Select("select * from `t_order`")
    List<Map> findAll2();
}

package com.data.mapper;

import com.manager.common.core.domain.model.PlayUser;
import com.manager.common.core.domain.model.PlayWater;
import com.manager.common.core.domain.model.UserExchange;
import com.manager.common.core.domain.model.UserLock;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/8/20
 */
@Mapper
public interface PlayerMapper {

    List<PlayUser> selectPlayer(@Param("playUser") PlayUser playUser,@Param("count") String count);

    String getOneRecharge(@Param("uid") String uid);

    String getSumChannel(@Param("channel") String channel);

    /**
     * type 支付宝：0 银行卡：1
     */
    @Select("SELECT uid from user_exchange ue where ue.account = #{value} and ue.type = #{type}")
    List<String> getAlipayOrBankCard(@Param("value") String value,@Param("type") Integer type);

    @Select("select curr/10000 curr,FROM_UNIXTIME(time) time from data_coins where uid = #{uid}")
    List<Map> selectPlayerCurr(@Param("uid") Long uid);

    Integer updatePlayer(PlayUser playUser);

    @Select("select u.phone from data_register r left join data_user u on r.account_id = u.account_id where r.uid = #{uid} limit 0,1")
    String getPhone(@Param("uid") Long uid);

    @Select("select name,account,origin_bank originBank from user_exchange where uid = #{uid} and type='1' limit 0,1")
    Map getBankInfo(@Param("uid") Long uid);

    @Select("select name,account,origin_bank originBank from user_exchange where uid = #{uid} and type='0' limit 0,1")
    Map getAlipayInfo(@Param("uid") Long uid);

    Integer updateBank(UserExchange userExchange);

    Map getRecAndexc(@Param("uid") Long uid);

    List getRechargeInfo(@Param("uid") String uid);

    List getExchangeInfo(@Param("uid") String uid);

    Map userInfo(@Param("uid") Long uid);

    List waterInfo(PlayWater playWater);

    void updateToken(@Param("uid") Long uid);

    void saveUserLock(UserLock userLock);

    void saveUserLockLog(UserLock userLock);

    @Select("SELECT uid,create_time createTime,lock_type lockDay,lock_day lockDay,lock_mark lockMark,create_by createBy FROM data_lock_log where uid = #{uid}")
    List<Map> getLockLog(@Param("uid") Long uid);

    @Select("select curr from data_register where uid = #{uid}")
    Long getUsercurr(@Param("uid") String uid);

    @Select("select channel from data_register where uid = #{uid}")
    String getChannel(@Param("uid")String uid);

    @Select("select tenant from sys_tenant where t_id = #{channel} limit 0,1 ")
    int getTid(@Param("channel") String channel);
}

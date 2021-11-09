package com.data.mapper;

import com.manager.common.core.domain.model.ExchangeOrder;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author marvin 2021/9/11
 */
@Mapper
public interface MonthCardMapper {

    @Select("select jin_recharge,jin_recharge_give jin_recharge_to_give,jin_recharge_give_day jin_recharge_to_give_day," +
            "yin_recharge,yin_recharge_give yin_recharge_to_give,yin_recharge_give_day yin_recharge_give_day," +
            "open_type jump_type,live_addr jump_url,wechat wx,qq  " +
            "from config_month_recharge where status = '1' and tid = #{tid} order by update_time limit 0,1 ")
    Map getMonthConfig(@Param("tid") Integer tid);

    @Select("select id,cname,pay_channel,pay_type,recharge_give recharge_to_give,sort sort_id,pay_channel pay_name from config_pay where status = '1' and tid = #{tid} ")
    List<Map> selectConfigPay(@Param("tid") Integer tid);

    @Select("SELECT open_type,recharge_other_type,recharge_type,IF(open_type = '1',live_addr,url)url,img_type vip_img,vip_name,QQ,wechat,order_num `index` FROM config_vip_recharge " +
            " WHERE pay_id = #{id} AND status = '1' and tid = #{tid} ")
    List<Map> selectVipconfig(@Param("id") Integer id, @Param("tid") Integer tid);

    @Select("SELECT open_type jump_type,btn,live_addr url,bank_value FROM config_bank_recharge where pay_id = #{id} AND status = '1' and vip_list like concat('%',#{vip},'%') and tid = #{tid} ")
    List<Map> selectBankconfig(@Param("id") Integer id, @Param("vip") int vip, @Param("tid") Integer tid);

    @Select("SELECT btn,diy_max,diy_min,is_diy,msg,other_name,online_config_id,pay_type pay_channel,pay_channel_code FROM config_online_recharge where pay_id = #{id} " +
            "AND status = '1' and vip_list like concat('%',#{vip},'%') and (use_mobile='3' or use_mobile=#{phoneType}) and tid = #{tid} ")
    List<Map> selectOnlineconfig(@Param("id") Integer id, @Param("vip") Integer vip, @Param("phoneType") String phoneType, @Param("tid") Integer tid);

    @Select("select recharge_give from config_pay where pay_type = '1' and status = '1' and tid = #{tid} limit 0,1")
    Integer getVipGive(@Param("tid") Integer tid);

    @Select("select recharge_give from config_pay where pay_type = '3' and status = '1' and tid = #{tid} limit 0,1")
    Integer getBankGive(Integer tid);



    @Select("select count(*) from user_exchange where type = #{type} and account = #{account} ")
    int getAccountCount(@Param("type") String type, @Param("account") String account);

    @Insert("insert into user_exchange (uid,tid,type,name,account,origin_bank,channel,create_time) values(#{uid},#{tid},#{type},#{name},#{account},#{originBank},#{channel},sysdate())")
    Integer saveExchange(@Param("channel") String channel, @Param("tid") Integer tid, @Param("uid") String uid,
                         @Param("type") String type, @Param("name") String name, @Param("account") String account, @Param("originBank") String originBank);

    Integer saveWithdraw(ExchangeOrder exchangeOrder);

    @Select("select keep_money,max_money,min_money,num,poundage,add_mosaic_num recharge_times,method type from config_exchange where status = '1' and tid = #{tid}")
    List<Map> getExchangeConfig(@Param("tid") Integer tid);

    @Select("select u.name,u.account,u.type,u.origin_bank originBank,u.create_time CreateAt," +
            "c.keep_money,c.max_money,c.min_money,c.num,c.poundage,c.add_mosaic_num recharge_times " +
            "from user_exchange u left join config_exchange c on u.type = c.method and u.tid = c.tid where u.uid = #{uid} and u.tid=#{tid} ")
    List<Map> getUserBind(@Param("uid") String uid, @Param("tid") Integer tid);

    @Select("SELECT total_add pay_money,total_water person_water FROM data_register where uid = #{uid}")
    List<Map> getUserWater(@Param("uid") String uid);

    @Select("select * from config_bank")
    List<Map> getBankList();

    @Select("select register_ip registerIp,total_add totalAdd,total_give totalGive,total_water totalWater,name from data_register where uid = #{uid} limit 0,1")
    Map findUserByid(@Param("uid") String uid);

    @Select("select count(1) from config_exchange_order where uid = #{uid}")
    Integer getWithdrawNumber(String uid);

    @Select("select poundage from config_exchange where tid = #{tid} and method = #{type} ")
    Integer getPoundage(@Param("type") String type, @Param("tid") Integer tid);

    Map checkBlack(ExchangeOrder exchangeOrder);

    void saveBlackInfo(@Param("tid")Integer tid, @Param("uid")String uid,@Param("blackType") Object blackType,@Param("blackNum") Object blackNum);
}

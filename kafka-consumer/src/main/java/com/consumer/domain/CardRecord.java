package com.consumer.domain;

import lombok.Data;

/**
 * @author marvin 2021/8/18
 */
@Data
public class CardRecord {
    /**
     *     op:"card_record",
     *     "key":"tablesvr_1_1628990404_1670",
     *     "time":1628990404,
     *     "exinfo":{
     *         "sender":107033,
     *         "hb_coins":100000,
     *         "lei_number":5,
     *         "control_uid":0,
     *         "system_result":0
     *     },
     *     "mstime":1628990404686,
     *     "address":":0000003c",
     *     "end_time":1628990404,
     *     "game_type":2008,
     *     "side_list":[
     *         {
     *             "uid":107033,
     *             "is_lei":false,
     *             "is_robot":true,
     *             "fee_coins":10000,
     *             "rob_coins":0,
     *             "result_coins":90000
     *         },
     *         {
     *             "uid":107338,
     *             "is_lei":true,
     *             "is_robot":true,
     *             "fee_coins":0,
     *             "rob_coins":15500,
     *             "sender_name":"贵宾107033",
     *             "result_coins":-84500
     *         }
     *     ],
     *     "table_gid":"20210815092004_33",
     *     "total_num":11,
     *     "begin_time":1628990399,
     *     "loser_list":{
     *
     *     },
     *     "system_win":0,
     *     "table_type":200800,
     *     "winner_list":{
     *
     *     }
     */

    private String key;
    private String op;
    private Long time;
    private Long mstime;
}

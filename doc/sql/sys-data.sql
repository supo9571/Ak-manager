/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.35 : Database - sys-data
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sys-data` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `sys-data`;

/*Table structure for table `config_game` */

DROP TABLE IF EXISTS `config_game`;

CREATE TABLE `config_game` (
  `game_id` int(11) NOT NULL AUTO_INCREMENT,
  `game_name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`game_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2023 DEFAULT CHARSET=utf8mb4;

/*Data for the table `config_game` */

insert  into `config_game`(`game_id`,`game_name`) values 
(1,'扎金花'),
(2,'抢庄牛牛'),
(3,'斗地主金币场'),
(4,'斗地主癞子玩法'),
(5,'黑杰克'),
(6,'捕鱼'),
(7,'决战海王捕鱼'),
(8,'二人麻将'),
(9,'深海觉醒'),
(10,'圣兽闹海'),
(2000,'红黑大战'),
(2001,'百人牛牛'),
(2002,'十倍百人牛牛'),
(2003,'龙凤对决'),
(2004,'老虎机'),
(2005,'奔驰宝马'),
(2006,'百家乐'),
(2007,'二八杠'),
(2008,'红包'),
(2009,'德州扑克'),
(2011,'印度扎金花'),
(2012,'古惑仔老虎机'),
(2014,'万人扎金花'),
(2016,'魔兽世界'),
(2017,'剑侠情缘'),
(2018,'绝地求生'),
(2019,'葫芦兄弟'),
(2020,'变形金刚'),
(2021,'跑马灯水果机'),
(2022,'王者荣耀');

/*Table structure for table `config_reason` */

DROP TABLE IF EXISTS `config_reason`;

CREATE TABLE `config_reason` (
  `r_id` int(11) NOT NULL,
  `r_name` varchar(30) DEFAULT NULL,
  `CODE` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`r_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `config_reason` */

insert  into `config_reason`(`r_id`,`r_name`,`CODE`) values 
(1,'押注金币','BET_COIN'),
(2,'赢金币','WIN_COIN'),
(3,'押注失败金币返回','BET_COIN_BACK'),
(4,'扣除金币失败返还','COST_COIN_ERROR_BACK'),
(5,'好友房扣除房卡失败返卡','FRIEND_ERROR_ROOMCARD_BACK'),
(6,'扣台费','PAY_FEE'),
(7,'使用魔法表情','USE_MAGIC_PICTRUE'),
(8,'开房扣除房卡','FRIEND_TABLE_ROOMCARD_COST'),
(9,'好友房解散后返卡','FRIEND_TABLE_RETURN'),
(10,'押注失败房卡返回','BET_ROOMCARD_BACK'),
(11,'赢房卡','WIN_ROOMCARD'),
(12,'扣除房卡失败返还','COST_ROOMCARD_ERROR_BACK'),
(13,'押注房卡','BET_ROOMCARD'),
(14,'扣台费(房卡)','PAY_FEE_ROOMCARD'),
(15,'抽取佣金','PAY_COMMISSION'),
(16,'捕鱼开火','FISHING_FIRE      '),
(17,'捕中鱼','FISHING_CATCH     '),
(18,'购买失败金币返回','FISHING_BUY_BACK  '),
(19,'购买道具','FISHING_BUY_ITEM  '),
(20,'子弹退还','FISHING_BACK_COINS'),
(30,'免费下注','TIGER_FREE_BET     '),
(31,'返还免费次数','BET_FREE_TIMES_BACK'),
(32,'奖励免费次数','AWARD_FREE_TIEMS   '),
(33,'输金币','LOSE_COIN          '),
(34,'古惑仔老虎机特殊模下注','GHZ_BYB_BET       '),
(35,'返还搏一搏次数','BET_BYB_TIMES_BACK'),
(36,'玩搏一搏','PLAY_BYB          '),
(37,'捕鱼使用道具','FISHING_USE_ITEM  '),
(100000,'破产补助','GAMECOIN_BANKRUPT     '),
(100001,'注册送金币','GAMECOIN_REGISTER     '),
(100002,'后台加金币','GAMECOIN_SYS_ADD      '),
(100003,'后台减金币','GAMECOIN_SYS_MINUS    '),
(100004,'分享奖励','GAMECOIN_SHARE        '),
(100005,'绑定奖励','GAMECOIN_BIND         '),
(100006,'活动推广金币消耗','GAMECOIN_PROMOTION_LST'),
(100007,'活动推广金币奖励','GAMECOIN_PROMOTION_WIN'),
(100008,'首充送金币','GAMECOIN_CHARGE_REWARD'),
(100009,'活动推广房卡奖励','ROOMCARD_PROMOTION_WIN'),
(100010,'首充送房卡','ROOMCARD_CHARGE_REWARD'),
(100011,'系统加房卡','ROOMCARD_SYS_ADD      '),
(100012,'系统减房卡','ROOMCARD_SYS_MINUS    '),
(100013,'注册送房卡','ROOMCARD_REGISTER     '),
(100014,'花钻石换座位','DIAMOND_SWITCH_SEAT   '),
(100015,'活动推广钻石奖励','DIAMOND_PROMOTION_LST '),
(100016,'后台加钻石','DIAMOND_SYS_ADD       '),
(100017,'后台减钻石','DIAMOND_SYS_MINUS     '),
(100018,'注册送钻石','DIAMOND_REGISTER      '),
(100019,'签到','SIGN_IN               '),
(100020,'领取签到奖励','TAKE_SIGN_AWARD       '),
(100022,'领取邮件奖励','TAKE_MAIL_ATTACH      '),
(100023,'GM操作','GM                    '),
(100025,'商城购买','BUY_FROM_SHOP         '),
(100027,'新手奖励','NEWBIE_AWARD          '),
(100028,'商城购买','BUY_FROM_SHOP_RMB     '),
(100029,'为机器人增加金币','ADD_COINS_FOR_ROBOT   '),
(100030,'领取任务奖励','TAKE_TASK_AWARD       '),
(100031,'破产登记','BANKRUPT_REGISTER     '),
(100032,'牌局记录','CARD_RECORD           '),
(100033,'在线用户','USER_ONLINE           '),
(100034,'在玩用户','USER_PLAYING          '),
(100035,'活动添加金币','ACTIVITY_ADD_COINS    '),
(100036,'人民币购买详情记录','PAY_BY_RMB_DETAIL     '),
(100037,'商城购买详情记录','PAY_BY_SHOP_DETAIL    '),
(100038,'代理返现','CASHBACK              '),
(100039,'保险箱存钱','DESPOSIT_SAFE_BOX     '),
(100040,'兑换金币','EXCHANGE_COINS		'),
(100041,'绑定手机奖励','BIND_PHONE_REWARD	'),
(100042,'后台拒绝返回金币','RETURNBACK			'),
(100043,'为机器人减少金币','REDUCE_COINS_FOR_ROBOT'),
(100044,'玩家牌局赢钱','PLAYER_WIN            '),
(100045,'玩家牌局输钱','PLAYER_LOSE           '),
(100046,'新增玩家','NEW_PLAYER_ADD        '),
(100047,'充值返水','CHARGE_RETRUN         '),
(100048,'鸿运','LUCKY                 '),
(100049,'周福利兑换','SEVEN_AWARD_EXCHANGE  '),
(100050,'拉新活动抽奖','NEW_BIND_LUCKY_DRWA'),
(100051,'打开财神红包','OPEN_CAISHEN       '),
(100052,'大抽奖','AWARD_LUCKY_DRWA   '),
(100053,'活动领取奖励','TASK_ACT           '),
(100054,'俱乐部兑换 ','CLUB_EXCHANGE      '),
(100055,'强制设置机器人庄家','FORCE_ROBOT_BANKER '),
(100056,'保险箱取钱','TAKE_OUT_SAFE_BOX  '),
(100057,'抽成流水','WATER              '),
(100058,'红包风暴','REDPACK            '),
(100059,'系统修改水果机jackport池','SYSJACKPORT        '),
(100060,'玩家中奖jackport池','USERJACKPORT       '),
(100061,'每日签到加钱','DAILYSIGNED        '),
(100062,'月俸禄加钱','MONTHPRIZE         '),
(100063,'摇钱树全任务加钱','CASHCOWTASK        '),
(100065,'vip日礼金','VIPDAILYPRIZE      '),
(100066,'vip周礼金','VIPWEEKPRIZE       '),
(100067,'vip月礼金','VIPMONTHPRIZE      '),
(100068,'vip升级奖励','VIPUPDATEPRIZE     '),
(100070,'VIP充值','VIP_CHARGE         '),
(100071,'金卡月卡充值','GOLD_CARD_CHARGE   '),
(100072,'银卡月卡充值','SILVER_CARD_CHARGE '),
(100073,'银行卡充值','BANK_CHARGE        '),
(100074,'线上充值','ONLINE_CHARGE      '),
(100075,'充值赠送','CHARGE_SEND        '),
(110001,'每日充值红包奖励','REDBOX_DAY_REWARD       '),
(110002,'连续充值红包奖励','REDBOX_CONTINUOUS_REWARD'),
(110003,'每日首冲活动奖励','DAILYFIRSTCHART         '),
(110004,'每日流水返利奖励','BENEFITS                '),
(110005,'月卡活动奖励','MONTH_CARD_REWARD       '),
(110006,'全民推广活动奖励','COMPREHENSIVE_PROMOTION '),
(200000,'用户注册','ACCOUNT_REGISTER'),
(200001,'账号登入','ACCOUNT_LOGIN   '),
(200002,'账号登出','ACCOUNT_LOGOUT  ');

/*Table structure for table `data_coins` */

DROP TABLE IF EXISTS `data_coins`;

CREATE TABLE `data_coins` (
  `key` varchar(50) NOT NULL COMMENT '消息主键',
  `op` varchar(30) DEFAULT NULL COMMENT '消息主题',
  `r` int(11) DEFAULT NULL COMMENT '原因',
  `uid` bigint(20) DEFAULT NULL COMMENT '玩家id',
  `curr` bigint(20) DEFAULT NULL COMMENT '当前余额',
  `time` bigint(20) DEFAULT NULL COMMENT '操作时间',
  `mstime` bigint(20) DEFAULT NULL COMMENT '操作时间ms',
  `value` bigint(20) DEFAULT NULL COMMENT '账变金额',
  `before` bigint(20) DEFAULT NULL COMMENT '账变前金额',
  `channel` varchar(20) DEFAULT NULL COMMENT '渠道',
  `device_id` varchar(50) DEFAULT NULL COMMENT '机器码',
  `game_type` int(11) DEFAULT NULL COMMENT '游戏id',
  `table_type` int(11) DEFAULT NULL COMMENT '桌号',
  `cur_channel` varchar(20) DEFAULT NULL COMMENT '当前渠道',
  `device_brand` varchar(20) DEFAULT NULL COMMENT '设备名称',
  `client_version` varchar(20) DEFAULT NULL COMMENT '版本号',
  `safe_box` bigint(20) DEFAULT NULL COMMENT '保险箱金额',
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `data_coins` */

/*Table structure for table `data_login` */

DROP TABLE IF EXISTS `data_login`;

CREATE TABLE `data_login` (
  `key` varchar(50) NOT NULL COMMENT '消息主键',
  `op` varchar(20) DEFAULT NULL COMMENT '消息主题',
  `uid` bigint(20) DEFAULT NULL COMMENT '玩家id',
  `name` varchar(20) DEFAULT NULL COMMENT '玩家名称',
  `sex` int(11) DEFAULT NULL COMMENT '性别',
  `phone` varchar(20) DEFAULT NULL COMMENT '电话',
  `r` int(11) DEFAULT NULL COMMENT '原因',
  `ip` bigint(20) DEFAULT NULL COMMENT 'ip',
  `time` bigint(20) DEFAULT NULL COMMENT '时间',
  `mstime` bigint(20) DEFAULT NULL COMMENT '时间ms',
  `level` int(11) DEFAULT NULL COMMENT '等级',
  `channel` varchar(20) DEFAULT NULL COMMENT '渠道',
  `device_id` varchar(50) DEFAULT NULL COMMENT '设备码',
  `vip_level` int(11) DEFAULT NULL COMMENT 'vip等级',
  `cur_channel` varchar(20) DEFAULT NULL COMMENT '渠道',
  `register_ip` varchar(20) DEFAULT NULL COMMENT '注册ip',
  `device_brand` varchar(20) DEFAULT NULL COMMENT '设备名',
  `client_version` varchar(20) DEFAULT NULL COMMENT '版本号',
  `ip_address` varchar(20) DEFAULT NULL COMMENT 'IP地址',
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `data_login` */

/*Table structure for table `data_logout` */

DROP TABLE IF EXISTS `data_logout`;

CREATE TABLE `data_logout` (
  `key` varchar(50) NOT NULL COMMENT '消息主键',
  `op` varchar(20) DEFAULT NULL COMMENT '消息主题',
  `uid` bigint(20) DEFAULT NULL COMMENT '玩家id',
  `r` int(11) DEFAULT NULL COMMENT '原因',
  `ip` bigint(20) DEFAULT NULL COMMENT 'ip',
  `time` bigint(20) DEFAULT NULL COMMENT '时间',
  `mstime` bigint(20) DEFAULT NULL COMMENT '时间ms',
  `channel` varchar(20) DEFAULT NULL COMMENT '渠道',
  `device_id` varchar(50) DEFAULT NULL COMMENT '设备码',
  `cur_channel` varchar(20) DEFAULT NULL COMMENT '渠道',
  `device_brand` varchar(20) DEFAULT NULL COMMENT '设备名',
  `client_version` varchar(20) DEFAULT NULL COMMENT '版本号',
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `data_logout` */

/*Table structure for table `data_register` */

DROP TABLE IF EXISTS `data_register`;

CREATE TABLE `data_register` (
  `key` varchar(50) NOT NULL COMMENT '消息主键',
  `op` varchar(20) DEFAULT NULL COMMENT '消息主题',
  `uid` bigint(20) DEFAULT NULL COMMENT '玩家id',
  `ip` bigint(20) DEFAULT NULL COMMENT '注册ip',
  `name` varchar(20) DEFAULT NULL COMMENT '玩家名字',
  `time` bigint(20) DEFAULT NULL COMMENT '注册时间',
  `mstime` bigint(20) DEFAULT NULL COMMENT '注册时间ms',
  `channel` varchar(20) DEFAULT NULL COMMENT '渠道',
  `agent_id` int(11) DEFAULT NULL COMMENT '上级代理',
  `device_id` varchar(50) DEFAULT NULL COMMENT '机器码',
  `cur_channel` varchar(20) DEFAULT NULL COMMENT '当前代理',
  `register_ip` varchar(20) DEFAULT NULL COMMENT '注册ip',
  `device_brand` varchar(20) DEFAULT NULL COMMENT '设备名称',
  `client_version` varchar(20) DEFAULT NULL COMMENT '版本号',
  `register_machine` varchar(50) DEFAULT NULL COMMENT '注册机器',
  `safe_box` bigint(20) DEFAULT NULL COMMENT '保险箱金额',
  `login_ip` varchar(20) DEFAULT NULL COMMENT '最后登录ip',
  `login_device_id` varchar(50) DEFAULT NULL COMMENT '最后登录设备码',
  `login_device_brand` varchar(20) DEFAULT NULL COMMENT '最后登录设备名称',
  `login_time` bigint(20) DEFAULT NULL COMMENT '最后登录时间',
  `vip_level` int(11) DEFAULT NULL COMMENT 'vip等级',
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `data_register` */

/*Table structure for table `sys_msg` */

DROP TABLE IF EXISTS `sys_msg`;

CREATE TABLE `sys_msg` (
  `key` varchar(50) NOT NULL,
  `op` varchar(20) DEFAULT NULL,
  `msg` text,
  PRIMARY KEY (`key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_msg` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

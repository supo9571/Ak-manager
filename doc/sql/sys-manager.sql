/*
SQLyog Community v13.1.6 (64 bit)
MySQL - 5.7.35 : Database - sys-manager
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`sys-manager` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `sys-manager`;

/*Table structure for table `sys_black_info` */

DROP TABLE IF EXISTS `sys_black_info`;

CREATE TABLE `sys_black_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `t_id` bigint(20) NOT NULL COMMENT '平台id',
  `play_id` bigint(20) DEFAULT NULL COMMENT '玩家id',
  `black_type` smallint(4) DEFAULT NULL COMMENT '黑名单类型',
  `handle_type` bigint(20) DEFAULT NULL COMMENT '处理方式',
  `black_num` varchar(20) NOT NULL COMMENT '黑白单号码',
  `handle_time` timestamp NULL DEFAULT NULL COMMENT '执行时间',
  `handle_user` bigint(20) DEFAULT NULL COMMENT '操作人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_black_info` */

/*Table structure for table `sys_config` */

DROP TABLE IF EXISTS `sys_config`;

CREATE TABLE `sys_config` (
  `config_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '参数主键',
  `config_name` varchar(100) DEFAULT '' COMMENT '参数名称',
  `config_key` varchar(100) DEFAULT '' COMMENT '参数键名',
  `config_value` varchar(500) DEFAULT '' COMMENT '参数键值',
  `config_type` char(1) DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`config_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COMMENT='参数配置表';

/*Data for the table `sys_config` */

insert  into `sys_config`(`config_id`,`config_name`,`config_key`,`config_value`,`config_type`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values 
(1,'主框架页-默认皮肤样式名称','sys.index.skinName','skin-blue','Y','admin','2021-08-09 11:18:37','',NULL,'蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow'),
(2,'用户管理-账号初始密码','sys.user.initPassword','123456','Y','admin','2021-08-09 11:18:37','',NULL,'初始化密码 123456'),
(3,'主框架页-侧边栏主题','sys.index.sideTheme','theme-dark','Y','admin','2021-08-09 11:18:37','',NULL,'深色主题theme-dark，浅色主题theme-light'),
(4,'账号自助-验证码开关','sys.account.captchaOnOff','false','Y','admin','2021-08-09 11:18:37','',NULL,'是否开启验证码功能（true开启，false关闭）'),
(5,'账号自助-是否开启用户注册功能','sys.account.registerUser','false','Y','admin','2021-08-09 11:18:37','',NULL,'是否开启注册用户功能（true开启，false关闭）');

/*Table structure for table `sys_dict_data` */

DROP TABLE IF EXISTS `sys_dict_data`;

CREATE TABLE `sys_dict_data` (
  `dict_code` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int(4) DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_code`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COMMENT='字典数据表';

/*Data for the table `sys_dict_data` */

insert  into `sys_dict_data`(`dict_code`,`dict_sort`,`dict_label`,`dict_value`,`dict_type`,`css_class`,`list_class`,`is_default`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values 
(1,1,'男','0','sys_user_sex','','','Y','0','admin','2021-08-09 11:18:34','',NULL,'性别男'),
(2,2,'女','1','sys_user_sex','','','N','0','admin','2021-08-09 11:18:34','',NULL,'性别女'),
(3,3,'未知','2','sys_user_sex','','','N','0','admin','2021-08-09 11:18:34','',NULL,'性别未知'),
(4,1,'显示','0','sys_show_hide','','primary','Y','0','admin','2021-08-09 11:18:34','',NULL,'显示菜单'),
(5,2,'隐藏','1','sys_show_hide','','danger','N','0','admin','2021-08-09 11:18:34','',NULL,'隐藏菜单'),
(6,1,'正常','0','sys_normal_disable','','primary','Y','0','admin','2021-08-09 11:18:34','',NULL,'正常状态'),
(7,2,'停用','1','sys_normal_disable','','danger','N','0','admin','2021-08-09 11:18:34','',NULL,'停用状态'),
(8,1,'正常','0','sys_job_status','','primary','Y','0','admin','2021-08-09 11:18:34','',NULL,'正常状态'),
(9,2,'暂停','1','sys_job_status','','danger','N','0','admin','2021-08-09 11:18:35','',NULL,'停用状态'),
(10,1,'默认','DEFAULT','sys_job_group','','','Y','0','admin','2021-08-09 11:18:35','',NULL,'默认分组'),
(11,2,'系统','SYSTEM','sys_job_group','','','N','0','admin','2021-08-09 11:18:35','',NULL,'系统分组'),
(12,1,'是','Y','sys_yes_no','','primary','Y','0','admin','2021-08-09 11:18:35','',NULL,'系统默认是'),
(13,2,'否','N','sys_yes_no','','danger','N','0','admin','2021-08-09 11:18:35','',NULL,'系统默认否'),
(14,1,'通知','1','sys_notice_type','','warning','Y','0','admin','2021-08-09 11:18:35','',NULL,'通知'),
(15,2,'公告','2','sys_notice_type','','success','N','0','admin','2021-08-09 11:18:35','',NULL,'公告'),
(16,1,'正常','0','sys_notice_status','','primary','Y','0','admin','2021-08-09 11:18:35','',NULL,'正常状态'),
(17,2,'关闭','1','sys_notice_status','','danger','N','0','admin','2021-08-09 11:18:35','',NULL,'关闭状态'),
(18,1,'新增','1','sys_oper_type','','info','N','0','admin','2021-08-09 11:18:35','',NULL,'新增操作'),
(19,2,'修改','2','sys_oper_type','','info','N','0','admin','2021-08-09 11:18:36','',NULL,'修改操作'),
(20,3,'删除','3','sys_oper_type','','danger','N','0','admin','2021-08-09 11:18:36','',NULL,'删除操作'),
(21,4,'授权','4','sys_oper_type','','primary','N','0','admin','2021-08-09 11:18:36','',NULL,'授权操作'),
(22,5,'导出','5','sys_oper_type','','warning','N','0','admin','2021-08-09 11:18:36','',NULL,'导出操作'),
(23,6,'导入','6','sys_oper_type','','warning','N','0','admin','2021-08-09 11:18:36','',NULL,'导入操作'),
(24,7,'强退','7','sys_oper_type','','danger','N','0','admin','2021-08-09 11:18:36','',NULL,'强退操作'),
(25,8,'生成代码','8','sys_oper_type','','warning','N','0','admin','2021-08-09 11:18:36','',NULL,'生成操作'),
(26,9,'清空数据','9','sys_oper_type','','danger','N','0','admin','2021-08-09 11:18:36','',NULL,'清空操作'),
(27,1,'成功','0','sys_common_status','','primary','N','0','admin','2021-08-09 11:18:36','',NULL,'正常状态'),
(28,2,'失败','1','sys_common_status','','danger','N','0','admin','2021-08-09 11:18:36','',NULL,'停用状态');

/*Table structure for table `sys_dict_type` */

DROP TABLE IF EXISTS `sys_dict_type`;

CREATE TABLE `sys_dict_type` (
  `dict_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`dict_id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COMMENT='字典类型表';

/*Data for the table `sys_dict_type` */

insert  into `sys_dict_type`(`dict_id`,`dict_name`,`dict_type`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values 
(1,'用户性别','sys_user_sex','0','admin','2021-08-09 11:18:32','',NULL,'用户性别列表'),
(2,'菜单状态','sys_show_hide','0','admin','2021-08-09 11:18:33','',NULL,'菜单状态列表'),
(3,'系统开关','sys_normal_disable','0','admin','2021-08-09 11:18:33','',NULL,'系统开关列表'),
(4,'任务状态','sys_job_status','0','admin','2021-08-09 11:18:33','',NULL,'任务状态列表'),
(5,'任务分组','sys_job_group','0','admin','2021-08-09 11:18:33','',NULL,'任务分组列表'),
(6,'系统是否','sys_yes_no','0','admin','2021-08-09 11:18:33','',NULL,'系统是否列表'),
(7,'通知类型','sys_notice_type','0','admin','2021-08-09 11:18:33','',NULL,'通知类型列表'),
(8,'通知状态','sys_notice_status','0','admin','2021-08-09 11:18:33','',NULL,'通知状态列表'),
(9,'操作类型','sys_oper_type','0','admin','2021-08-09 11:18:33','',NULL,'操作类型列表'),
(10,'系统状态','sys_common_status','0','admin','2021-08-09 11:18:33','',NULL,'登录状态列表');

/*Table structure for table `sys_ip_white` */

DROP TABLE IF EXISTS `sys_ip_white`;

CREATE TABLE `sys_ip_white` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `t_id` bigint(20) NOT NULL COMMENT '平台id',
  `user_id` bigint(20) NOT NULL COMMENT '账号id',
  `ip` varchar(20) NOT NULL COMMENT 'ip地址',
  `create_user_id` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4;

/*Data for the table `sys_ip_white` */

insert  into `sys_ip_white`(`id`,`t_id`,`user_id`,`ip`,`create_user_id`,`create_time`) values 
(14,1,11,'132.2.2.2',1,'2021-08-12 23:18:14'),
(15,1,11,'133.3.3',1,'2021-08-12 23:18:14'),
(16,1,12,'123.2.2.2',1,'2021-08-13 13:51:25'),
(17,1,12,'132.2.2.2',1,'2021-08-13 13:51:25'),
(18,1,12,'133.3.3',1,'2021-08-13 13:51:25');

/*Table structure for table `sys_logininfor` */

DROP TABLE IF EXISTS `sys_logininfor`;

CREATE TABLE `sys_logininfor` (
  `info_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '访问ID',
  `user_name` varchar(50) DEFAULT '' COMMENT '用户账号',
  `ipaddr` varchar(128) DEFAULT '' COMMENT '登录IP地址',
  `login_location` varchar(255) DEFAULT '' COMMENT '登录地点',
  `browser` varchar(50) DEFAULT '' COMMENT '浏览器类型',
  `os` varchar(50) DEFAULT '' COMMENT '操作系统',
  `status` char(1) DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
  `msg` varchar(255) DEFAULT '' COMMENT '提示消息',
  `login_time` datetime DEFAULT NULL COMMENT '访问时间',
  PRIMARY KEY (`info_id`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COMMENT='系统访问记录';

/*Data for the table `sys_logininfor` */

insert  into `sys_logininfor`(`info_id`,`user_name`,`ipaddr`,`login_location`,`browser`,`os`,`status`,`msg`,`login_time`) values 
(1,'admin','5.192.142.161','XX XX','Chrome 9','Windows 10','0','登录成功','2021-08-09 13:59:56'),
(2,'admin','59.149.170.12','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-09 14:06:03'),
(3,'reid','148.66.56.210','XX XX','Chrome 9','Mac OS X','1','用户不存在/密码错误','2021-08-10 00:25:56'),
(4,'admin','148.66.56.210','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 00:26:00'),
(5,'admin','148.66.56.210','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 00:26:02'),
(6,'admin','148.66.56.210','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 00:44:04'),
(7,'admin','148.66.56.210','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 00:44:16'),
(8,'admin','148.66.56.210','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 00:46:50'),
(9,'admin','148.66.56.210','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 00:49:28'),
(10,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 11:54:06'),
(11,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 12:00:36'),
(12,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 12:01:37'),
(13,'admin','5.192.142.161','XX XX','Chrome 9','Windows 10','0','登录成功','2021-08-10 12:13:16'),
(14,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 12:14:43'),
(15,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 12:19:44'),
(16,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 12:23:09'),
(17,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 12:24:46'),
(18,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 12:35:07'),
(19,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-08-10 13:05:05'),
(20,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 13:24:53'),
(21,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 14:10:53'),
(22,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 14:10:59'),
(23,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 14:11:18'),
(24,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 14:13:11'),
(25,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 14:18:54'),
(26,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 14:20:01'),
(27,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 14:21:46'),
(28,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 14:23:07'),
(29,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 14:23:30'),
(30,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 14:23:50'),
(31,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 14:23:54'),
(32,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 14:23:57'),
(33,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 14:24:24'),
(34,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 14:25:14'),
(35,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 14:25:17'),
(36,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 14:27:50'),
(37,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-10 14:34:32'),
(38,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-08-11 05:41:20'),
(39,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-08-11 06:02:29'),
(40,'admin','5.192.142.161','XX XX','Chrome 9','Windows 10','0','登录成功','2021-08-11 06:13:26'),
(41,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-11 11:17:37'),
(42,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-11 13:17:00'),
(43,'admin','110.173.59.114','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-11 13:17:31'),
(44,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-08-12 05:12:30'),
(45,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-08-12 05:12:50'),
(46,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-08-12 05:35:43'),
(47,'admin','5.192.142.161','XX XX','Chrome 9','Windows 10','0','登录成功','2021-08-12 06:04:57'),
(48,'admin','5.192.142.161','XX XX','Chrome 9','Windows 10','0','登录成功','2021-08-12 06:08:18'),
(49,'admin','5.192.142.161','XX XX','Chrome 9','Windows 10','0','登录成功','2021-08-12 16:29:25'),
(50,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-08-13 13:46:28'),
(51,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-08-13 13:48:51'),
(52,'admin','5.192.142.161','XX XX','Chrome 9','Windows 10','0','登录成功','2021-08-13 19:42:20'),
(53,'admin','5.192.142.161','XX XX','Chrome 9','Windows 10','0','登录成功','2021-08-14 13:30:26'),
(54,'admin','103.117.145.34','XX XX','Chrome 9','Mac OS X','0','登录成功','2021-08-14 13:36:36'),
(55,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-08-14 20:10:47'),
(56,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-08-14 20:13:45'),
(57,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-08-14 20:14:10'),
(58,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-08-14 20:14:27'),
(59,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-08-14 20:14:46'),
(60,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-08-14 20:22:33'),
(61,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-08-14 20:22:56'),
(62,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-08-14 20:24:36'),
(63,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-08-14 20:40:20'),
(64,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-08-14 20:41:28'),
(65,'admin','127.0.0.1','内网IP','Chrome 9','Windows 10','0','登录成功','2021-08-14 20:53:47');

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `menu_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `menu_name` varchar(50) NOT NULL COMMENT '菜单名称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int(4) DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(200) DEFAULT '' COMMENT '路由地址',
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `is_frame` int(1) DEFAULT '1' COMMENT '是否为外链（0是 1否）',
  `is_cache` int(1) DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
  `menu_type` char(1) DEFAULT '' COMMENT '菜单类型（M目录 C菜单 F按钮）',
  `visible` char(1) DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
  `status` char(1) DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
  `perms` varchar(100) DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) DEFAULT '#' COMMENT '菜单图标',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1061 DEFAULT CHARSET=utf8mb4 COMMENT='菜单权限表';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`menu_id`,`menu_name`,`parent_id`,`order_num`,`path`,`component`,`is_frame`,`is_cache`,`menu_type`,`visible`,`status`,`perms`,`icon`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values 
(1,'系统管理',0,1,'system',NULL,1,0,'M','0','0','','system','admin','2021-08-09 11:18:11','',NULL,'系统管理目录'),
(2,'系统监控',0,2,'monitor',NULL,1,0,'M','0','0','','monitor','admin','2021-08-09 11:18:11','',NULL,'系统监控目录'),
(3,'系统工具',0,3,'tool',NULL,1,0,'M','0','0','','tool','admin','2021-08-09 11:18:11','',NULL,'系统工具目录'),
(4,'若依官网',0,4,'http://ruoyi.vip',NULL,0,0,'M','0','0','','guide','admin','2021-08-09 11:18:12','',NULL,'若依官网地址'),
(100,'用户管理',1,1,'user','system/user/index',1,0,'C','0','0','system:user:list','user','admin','2021-08-09 11:18:12','',NULL,'用户管理菜单'),
(101,'角色管理',1,2,'role','system/role/index',1,0,'C','0','0','system:role:list','peoples','admin','2021-08-09 11:18:12','',NULL,'角色管理菜单'),
(102,'菜单管理',1,3,'menu','system/menu/index',1,0,'C','0','0','system:menu:list','tree-table','admin','2021-08-09 11:18:12','',NULL,'菜单管理菜单'),
(103,'部门管理',1,4,'dept','system/dept/index',1,0,'C','0','0','system:dept:list','tree','admin','2021-08-09 11:18:12','',NULL,'部门管理菜单'),
(104,'岗位管理',1,5,'post','system/post/index',1,0,'C','0','0','system:post:list','post','admin','2021-08-09 11:18:12','',NULL,'岗位管理菜单'),
(105,'字典管理',1,6,'dict','system/dict/index',1,0,'C','0','0','system:dict:list','dict','admin','2021-08-09 11:18:12','',NULL,'字典管理菜单'),
(106,'参数设置',1,7,'config','system/config/index',1,0,'C','0','0','system:config:list','edit','admin','2021-08-09 11:18:12','',NULL,'参数设置菜单'),
(107,'通知公告',1,8,'notice','system/notice/index',1,0,'C','0','0','system:notice:list','message','admin','2021-08-09 11:18:12','',NULL,'通知公告菜单'),
(108,'日志管理',1,9,'log','',1,0,'M','0','0','','log','admin','2021-08-09 11:18:13','',NULL,'日志管理菜单'),
(109,'在线用户',2,1,'online','monitor/online/index',1,0,'C','0','0','monitor:online:list','online','admin','2021-08-09 11:18:13','',NULL,'在线用户菜单'),
(110,'定时任务',2,2,'job','monitor/job/index',1,0,'C','0','0','monitor:job:list','job','admin','2021-08-09 11:18:13','',NULL,'定时任务菜单'),
(111,'数据监控',2,3,'druid','monitor/druid/index',1,0,'C','0','0','monitor:druid:list','druid','admin','2021-08-09 11:18:13','',NULL,'数据监控菜单'),
(112,'服务监控',2,4,'server','monitor/server/index',1,0,'C','0','0','monitor:server:list','server','admin','2021-08-09 11:18:13','',NULL,'服务监控菜单'),
(113,'缓存监控',2,5,'cache','monitor/cache/index',1,0,'C','0','0','monitor:cache:list','redis','admin','2021-08-09 11:18:13','',NULL,'缓存监控菜单'),
(114,'表单构建',3,1,'build','tool/build/index',1,0,'C','0','0','tool:build:list','build','admin','2021-08-09 11:18:13','',NULL,'表单构建菜单'),
(115,'代码生成',3,2,'gen','tool/gen/index',1,0,'C','0','0','tool:gen:list','code','admin','2021-08-09 11:18:13','',NULL,'代码生成菜单'),
(116,'系统接口',3,3,'swagger','tool/swagger/index',1,0,'C','0','0','tool:swagger:list','swagger','admin','2021-08-09 11:18:13','',NULL,'系统接口菜单'),
(500,'操作日志',108,1,'operlog','monitor/operlog/index',1,0,'C','0','0','monitor:operlog:list','form','admin','2021-08-09 11:18:13','',NULL,'操作日志菜单'),
(501,'登录日志',108,2,'logininfor','monitor/logininfor/index',1,0,'C','0','0','sys:info:list','logininfor','admin','2021-08-09 11:18:14','',NULL,'登录日志菜单'),
(1001,'用户查询',100,1,'','',1,0,'F','0','0','system:user:query','#','admin','2021-08-09 11:18:14','',NULL,''),
(1002,'用户新增',100,2,'','',1,0,'F','0','0','system:user:add','#','admin','2021-08-09 11:18:14','',NULL,''),
(1003,'用户修改',100,3,'','',1,0,'F','0','0','system:user:edit','#','admin','2021-08-09 11:18:14','',NULL,''),
(1004,'用户删除',100,4,'','',1,0,'F','0','0','system:user:remove','#','admin','2021-08-09 11:18:14','',NULL,''),
(1005,'用户导出',100,5,'','',1,0,'F','0','0','system:user:export','#','admin','2021-08-09 11:18:14','',NULL,''),
(1006,'用户导入',100,6,'','',1,0,'F','0','0','system:user:import','#','admin','2021-08-09 11:18:14','',NULL,''),
(1007,'重置密码',100,7,'','',1,0,'F','0','0','system:user:resetPwd','#','admin','2021-08-09 11:18:14','',NULL,''),
(1008,'角色查询',101,1,'','',1,0,'F','0','0','system:role:query','#','admin','2021-08-09 11:18:14','',NULL,''),
(1009,'角色新增',101,2,'','',1,0,'F','0','0','system:role:add','#','admin','2021-08-09 11:18:14','',NULL,''),
(1010,'角色修改',101,3,'','',1,0,'F','0','0','system:role:edit','#','admin','2021-08-09 11:18:15','',NULL,''),
(1011,'角色删除',101,4,'','',1,0,'F','0','0','system:role:remove','#','admin','2021-08-09 11:18:15','',NULL,''),
(1012,'角色导出',101,5,'','',1,0,'F','0','0','system:role:export','#','admin','2021-08-09 11:18:15','',NULL,''),
(1013,'菜单查询',102,1,'','',1,0,'F','0','0','system:menu:query','#','admin','2021-08-09 11:18:15','',NULL,''),
(1014,'菜单新增',102,2,'','',1,0,'F','0','0','system:menu:add','#','admin','2021-08-09 11:18:15','',NULL,''),
(1015,'菜单修改',102,3,'','',1,0,'F','0','0','system:menu:edit','#','admin','2021-08-09 11:18:15','',NULL,''),
(1016,'菜单删除',102,4,'','',1,0,'F','0','0','system:menu:remove','#','admin','2021-08-09 11:18:15','',NULL,''),
(1017,'部门查询',103,1,'','',1,0,'F','0','0','system:dept:query','#','admin','2021-08-09 11:18:15','',NULL,''),
(1018,'部门新增',103,2,'','',1,0,'F','0','0','system:dept:add','#','admin','2021-08-09 11:18:15','',NULL,''),
(1019,'部门修改',103,3,'','',1,0,'F','0','0','system:dept:edit','#','admin','2021-08-09 11:18:15','',NULL,''),
(1020,'部门删除',103,4,'','',1,0,'F','0','0','system:dept:remove','#','admin','2021-08-09 11:18:16','',NULL,''),
(1021,'岗位查询',104,1,'','',1,0,'F','0','0','system:post:query','#','admin','2021-08-09 11:18:16','',NULL,''),
(1022,'岗位新增',104,2,'','',1,0,'F','0','0','system:post:add','#','admin','2021-08-09 11:18:16','',NULL,''),
(1023,'岗位修改',104,3,'','',1,0,'F','0','0','system:post:edit','#','admin','2021-08-09 11:18:16','',NULL,''),
(1024,'岗位删除',104,4,'','',1,0,'F','0','0','system:post:remove','#','admin','2021-08-09 11:18:16','',NULL,''),
(1025,'岗位导出',104,5,'','',1,0,'F','0','0','system:post:export','#','admin','2021-08-09 11:18:16','',NULL,''),
(1026,'字典查询',105,1,'#','',1,0,'F','0','0','system:dict:query','#','admin','2021-08-09 11:18:16','',NULL,''),
(1027,'字典新增',105,2,'#','',1,0,'F','0','0','system:dict:add','#','admin','2021-08-09 11:18:17','',NULL,''),
(1028,'字典修改',105,3,'#','',1,0,'F','0','0','system:dict:edit','#','admin','2021-08-09 11:18:17','',NULL,''),
(1029,'字典删除',105,4,'#','',1,0,'F','0','0','system:dict:remove','#','admin','2021-08-09 11:18:17','',NULL,''),
(1030,'字典导出',105,5,'#','',1,0,'F','0','0','system:dict:export','#','admin','2021-08-09 11:18:17','',NULL,''),
(1031,'参数查询',106,1,'#','',1,0,'F','0','0','system:config:query','#','admin','2021-08-09 11:18:17','',NULL,''),
(1032,'参数新增',106,2,'#','',1,0,'F','0','0','system:config:add','#','admin','2021-08-09 11:18:17','',NULL,''),
(1033,'参数修改',106,3,'#','',1,0,'F','0','0','system:config:edit','#','admin','2021-08-09 11:18:17','',NULL,''),
(1034,'参数删除',106,4,'#','',1,0,'F','0','0','system:config:remove','#','admin','2021-08-09 11:18:18','',NULL,''),
(1035,'参数导出',106,5,'#','',1,0,'F','0','0','system:config:export','#','admin','2021-08-09 11:18:18','',NULL,''),
(1036,'公告查询',107,1,'#','',1,0,'F','0','0','system:notice:query','#','admin','2021-08-09 11:18:18','',NULL,''),
(1037,'公告新增',107,2,'#','',1,0,'F','0','0','system:notice:add','#','admin','2021-08-09 11:18:18','',NULL,''),
(1038,'公告修改',107,3,'#','',1,0,'F','0','0','system:notice:edit','#','admin','2021-08-09 11:18:18','',NULL,''),
(1039,'公告删除',107,4,'#','',1,0,'F','0','0','system:notice:remove','#','admin','2021-08-09 11:18:18','',NULL,''),
(1040,'操作查询',500,1,'#','',1,0,'F','0','0','monitor:operlog:query','#','admin','2021-08-09 11:18:19','',NULL,''),
(1041,'操作删除',500,2,'#','',1,0,'F','0','0','monitor:operlog:remove','#','admin','2021-08-09 11:18:19','',NULL,''),
(1042,'日志导出',500,4,'#','',1,0,'F','0','0','monitor:operlog:export','#','admin','2021-08-09 11:18:19','',NULL,''),
(1043,'登录查询',501,1,'#','',1,0,'F','0','0','monitor:logininfor:query','#','admin','2021-08-09 11:18:19','',NULL,''),
(1044,'登录删除',501,2,'#','',1,0,'F','0','0','monitor:logininfor:remove','#','admin','2021-08-09 11:18:19','',NULL,''),
(1045,'日志导出',501,3,'#','',1,0,'F','0','0','monitor:logininfor:export','#','admin','2021-08-09 11:18:20','',NULL,''),
(1046,'在线查询',109,1,'#','',1,0,'F','0','0','monitor:online:query','#','admin','2021-08-09 11:18:20','',NULL,''),
(1047,'批量强退',109,2,'#','',1,0,'F','0','0','monitor:online:batchLogout','#','admin','2021-08-09 11:18:20','',NULL,''),
(1048,'单条强退',109,3,'#','',1,0,'F','0','0','monitor:online:forceLogout','#','admin','2021-08-09 11:18:20','',NULL,''),
(1049,'任务查询',110,1,'#','',1,0,'F','0','0','monitor:job:query','#','admin','2021-08-09 11:18:20','',NULL,''),
(1050,'任务新增',110,2,'#','',1,0,'F','0','0','monitor:job:add','#','admin','2021-08-09 11:18:20','',NULL,''),
(1051,'任务修改',110,3,'#','',1,0,'F','0','0','monitor:job:edit','#','admin','2021-08-09 11:18:20','',NULL,''),
(1052,'任务删除',110,4,'#','',1,0,'F','0','0','monitor:job:remove','#','admin','2021-08-09 11:18:21','',NULL,''),
(1053,'状态修改',110,5,'#','',1,0,'F','0','0','monitor:job:changeStatus','#','admin','2021-08-09 11:18:21','',NULL,''),
(1054,'任务导出',110,7,'#','',1,0,'F','0','0','monitor:job:export','#','admin','2021-08-09 11:18:21','',NULL,''),
(1055,'生成查询',115,1,'#','',1,0,'F','0','0','tool:gen:query','#','admin','2021-08-09 11:18:21','',NULL,''),
(1056,'生成修改',115,2,'#','',1,0,'F','0','0','tool:gen:edit','#','admin','2021-08-09 11:18:21','',NULL,''),
(1057,'生成删除',115,3,'#','',1,0,'F','0','0','tool:gen:remove','#','admin','2021-08-09 11:18:21','',NULL,''),
(1058,'导入代码',115,2,'#','',1,0,'F','0','0','tool:gen:import','#','admin','2021-08-09 11:18:21','',NULL,''),
(1059,'预览代码',115,4,'#','',1,0,'F','0','0','tool:gen:preview','#','admin','2021-08-09 11:18:22','',NULL,''),
(1060,'生成代码',115,5,'#','',1,0,'F','0','0','tool:gen:code','#','admin','2021-08-09 11:18:22','',NULL,'');

/*Table structure for table `sys_notice` */

DROP TABLE IF EXISTS `sys_notice`;

CREATE TABLE `sys_notice` (
  `notice_id` int(4) NOT NULL AUTO_INCREMENT COMMENT '公告ID',
  `notice_title` varchar(50) NOT NULL COMMENT '公告标题',
  `notice_type` char(1) NOT NULL COMMENT '公告类型（1通知 2公告）',
  `notice_content` longblob COMMENT '公告内容',
  `status` char(1) DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`notice_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='通知公告表';

/*Data for the table `sys_notice` */

insert  into `sys_notice`(`notice_id`,`notice_title`,`notice_type`,`notice_content`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values 
(1,'温馨提醒：2018-07-01 若依新版本发布啦','2','新版本内容','0','admin','2021-08-09 11:18:38','',NULL,'管理员'),
(2,'维护通知：2018-07-01 若依系统凌晨维护','1','维护内容','0','admin','2021-08-09 11:18:39','',NULL,'管理员');

/*Table structure for table `sys_oper_log` */

DROP TABLE IF EXISTS `sys_oper_log`;

CREATE TABLE `sys_oper_log` (
  `oper_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '日志主键',
  `title` varchar(50) DEFAULT '' COMMENT '模块标题',
  `business_type` int(2) DEFAULT '0' COMMENT '业务类型（0其它 1新增 2修改 3删除）',
  `method` varchar(100) DEFAULT '' COMMENT '方法名称',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方式',
  `operator_type` int(1) DEFAULT '0' COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
  `oper_name` varchar(50) DEFAULT '' COMMENT '操作人员',
  `t_id` varchar(50) DEFAULT '' COMMENT '平台id',
  `oper_url` varchar(255) DEFAULT '' COMMENT '请求URL',
  `oper_ip` varchar(128) DEFAULT '' COMMENT '主机地址',
  `oper_location` varchar(255) DEFAULT '' COMMENT '操作地点',
  `oper_param` varchar(2000) DEFAULT '' COMMENT '请求参数',
  `json_result` varchar(2000) DEFAULT '' COMMENT '返回参数',
  `status` int(1) DEFAULT '0' COMMENT '操作状态（0正常 1异常）',
  `error_msg` varchar(2000) DEFAULT '' COMMENT '错误消息',
  `oper_time` datetime DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`oper_id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COMMENT='操作日志记录';

/*Data for the table `sys_oper_log` */

insert  into `sys_oper_log`(`oper_id`,`title`,`business_type`,`method`,`request_method`,`operator_type`,`oper_name`,`t_id`,`oper_url`,`oper_ip`,`oper_location`,`oper_param`,`json_result`,`status`,`error_msg`,`oper_time`) values 
(1,'添加黑名单',1,'com.manager.web.controller.system.SysIpController.addBlack()','GET',1,'admin',NULL,'/system/ip/black/add','127.0.0.1','内网IP','{}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-08-12 07:04:25'),
(2,'添加黑名单',1,'com.manager.web.controller.system.SysIpController.addBlack()','GET',1,'admin',NULL,'/system/ip/black/add','127.0.0.1','内网IP','{}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-08-12 07:19:21'),
(3,'添加黑名单',1,'com.manager.web.controller.system.SysIpController.addBlack()','GET',1,'admin',NULL,'/system/ip/black/add','127.0.0.1','内网IP','org.apache.catalina.util.ParameterMap@62a6ad98','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-08-12 07:20:56'),
(4,'添加黑名单',1,'com.manager.web.controller.system.SysIpController.addBlack()','GET',1,'admin',NULL,'/system/ip/black/add','127.0.0.1','内网IP','{\"deptId\":[\"100\"],\"userId\":[\"1\"],\"ips\":[\"127.0.0.1\"]}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-08-12 07:24:03'),
(5,'用户管理',1,'com.manager.web.controller.system.SysUserController.add()','POST',1,'admin',NULL,'/system/user/add','127.0.0.1','内网IP','{\"googleKey\":\"\",\"nickName\":\"\",\"remark\":\"\",\"params\":{},\"userName\":\"test001\",\"userId\":\"\",\"tId\":\"1\",\"createBy\":\"admin\",\"password\":\"$2a$10$NOaYqTalT2Ywxw9zKKz6C.9dJ4HZ6lO.9dGzEf/q6aUXXQbkRK54G\",\"googleSwitch\":false,\"updateBy\":\"\",\"searchValue\":\"\",\"status\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-08-12 22:18:16'),
(6,'用户管理',1,'com.manager.web.controller.system.SysUserController.add()','POST',1,'admin',NULL,'/system/user/add','127.0.0.1','内网IP','{\"googleKey\":\"\",\"nickName\":\"\",\"remark\":\"\",\"params\":{},\"userName\":\"test001\",\"userId\":\"\",\"tId\":\"1\",\"createBy\":\"\",\"password\":\"123456\",\"googleSwitch\":false,\"updateBy\":\"\",\"searchValue\":\"\",\"status\":\"\"}','{\"msg\":\"新增用户\'test001\'失败，登录账号已存在\",\"code\":500}',0,NULL,'2021-08-12 22:20:58'),
(7,'用户管理',1,'com.manager.web.controller.system.SysUserController.add()','POST',1,'admin',NULL,'/system/user/add','127.0.0.1','内网IP','{\"googleKey\":\"\",\"nickName\":\"\",\"remark\":\"\",\"params\":{},\"userName\":\"test002\",\"userId\":\"\",\"tId\":\"1\",\"createBy\":\"admin\",\"password\":\"$2a$10$QNGoqJd5qO8I.AVDiTvLpu4tGZ2dCRN1aRowjSZ2ny3HDLnQvL0Zy\",\"googleSwitch\":false,\"updateBy\":\"\",\"searchValue\":\"\",\"status\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-08-12 22:21:39'),
(8,'用户管理',1,'com.manager.web.controller.system.SysUserController.add()','POST',1,'admin',NULL,'/system/user/add','127.0.0.1','内网IP','{\"googleKey\":\"\",\"nickName\":\"\",\"remark\":\"\",\"params\":{},\"userName\":\"test002\",\"userId\":\"5\",\"tId\":\"1\",\"createBy\":\"admin\",\"password\":\"$2a$10$y0uQf5IAKBpo5JvbhRbZL.5cy7BPe1Wes6Q5oAml9nC23HxRyLP8O\",\"googleSwitch\":false,\"updateBy\":\"\",\"searchValue\":\"\",\"status\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-08-12 22:25:45'),
(9,'用户管理',1,'com.manager.web.controller.system.SysUserController.add()','POST',1,'admin',NULL,'/system/user/add','127.0.0.1','内网IP','{\"googleKey\":\"\",\"nickName\":\"\",\"roleId\":\"22\",\"remark\":\"\",\"params\":{},\"userName\":\"test002\",\"ips\":\"123.2.2.2,132.2.2.2,133.3.3\",\"userId\":\"\",\"tId\":\"1\",\"password\":\"123456\",\"googleSwitch\":false,\"updateBy\":\"\",\"searchValue\":\"\",\"status\":\"\"}','{\"msg\":\"新增用户\'test002\'失败，登录账号已存在\",\"code\":500}',0,NULL,'2021-08-12 23:05:28'),
(10,'用户管理',1,'com.manager.web.controller.system.SysUserController.add()','POST',1,'admin',NULL,'/system/user/add','127.0.0.1','内网IP','{\"googleKey\":\"\",\"nickName\":\"\",\"roleId\":\"22\",\"remark\":\"\",\"params\":{},\"userName\":\"test002\",\"ips\":\"123.2.2.2,132.2.2.2,133.3.3\",\"userId\":\"\",\"tId\":\"1\",\"password\":\"123456\",\"googleSwitch\":false,\"updateBy\":\"\",\"searchValue\":\"\",\"status\":\"\"}','{\"msg\":\"新增用户\'test002\'失败，登录账号已存在\",\"code\":500}',0,NULL,'2021-08-12 23:05:46'),
(11,'用户管理',1,'com.manager.web.controller.system.SysUserController.add()','POST',1,'admin',NULL,'/system/user/add','127.0.0.1','内网IP','{\"googleKey\":\"\",\"nickName\":\"\",\"roleId\":\"22\",\"remark\":\"\",\"params\":{},\"userName\":\"test002\",\"ips\":\"123.2.2.2,132.2.2.2,133.3.3\",\"userId\":\"6\",\"tId\":\"1\",\"createBy\":\"admin\",\"password\":\"$2a$10$fFEXVREzFk9TALhCYd4XNOUa0P3649waFFFQmC/G28zp3rS6XwPa.\",\"googleSwitch\":false,\"updateBy\":\"\",\"searchValue\":\"\",\"status\":\"\"}','null',1,'\r\n### Error updating database.  Cause: java.sql.SQLSyntaxErrorException: Unknown column \'dept_id\' in \'field list\'\r\n### The error may exist in file [D:\\workspace\\Ak-manager\\manager-system\\target\\classes\\mapper\\system\\SysIpWhiteMapper.xml]\r\n### The error may involve defaultParameterMap\r\n### The error occurred while setting parameters\r\n### SQL: insert into sys_ip_white(             dept_id,             user_id,             ip,             create_user_id)         values                        (                 ?,                 ?,                 ?,                 ?             )          ,              (                 ?,                 ?,                 ?,                 ?             )          ,              (                 ?,                 ?,                 ?,                 ?             )\r\n### Cause: java.sql.SQLSyntaxErrorException: Unknown column \'dept_id\' in \'field list\'\n; bad SQL grammar []; nested exception is java.sql.SQLSyntaxErrorException: Unknown column \'dept_id\' in \'field list\'','2021-08-12 23:06:07'),
(12,'用户管理',1,'com.manager.web.controller.system.SysUserController.add()','POST',1,'admin',NULL,'/system/user/add','127.0.0.1','内网IP','{\"googleKey\":\"\",\"nickName\":\"\",\"roleId\":\"22\",\"remark\":\"\",\"params\":{},\"userName\":\"test002\",\"ips\":\"123.2.2.2,132.2.2.2,133.3.3\",\"userId\":\"7\",\"tId\":\"1\",\"createBy\":\"admin\",\"password\":\"$2a$10$i5bbPNSLev/VANAbM/iDk.Vlr5.vixeXizz4hFXzBFb/IN5kHRsMW\",\"googleSwitch\":false,\"updateBy\":\"\",\"searchValue\":\"\",\"status\":\"\"}','null',1,'nested exception is org.apache.ibatis.reflection.ReflectionException: There is no getter for property named \'tId\' in \'class com.manager.common.core.domain.entity.SysIpWhite\'','2021-08-12 23:07:01'),
(13,'用户管理',1,'com.manager.web.controller.system.SysUserController.add()','POST',1,'admin',NULL,'/system/user/add','127.0.0.1','内网IP','{\"googleKey\":\"\",\"nickName\":\"\",\"roleId\":\"22\",\"remark\":\"\",\"params\":{},\"userName\":\"test002\",\"ips\":\"123.2.2.2,132.2.2.2,133.3.3\",\"userId\":\"8\",\"tId\":\"1\",\"createBy\":\"admin\",\"password\":\"$2a$10$zvCIcyg1ngJIapP7RuqteuidpmGohX4ebTuKJDlnwUvdG1WQ7HEQi\",\"googleSwitch\":false,\"updateBy\":\"\",\"searchValue\":\"\",\"status\":\"\"}','null',1,'nested exception is org.apache.ibatis.binding.BindingException: Parameter \'item\' not found. Available parameters are [roleId, userId, param1, param2]','2021-08-12 23:09:58'),
(14,'用户管理',1,'com.manager.web.controller.system.SysUserController.add()','POST',1,'admin',NULL,'/system/user/add','127.0.0.1','内网IP','{\"googleKey\":\"\",\"nickName\":\"\",\"roleId\":\"22\",\"remark\":\"\",\"params\":{},\"userName\":\"test002\",\"ips\":\"123.2.2.2,132.2.2.2,133.3.3\",\"userId\":\"9\",\"tId\":\"1\",\"createBy\":\"admin\",\"password\":\"$2a$10$/Y.2TAvsQt0Xxy/MTZDbMeTacFO8lZ4e1PInufOV/Lrm/BwuUT.pO\",\"googleSwitch\":false,\"updateBy\":\"\",\"searchValue\":\"\",\"status\":\"\"}','null',1,'nested exception is org.apache.ibatis.binding.BindingException: Parameter \'item\' not found. Available parameters are [roleId, userId, param1, param2]','2021-08-12 23:10:21'),
(15,'添加白名单',1,'com.manager.web.controller.system.SysIpController.add()','GET',1,'admin',NULL,'/system/ip/white/add','127.0.0.1','内网IP','{\"tId\":[\"1\"],\"userId\":[\"1\"],\"ips\":[\"1223\"]}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-08-12 23:16:58'),
(16,'用户管理',1,'com.manager.web.controller.system.SysUserController.add()','POST',1,'admin',NULL,'/system/user/add','127.0.0.1','内网IP','{\"googleKey\":\"\",\"nickName\":\"\",\"roleId\":\"22\",\"remark\":\"\",\"params\":{},\"userName\":\"test002\",\"ips\":\"123.2.2.2,132.2.2.2,133.3.3\",\"userId\":\"10\",\"tId\":\"1\",\"createBy\":\"admin\",\"password\":\"$2a$10$7CDCOzWnp2e22KOVANvd3.Dqg71M14znjCAONpgfrFqbiKUnJkJwa\",\"googleSwitch\":false,\"updateBy\":\"\",\"searchValue\":\"\",\"status\":\"\"}','null',1,'nested exception is org.apache.ibatis.binding.BindingException: Parameter \'item\' not found. Available parameters are [roleId, userId, param1, param2]','2021-08-12 23:17:05'),
(17,'用户管理',1,'com.manager.web.controller.system.SysUserController.add()','POST',1,'admin',NULL,'/system/user/add','127.0.0.1','内网IP','{\"googleKey\":\"\",\"nickName\":\"\",\"roleId\":\"22\",\"remark\":\"\",\"params\":{},\"userName\":\"test002\",\"ips\":\"123.2.2.2,132.2.2.2,133.3.3\",\"userId\":\"11\",\"tId\":\"1\",\"createBy\":\"admin\",\"password\":\"$2a$10$XZaFlqDCM.0puiGOYq3wMOtjvp495F2c/ZgtsrsUsx4ojNjhi4T0O\",\"googleSwitch\":false,\"updateBy\":\"\",\"searchValue\":\"\",\"status\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-08-12 23:18:15'),
(18,'用户管理',1,'com.manager.web.controller.system.SysUserController.add()','POST',1,'admin',NULL,'/system/user/add','127.0.0.1','内网IP','{\"googleKey\":\"\",\"nickName\":\"\",\"roleId\":\"22\",\"admin\":false,\"remark\":\"\",\"params\":{},\"userName\":\"test003\",\"ips\":\"123.2.2.2,132.2.2.2,133.3.3\",\"userId\":12,\"tId\":\"1\",\"createBy\":\"admin\",\"password\":\"$2a$10$It2JG8cSprEN5G6kgTZtdeqEgE1hSeJ0rfcgnai0l5wQXJOf3cW9e\",\"googleSwitch\":false,\"updateBy\":\"\",\"searchValue\":\"\",\"status\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-08-13 13:51:26'),
(19,'用户管理',2,'com.manager.web.controller.system.SysUserController.edit()','POST',1,'admin',NULL,'/system/user/edit','127.0.0.1','内网IP','{\"googleKey\":\"\",\"nickName\":\"测试003\",\"roleId\":\"\",\"roles\":[{\"flag\":true,\"roleId\":0,\"admin\":false,\"remark\":\"\",\"dataScope\":\"\",\"delFlag\":\"\",\"params\":{},\"roleSort\":\"\",\"deptCheckStrictly\":true,\"createBy\":\"\",\"updateBy\":\"\",\"menuCheckStrictly\":true,\"roleKey\":\"\",\"roleName\":\"\",\"deptIds\":[],\"menuIds\":[],\"searchValue\":\"\",\"status\":\"\"}],\"admin\":false,\"remark\":\"\",\"delFlag\":\"\",\"params\":{},\"userName\":\"\",\"ips\":\"\",\"userId\":9,\"tId\":\"\",\"createBy\":\"\",\"password\":\"\",\"googleSwitch\":false,\"updateBy\":\"admin\",\"loginIp\":\"\",\"searchValue\":\"\",\"status\":\"\"}','{\"msg\":\"操作失败\",\"code\":500}',0,NULL,'2021-08-13 14:07:16'),
(20,'用户管理',2,'com.manager.web.controller.system.SysUserController.edit()','POST',1,'admin',NULL,'/system/user/edit','127.0.0.1','内网IP','{\"googleKey\":\"\",\"nickName\":\"测试003\",\"roleId\":\"\",\"roles\":[{\"flag\":true,\"roleId\":0,\"admin\":false,\"remark\":\"\",\"dataScope\":\"\",\"delFlag\":\"\",\"params\":{},\"roleSort\":\"\",\"deptCheckStrictly\":true,\"createBy\":\"\",\"updateBy\":\"\",\"menuCheckStrictly\":true,\"roleKey\":\"\",\"roleName\":\"\",\"deptIds\":[],\"menuIds\":[],\"searchValue\":\"\",\"status\":\"\"}],\"admin\":false,\"remark\":\"\",\"delFlag\":\"\",\"params\":{},\"userName\":\"\",\"ips\":\"\",\"userId\":9,\"tId\":\"\",\"createBy\":\"\",\"password\":\"\",\"googleSwitch\":false,\"updateBy\":\"admin\",\"loginIp\":\"\",\"searchValue\":\"\",\"status\":\"\"}','{\"msg\":\"操作失败\",\"code\":500}',0,NULL,'2021-08-13 14:18:03'),
(21,'用户管理',2,'com.manager.web.controller.system.SysUserController.edit()','POST',1,'admin',NULL,'/system/user/edit','127.0.0.1','内网IP','{\"googleKey\":\"\",\"nickName\":\"测试003\",\"roleId\":\"2\",\"roles\":[{\"flag\":true,\"roleId\":0,\"admin\":false,\"remark\":\"\",\"dataScope\":\"\",\"delFlag\":\"\",\"params\":{},\"roleSort\":\"\",\"deptCheckStrictly\":true,\"createBy\":\"\",\"updateBy\":\"\",\"menuCheckStrictly\":true,\"roleKey\":\"\",\"roleName\":\"\",\"deptIds\":[],\"menuIds\":[],\"searchValue\":\"\",\"status\":\"\"}],\"admin\":false,\"remark\":\"\",\"delFlag\":\"\",\"params\":{},\"userName\":\"\",\"ips\":\"\",\"userId\":12,\"tId\":\"\",\"createBy\":\"\",\"password\":\"\",\"googleSwitch\":false,\"updateBy\":\"admin\",\"loginIp\":\"\",\"searchValue\":\"\",\"status\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-08-13 14:19:58'),
(22,'用户管理',2,'com.manager.web.controller.system.SysUserController.edit()','POST',1,'admin',NULL,'/system/user/edit','127.0.0.1','内网IP','{\"googleKey\":\"\",\"nickName\":\"测试003\",\"roleId\":\"2\",\"roles\":[{\"flag\":true,\"roleId\":0,\"admin\":false,\"remark\":\"\",\"dataScope\":\"\",\"delFlag\":\"\",\"params\":{},\"roleSort\":\"\",\"deptCheckStrictly\":true,\"createBy\":\"\",\"updateBy\":\"\",\"menuCheckStrictly\":true,\"roleKey\":\"\",\"roleName\":\"\",\"deptIds\":[],\"menuIds\":[],\"searchValue\":\"\",\"status\":\"\"}],\"admin\":false,\"remark\":\"\",\"delFlag\":\"\",\"params\":{},\"userName\":\"\",\"ips\":\"\",\"userId\":12,\"tId\":\"\",\"createBy\":\"\",\"password\":\"\",\"googleSwitch\":false,\"updateBy\":\"admin\",\"loginIp\":\"\",\"searchValue\":\"\",\"status\":\"\"}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-08-13 14:21:47'),
(23,'删除白名单',3,'com.manager.web.controller.system.SysIpController.delete()','GET',1,'admin','103','/system/ip/white/delete','127.0.0.1','内网IP','{\"id\":[\"1\"]}','{\"msg\":\"操作成功\",\"code\":200}',0,NULL,'2021-08-13 15:28:20');

/*Table structure for table `sys_post` */

DROP TABLE IF EXISTS `sys_post`;

CREATE TABLE `sys_post` (
  `post_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '岗位ID',
  `post_code` varchar(64) NOT NULL COMMENT '岗位编码',
  `post_name` varchar(50) NOT NULL COMMENT '岗位名称',
  `post_sort` int(4) NOT NULL COMMENT '显示顺序',
  `status` char(1) NOT NULL COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`post_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COMMENT='岗位信息表';

/*Data for the table `sys_post` */

insert  into `sys_post`(`post_id`,`post_code`,`post_name`,`post_sort`,`status`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values 
(1,'ceo','董事长',1,'0','admin','2021-08-09 11:18:10','',NULL,''),
(2,'se','项目经理',2,'0','admin','2021-08-09 11:18:10','',NULL,''),
(3,'hr','人力资源',3,'0','admin','2021-08-09 11:18:10','',NULL,''),
(4,'user','普通员工',4,'0','admin','2021-08-09 11:18:11','',NULL,'');

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `role_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(4) NOT NULL COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  `menu_check_strictly` tinyint(1) DEFAULT '1' COMMENT '菜单树选择项是否关联显示',
  `dept_check_strictly` tinyint(1) DEFAULT '1' COMMENT '部门树选择项是否关联显示',
  `status` char(1) NOT NULL COMMENT '角色状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='角色信息表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`role_id`,`role_name`,`role_key`,`role_sort`,`data_scope`,`menu_check_strictly`,`dept_check_strictly`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`) values 
(1,'超级管理员','admin',1,'1',1,1,'0','0','admin','2021-08-09 11:18:11','',NULL,'超级管理员'),
(2,'普通角色','common',2,'2',1,1,'0','0','admin','2021-08-09 11:18:11','',NULL,'普通角色');

/*Table structure for table `sys_role_dept` */

DROP TABLE IF EXISTS `sys_role_dept`;

CREATE TABLE `sys_role_dept` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `dept_id` bigint(20) NOT NULL COMMENT '部门ID',
  PRIMARY KEY (`role_id`,`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色和部门关联表';

/*Data for the table `sys_role_dept` */

insert  into `sys_role_dept`(`role_id`,`dept_id`) values 
(2,100),
(2,101),
(2,105);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `menu_id` bigint(20) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色和菜单关联表';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`role_id`,`menu_id`) values 
(2,1),
(2,2),
(2,3),
(2,4),
(2,100),
(2,101),
(2,102),
(2,103),
(2,104),
(2,105),
(2,106),
(2,107),
(2,108),
(2,109),
(2,110),
(2,111),
(2,112),
(2,113),
(2,114),
(2,115),
(2,116),
(2,500),
(2,501),
(2,1000),
(2,1001),
(2,1002),
(2,1003),
(2,1004),
(2,1005),
(2,1006),
(2,1007),
(2,1008),
(2,1009),
(2,1010),
(2,1011),
(2,1012),
(2,1013),
(2,1014),
(2,1015),
(2,1016),
(2,1017),
(2,1018),
(2,1019),
(2,1020),
(2,1021),
(2,1022),
(2,1023),
(2,1024),
(2,1025),
(2,1026),
(2,1027),
(2,1028),
(2,1029),
(2,1030),
(2,1031),
(2,1032),
(2,1033),
(2,1034),
(2,1035),
(2,1036),
(2,1037),
(2,1038),
(2,1039),
(2,1040),
(2,1041),
(2,1042),
(2,1043),
(2,1044),
(2,1045),
(2,1046),
(2,1047),
(2,1048),
(2,1049),
(2,1050),
(2,1051),
(2,1052),
(2,1053),
(2,1054),
(2,1055),
(2,1056),
(2,1057),
(2,1058),
(2,1059),
(2,1060);

/*Table structure for table `sys_tenant` */

DROP TABLE IF EXISTS `sys_tenant`;

CREATE TABLE `sys_tenant` (
  `t_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '平台id',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父平台id',
  `ancestors` varchar(50) DEFAULT '' COMMENT '祖级列表',
  `t_name` varchar(30) DEFAULT '' COMMENT '平台名称',
  `status` char(1) DEFAULT '0' COMMENT '部门状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `t_type` smallint(4) NOT NULL DEFAULT '0' COMMENT '类型 0平台 1总代 2渠道',
  PRIMARY KEY (`t_id`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

/*Data for the table `sys_tenant` */

insert  into `sys_tenant`(`t_id`,`parent_id`,`ancestors`,`t_name`,`status`,`del_flag`,`create_by`,`create_time`,`update_by`,`update_time`,`t_type`) values 
(100,0,'0','大圣娱乐','0','0','admin','2021-08-09 11:18:09','',NULL,0),
(101,100,'0,100','总代1','0','0','admin','2021-08-09 11:18:09','',NULL,1),
(102,100,'0,100','总代2','0','0','admin','2021-08-09 11:18:09','',NULL,1),
(103,101,'0,100,101','渠道1','0','0','admin','2021-08-09 11:18:09','',NULL,2),
(104,101,'0,100,101','渠道2','0','0','admin','2021-08-09 11:18:09','',NULL,2),
(105,101,'0,100,101','渠道3','0','0','admin','2021-08-09 11:18:09','',NULL,2),
(106,101,'0,100,101','渠道4','0','0','admin','2021-08-09 11:18:09','',NULL,2),
(107,101,'0,100,101','渠道5','0','0','admin','2021-08-09 11:18:09','',NULL,2),
(108,102,'0,100,102','渠道1','0','0','admin','2021-08-09 11:18:09','',NULL,2),
(109,102,'0,100,102','渠道2','0','0','admin','2021-08-09 11:18:09','',NULL,2);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `user_id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `t_id` bigint(20) NOT NULL COMMENT '平台ID',
  `user_name` varchar(30) NOT NULL COMMENT '账号',
  `nick_name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `user_type` varchar(2) DEFAULT '00' COMMENT '用户类型（00系统用户）',
  `password` varchar(100) DEFAULT '' COMMENT '密码',
  `status` char(1) DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
  `login_ip` varchar(128) DEFAULT '' COMMENT '最后登录IP',
  `login_date` datetime DEFAULT NULL COMMENT '最后登录时间',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  `google_key` varchar(50) DEFAULT NULL COMMENT 'google密钥',
  `google_switch` smallint(2) NOT NULL DEFAULT '0' COMMENT '是否开启google密钥（0关闭，1开启）',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COMMENT='用户信息表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`user_id`,`t_id`,`user_name`,`nick_name`,`user_type`,`password`,`status`,`del_flag`,`login_ip`,`login_date`,`create_by`,`create_time`,`update_by`,`update_time`,`remark`,`google_key`,`google_switch`) values 
(1,103,'admin','管理员','00','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','127.0.0.1','2021-08-14 20:53:45','admin','2021-08-09 11:18:10','','2021-08-14 20:53:47','管理员','FYAAK2VSJSDJKCAH',1),
(2,105,'ry','测试001','00','$2a$10$7JB720yubVSZvUI0rEqK/.VqGOZTH.ulu33dHOiBE8ByOhJIrdAu2','0','0','127.0.0.1','2021-08-09 11:18:10','admin','2021-08-09 11:18:10','',NULL,'测试员','',0),
(3,100,'test001',NULL,'00','$2a$10$NOaYqTalT2Ywxw9zKKz6C.9dJ4HZ6lO.9dGzEf/q6aUXXQbkRK54G','0','0','',NULL,'admin','2021-08-12 22:17:38','',NULL,NULL,NULL,0),
(11,103,'test002',NULL,'00','$2a$10$XZaFlqDCM.0puiGOYq3wMOtjvp495F2c/ZgtsrsUsx4ojNjhi4T0O','0','0','',NULL,'admin','2021-08-12 23:18:14','',NULL,NULL,NULL,0),
(12,104,'test003','测试003','00','$2a$10$It2JG8cSprEN5G6kgTZtdeqEgE1hSeJ0rfcgnai0l5wQXJOf3cW9e','0','0','',NULL,'admin','2021-08-13 13:51:25','admin','2021-08-13 14:21:46','',NULL,0);

/*Table structure for table `sys_user_post` */

DROP TABLE IF EXISTS `sys_user_post`;

CREATE TABLE `sys_user_post` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `post_id` bigint(20) NOT NULL COMMENT '岗位ID',
  PRIMARY KEY (`user_id`,`post_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户与岗位关联表';

/*Data for the table `sys_user_post` */

insert  into `sys_user_post`(`user_id`,`post_id`) values 
(1,1),
(2,2);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户和角色关联表';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values 
(1,1),
(2,2),
(11,2),
(12,2);

/*Table structure for table `xxl_job_group` */

DROP TABLE IF EXISTS `xxl_job_group`;

CREATE TABLE `xxl_job_group` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `app_name` varchar(64) NOT NULL COMMENT '执行器AppName',
  `title` varchar(12) NOT NULL COMMENT '执行器名称',
  `address_type` tinyint(4) NOT NULL DEFAULT '0' COMMENT '执行器地址类型：0=自动注册、1=手动录入',
  `address_list` text COMMENT '执行器地址列表，多地址逗号分隔',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `xxl_job_group` */

insert  into `xxl_job_group`(`id`,`app_name`,`title`,`address_type`,`address_list`,`update_time`) values 
(1,'job-executor','任务执行器',0,'http://172.17.0.7:6651/','2021-08-14 21:13:33');

/*Table structure for table `xxl_job_info` */

DROP TABLE IF EXISTS `xxl_job_info`;

CREATE TABLE `xxl_job_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_desc` varchar(255) NOT NULL,
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `author` varchar(64) DEFAULT NULL COMMENT '作者',
  `alarm_email` varchar(255) DEFAULT NULL COMMENT '报警邮件',
  `schedule_type` varchar(50) NOT NULL DEFAULT 'NONE' COMMENT '调度类型',
  `schedule_conf` varchar(128) DEFAULT NULL COMMENT '调度配置，值含义取决于调度类型',
  `misfire_strategy` varchar(50) NOT NULL DEFAULT 'DO_NOTHING' COMMENT '调度过期策略',
  `executor_route_strategy` varchar(50) DEFAULT NULL COMMENT '执行器路由策略',
  `executor_handler` varchar(255) DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) DEFAULT NULL COMMENT '执行器任务参数',
  `executor_block_strategy` varchar(50) DEFAULT NULL COMMENT '阻塞处理策略',
  `executor_timeout` int(11) NOT NULL DEFAULT '0' COMMENT '任务执行超时时间，单位秒',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `glue_type` varchar(50) NOT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) DEFAULT NULL COMMENT 'GLUE备注',
  `glue_updatetime` datetime DEFAULT NULL COMMENT 'GLUE更新时间',
  `child_jobid` varchar(255) DEFAULT NULL COMMENT '子任务ID，多个逗号分隔',
  `trigger_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '调度状态：0-停止，1-运行',
  `trigger_last_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '上次调度时间',
  `trigger_next_time` bigint(13) NOT NULL DEFAULT '0' COMMENT '下次调度时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `xxl_job_info` */

/*Table structure for table `xxl_job_lock` */

DROP TABLE IF EXISTS `xxl_job_lock`;

CREATE TABLE `xxl_job_lock` (
  `lock_name` varchar(50) NOT NULL COMMENT '锁名称',
  PRIMARY KEY (`lock_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `xxl_job_lock` */

insert  into `xxl_job_lock`(`lock_name`) values 
('schedule_lock');

/*Table structure for table `xxl_job_log` */

DROP TABLE IF EXISTS `xxl_job_log`;

CREATE TABLE `xxl_job_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `job_group` int(11) NOT NULL COMMENT '执行器主键ID',
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `executor_address` varchar(255) DEFAULT NULL COMMENT '执行器地址，本次执行的地址',
  `executor_handler` varchar(255) DEFAULT NULL COMMENT '执行器任务handler',
  `executor_param` varchar(512) DEFAULT NULL COMMENT '执行器任务参数',
  `executor_sharding_param` varchar(20) DEFAULT NULL COMMENT '执行器任务分片参数，格式如 1/2',
  `executor_fail_retry_count` int(11) NOT NULL DEFAULT '0' COMMENT '失败重试次数',
  `trigger_time` datetime DEFAULT NULL COMMENT '调度-时间',
  `trigger_code` int(11) NOT NULL COMMENT '调度-结果',
  `trigger_msg` text COMMENT '调度-日志',
  `handle_time` datetime DEFAULT NULL COMMENT '执行-时间',
  `handle_code` int(11) NOT NULL COMMENT '执行-状态',
  `handle_msg` text COMMENT '执行-日志',
  `alarm_status` tinyint(4) NOT NULL DEFAULT '0' COMMENT '告警状态：0-默认、1-无需告警、2-告警成功、3-告警失败',
  PRIMARY KEY (`id`),
  KEY `I_trigger_time` (`trigger_time`),
  KEY `I_handle_code` (`handle_code`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4;

/*Data for the table `xxl_job_log` */

/*Table structure for table `xxl_job_log_report` */

DROP TABLE IF EXISTS `xxl_job_log_report`;

CREATE TABLE `xxl_job_log_report` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `trigger_day` datetime DEFAULT NULL COMMENT '调度-时间',
  `running_count` int(11) NOT NULL DEFAULT '0' COMMENT '运行中-日志数量',
  `suc_count` int(11) NOT NULL DEFAULT '0' COMMENT '执行成功-日志数量',
  `fail_count` int(11) NOT NULL DEFAULT '0' COMMENT '执行失败-日志数量',
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_trigger_day` (`trigger_day`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

/*Data for the table `xxl_job_log_report` */

insert  into `xxl_job_log_report`(`id`,`trigger_day`,`running_count`,`suc_count`,`fail_count`,`update_time`) values 
(1,'2021-08-13 00:00:00',0,0,0,NULL),
(2,'2021-08-12 00:00:00',0,0,0,NULL),
(3,'2021-08-11 00:00:00',0,0,0,NULL),
(4,'2021-08-14 00:00:00',0,0,0,NULL);

/*Table structure for table `xxl_job_logglue` */

DROP TABLE IF EXISTS `xxl_job_logglue`;

CREATE TABLE `xxl_job_logglue` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `job_id` int(11) NOT NULL COMMENT '任务，主键ID',
  `glue_type` varchar(50) DEFAULT NULL COMMENT 'GLUE类型',
  `glue_source` mediumtext COMMENT 'GLUE源代码',
  `glue_remark` varchar(128) NOT NULL COMMENT 'GLUE备注',
  `add_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

/*Data for the table `xxl_job_logglue` */

/*Table structure for table `xxl_job_registry` */

DROP TABLE IF EXISTS `xxl_job_registry`;

CREATE TABLE `xxl_job_registry` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `registry_group` varchar(50) NOT NULL,
  `registry_key` varchar(255) NOT NULL,
  `registry_value` varchar(255) NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `i_g_k_v` (`registry_group`,`registry_key`,`registry_value`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4;

/*Data for the table `xxl_job_registry` */

insert  into `xxl_job_registry`(`id`,`registry_group`,`registry_key`,`registry_value`,`update_time`) values 
(13,'EXECUTOR','job-executor','http://172.17.0.7:6651/','2021-08-14 21:13:11');

/*Table structure for table `xxl_job_user` */

DROP TABLE IF EXISTS `xxl_job_user`;

CREATE TABLE `xxl_job_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL COMMENT '账号',
  `password` varchar(50) NOT NULL COMMENT '密码',
  `role` tinyint(4) NOT NULL COMMENT '角色：0-普通用户、1-管理员',
  `permission` varchar(255) DEFAULT NULL COMMENT '权限：执行器ID列表，多个逗号分割',
  PRIMARY KEY (`id`),
  UNIQUE KEY `i_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

/*Data for the table `xxl_job_user` */

insert  into `xxl_job_user`(`id`,`username`,`password`,`role`,`permission`) values 
(1,'admin','e10adc3949ba59abbe56e057f20f883e',1,NULL);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

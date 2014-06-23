# Host: 127.0.0.1  (Version: 5.5.29)
# Date: 2013-09-24 15:13:23
# Generator: MySQL-Front 5.3  (Build 2.30)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE */;
/*!40101 SET SQL_MODE='STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES */;
/*!40103 SET SQL_NOTES='ON' */;

DROP DATABASE IF EXISTS `dmcweb`;
CREATE DATABASE `dmcweb` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `dmcweb`;




#
# Source for table "t_blacklist_config"
#

DROP TABLE IF EXISTS `t_blacklist_config`;
CREATE TABLE `t_blacklist_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键：自增',
  `classify` char(1) DEFAULT NULL COMMENT '黑名单分类: 1 关键字，2 IP ，3 邮箱 4.HTTP',
  `name` varchar(255) DEFAULT NULL COMMENT '黑白名单',
  `descn` varchar(255) DEFAULT NULL COMMENT '描述',
  `status` char(1) DEFAULT NULL COMMENT '状态： 0停用 1启用',
  `created_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_user` varchar(45) DEFAULT NULL COMMENT '创建用户',
  `updated_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_user` varchar(45) DEFAULT NULL COMMENT '更新用户',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='黑白名单配置表';

#
# Data for table "t_blacklist_config"
#


#
# Source for table "t_commerce_record"
#

DROP TABLE IF EXISTS `t_commerce_record`;
CREATE TABLE `t_commerce_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键：自增',
  `time` datetime DEFAULT NULL COMMENT '记录时间',
  `url` varchar(255) DEFAULT NULL COMMENT '访问URL',
  `goods` varchar(255) DEFAULT NULL COMMENT '购买商品',
  `account` varchar(45) DEFAULT NULL COMMENT '登陆账户',
  `password` varchar(64) DEFAULT NULL COMMENT '登陆密码',
  `source_ip` varchar(20) DEFAULT NULL COMMENT '源IP地址',
  `source_mac` varchar(18) DEFAULT NULL COMMENT '源MAC',
  `source_belong` varchar(45) DEFAULT NULL COMMENT '源IP归属地',
  `dest_ip` varchar(20) DEFAULT NULL COMMENT '目标IP地址',
  `dest_mac` varchar(18) DEFAULT NULL COMMENT '目标MAC',
  `dest_belong` varchar(45) DEFAULT NULL COMMENT '目标IP归属地',
  `is_black` char(1) DEFAULT NULL COMMENT '是否黑名单： 0 否 1 是',
  `is_read` char(1) DEFAULT NULL COMMENT '是否已读： 0 否 1 是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='电子商务记录';

#
# Data for table "t_commerce_record"
#


#
# Source for table "t_control_record"
#

DROP TABLE IF EXISTS `t_control_record`;
CREATE TABLE `t_control_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键：自增',
  `time` datetime DEFAULT NULL COMMENT '记录时间',
  `command` varchar(255) DEFAULT NULL COMMENT '命令',
  `tool_name` varchar(45) DEFAULT NULL COMMENT '工具名称',
  `account` varchar(45) DEFAULT NULL COMMENT '登陆账户',
  `password` varchar(64) DEFAULT NULL COMMENT '登陆密码',
  `source_ip` varchar(20) DEFAULT NULL COMMENT '源IP地址',
  `source_mac` varchar(18) DEFAULT NULL COMMENT '源MAC',
  `source_belong` varchar(45) DEFAULT NULL COMMENT '源IP归属地',
  `source_port` int(11) DEFAULT NULL COMMENT '源端口',
  `dest_ip` varchar(20) DEFAULT NULL COMMENT '目标IP地址',
  `dest_mac` varchar(18) DEFAULT NULL COMMENT '目标MAC',
  `dest_belong` varchar(45) DEFAULT NULL COMMENT '目标IP归属地',
  `dest_port` int(11) DEFAULT NULL COMMENT '目标端口',
  `control_classify` char(1) DEFAULT NULL COMMENT '控制分类： 1 控制 ，2 被控制',
  `is_black` char(1) DEFAULT NULL COMMENT '是否黑名单： 0 否 1 是',
  `is_read` char(1) DEFAULT NULL COMMENT '是否已读： 0 否 1 是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='远程控制记录，包含telnet';

#
# Data for table "t_control_record"
#


#
# Source for table "t_ftp_record"
#

DROP TABLE IF EXISTS `t_ftp_record`;
CREATE TABLE `t_ftp_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键：自增',
  `time` datetime DEFAULT NULL COMMENT '记录时间',
  `file_name` varchar(45) DEFAULT NULL COMMENT '保存后的文件名',
  `file_old_name` varchar(45) DEFAULT NULL COMMENT '文件原名',
  `file_size` int(11) DEFAULT NULL COMMENT '文件大小',
  `file_classify` char(1) DEFAULT NULL COMMENT '文件分类：上传1，下载2',
  `protocol` char(1) DEFAULT NULL COMMENT '协议： 1 ftp , 2 ftps, 3 sftp',
  `host` varchar(20) DEFAULT NULL COMMENT '主机地址',
  `port` int(11) DEFAULT NULL COMMENT '端口',
  `root_directory` varchar(45) DEFAULT NULL COMMENT 'FTP根目录',
  `account` varchar(45) DEFAULT NULL COMMENT '登陆账户',
  `password` varchar(64) DEFAULT NULL COMMENT '登陆密码',
  `source_ip` varchar(20) DEFAULT NULL COMMENT '源IP地址',
  `source_mac` varchar(18) DEFAULT NULL COMMENT '源MAC',
  `source_belong` varchar(45) DEFAULT NULL COMMENT '源IP归属地',
  `dest_ip` varchar(20) DEFAULT NULL COMMENT '目标IP地址',
  `dest_mac` varchar(18) DEFAULT NULL COMMENT '目标MAC',
  `dest_belong` varchar(45) DEFAULT NULL COMMENT '目标IP归属地',
  `is_black` char(1) DEFAULT NULL COMMENT '是否黑名单： 0 否 1 是',
  `is_read` char(1) DEFAULT NULL COMMENT '是否已读 ： 0 未读 1 已读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='FTP记录';

#
# Data for table "t_ftp_record"
#


#
# Source for table "t_http_record"
#

DROP TABLE IF EXISTS `t_http_record`;
CREATE TABLE `t_http_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `time` datetime DEFAULT NULL COMMENT '记录时间',
  `source_ip` varchar(20) DEFAULT NULL COMMENT '源IP地址',
  `dest_ip` varchar(20) DEFAULT NULL COMMENT '目标IP地址',
  `url` varchar(255) DEFAULT NULL COMMENT '网址',
  `source_mac` varchar(18) DEFAULT NULL COMMENT '源MAC',
  `dest_mac` varchar(18) DEFAULT NULL COMMENT '目标MAC',
  `source_belong` varchar(45) DEFAULT NULL COMMENT '源IP归属地',
  `dest_belong` varchar(45) DEFAULT NULL COMMENT '目标IP归属地',
  `title` varchar(255) DEFAULT NULL COMMENT '网页标题',
  `keyword` varchar(255) DEFAULT NULL COMMENT '内容关键字',
  `is_black` char(1) DEFAULT NULL COMMENT '是否黑名单： 0 否  1 是',
  `is_read` char(1) DEFAULT NULL COMMENT '是否已读： 0 未读   1 已读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='HTTP(S)网页浏览记录';

#
# Data for table "t_http_record"
#


#
# Source for table "t_im_record"
#

DROP TABLE IF EXISTS `t_im_record`;
CREATE TABLE `t_im_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键：自增',
  `time` datetime DEFAULT NULL COMMENT '记录时间',
  `im_classify` char(1) DEFAULT NULL COMMENT '即时通讯名称：1 MSN、 2 Skype、3 QQ、4 飞信、5 微信',
  `source_ip` varchar(20) DEFAULT NULL COMMENT '源IP地址',
  `source_mac` varchar(18) DEFAULT NULL COMMENT '源MAC',
  `dest_ip` varchar(20) DEFAULT NULL COMMENT '目标IP地址',
  `dest_mac` varchar(18) DEFAULT NULL COMMENT '目标MAC',
  `source_account` varchar(45) DEFAULT NULL COMMENT '源账户',
  `source_password` varchar(64) DEFAULT NULL COMMENT '源密码',
  `dest_account` varchar(45) DEFAULT NULL COMMENT '目标账户',
  `dest_password` varchar(64) DEFAULT NULL COMMENT '目标密码',
  `source_belong` varchar(45) DEFAULT NULL COMMENT '源IP归属地',
  `dest_belong` varchar(45) DEFAULT NULL COMMENT '目标IP归属地',
  `message` text COMMENT '聊天消息',
  `file_old_name` varchar(45) DEFAULT NULL COMMENT '文件原名',
  `file_name` varchar(45) DEFAULT NULL COMMENT '保存的文件名称',
  `file_size` int(11) DEFAULT NULL COMMENT '文件大小',
  `file_classify` char(1) DEFAULT NULL COMMENT '文件分类：1发送，2接收',
  `is_black` char(1) DEFAULT NULL COMMENT '是否黑名单： 0 否 1 是',
  `is_read` char(1) DEFAULT NULL COMMENT '是否已读： 0 否 1 是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='即时通讯记录';

#
# Data for table "t_im_record"
#


#
# Source for table "t_mail_record"
#

DROP TABLE IF EXISTS `t_mail_record`;
CREATE TABLE `t_mail_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键：自增',
  `time` datetime DEFAULT NULL COMMENT '记录时间',
  `source_ip` varchar(20) DEFAULT NULL COMMENT '源IP地址',
  `source_mac` varchar(18) DEFAULT NULL COMMENT '源MAC',
  `source_belong` varchar(45) DEFAULT NULL COMMENT '源IP归属地',
  `dest_ip` varchar(20) DEFAULT NULL COMMENT '目标IP地址',
  `dest_mac` varchar(18) DEFAULT NULL COMMENT '目标MAC',
  `dest_belong` varchar(45) DEFAULT NULL COMMENT '目标IP归属地',
  `account` varchar(45) DEFAULT NULL COMMENT '账户',
  `password` varchar(64) DEFAULT NULL COMMENT '密码',
  `url` varchar(255) DEFAULT NULL COMMENT 'web邮箱url',
  `cookie` text COMMENT 'cookie信息',
  `from` varchar(45) DEFAULT NULL COMMENT '发送者',
  `to` text COMMENT '接收者',
  `cc` text COMMENT '抄送',
  `bcc` text COMMENT '密送',
  `subject` text COMMENT '邮件主题',
  `attach_old_name` varchar(45) DEFAULT NULL COMMENT '附件文件原名',
  `attach_name` varchar(45) DEFAULT NULL COMMENT '附件文件保存名称',
  `attach_size` int(11) DEFAULT NULL COMMENT '附件文件大小',
  `content_name` varchar(45) DEFAULT NULL COMMENT '邮件内容保存文件名',
  `classify` char(1) DEFAULT NULL COMMENT '分类 1：POP3，2：SMTP， 3： WEB收， 4：WEB发， 5： 存草稿，6：收草稿， 7.WEB邮件渗透',
  `is_black` char(1) DEFAULT NULL COMMENT '是否黑名单： 0 否 1 是',
  `is_read` char(1) DEFAULT NULL COMMENT '是否已读： 0 否 1 是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='邮件记录';

#
# Data for table "t_mail_record"
#


#
# Source for table "t_netdisk_record"
#

DROP TABLE IF EXISTS `t_netdisk_record`;
CREATE TABLE `t_netdisk_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键：自增',
  `time` datetime DEFAULT NULL COMMENT '记录时间',
  `file_old_name` varchar(45) DEFAULT NULL COMMENT '文件原名',
  `file_name` varchar(45) DEFAULT NULL COMMENT '保存的文件名',
  `file_size` int(11) DEFAULT NULL COMMENT '文件大小',
  `file_classify` char(1) DEFAULT NULL COMMENT '文件分类：1 上传  2下载',
  `url` varchar(255) DEFAULT NULL COMMENT '网盘地址',
  `account` varchar(45) DEFAULT NULL COMMENT '登陆账户',
  `password` varchar(64) DEFAULT NULL COMMENT '登陆密码',
  `source_ip` varchar(20) DEFAULT NULL COMMENT '源IP地址',
  `source_mac` varchar(18) DEFAULT NULL COMMENT '源MAC',
  `source_belong` varchar(45) DEFAULT NULL COMMENT '源归属地',
  `dest_ip` varchar(20) DEFAULT NULL COMMENT '目标IP地址',
  `dest_mac` varchar(18) DEFAULT NULL COMMENT '目标MAC',
  `dest_belong` varchar(45) DEFAULT NULL COMMENT '目标归属地',
  `is_black` char(1) DEFAULT NULL COMMENT '是否黑名单：0 否 1 是',
  `is_read` char(1) DEFAULT NULL COMMENT '是否已读： 0 未读 1 已读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网盘记录';

#
# Data for table "t_netdisk_record"
#


#
# Source for table "t_parameter_config"
#

DROP TABLE IF EXISTS `t_parameter_config`;
CREATE TABLE `t_parameter_config` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键：自增',
  `parameter_code` varchar(45) DEFAULT NULL COMMENT '参数编码（唯一）',
  `parameter_name` varchar(45) DEFAULT NULL COMMENT '参数名',
  `parameter_value` varchar(255) DEFAULT NULL COMMENT '参数值',
  `status` char(1) DEFAULT NULL COMMENT '0:停用，1：启用',
  `descn` varchar(255) DEFAULT NULL COMMENT '描述',
  `created_user` varchar(45) DEFAULT NULL COMMENT '创建者用户名',
  `created_time` datetime DEFAULT NULL COMMENT '创建日期',
  `updated_user` varchar(45) DEFAULT NULL COMMENT '更新者用户名',
  `updated_time` datetime DEFAULT NULL COMMENT '更新日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='参数配置表';

#
# Data for table "t_parameter_config"
#

INSERT INTO `t_parameter_config` VALUES (1,'ftp_file_path','FTP文件保存路径','/data/ftp/','1',NULL,NULL,NULL,NULL,NULL),(2,'netdisk_file_path','网盘文件保存路径','/data/netdisk/','1',NULL,NULL,NULL,NULL,NULL),(3,'im_file_path','即时聊天工具文件保存路径','/data/im/','1',NULL,NULL,NULL,NULL,NULL),(4,'attach_file_path','附件文件路径','/data/mail/attach/','1',NULL,NULL,NULL,NULL,NULL),(5,'content_file_path','邮件类型文件保存路径','/data/mail/content/','1',NULL,NULL,NULL,NULL,NULL);

#
# Source for table "t_password_record"
#

DROP TABLE IF EXISTS `t_password_record`;
CREATE TABLE `t_password_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键：自增',
  `time` datetime DEFAULT NULL COMMENT '记录时间',
  `classify` char(1) DEFAULT NULL COMMENT '来源分类：1：Web邮箱， 2：POP3/SMTP，3. QQ 4. PPPOE',
  `account` varchar(45) DEFAULT NULL COMMENT '登陆账户',
  `password` varchar(64) DEFAULT NULL COMMENT '登陆密码',
  `source_ip` varchar(20) DEFAULT NULL COMMENT '源IP地址',
  `source_mac` varchar(18) DEFAULT NULL COMMENT '源MAC',
  `source_belong` varchar(45) DEFAULT NULL COMMENT '源归属地',
  `dest_ip` varchar(20) DEFAULT NULL COMMENT '目标IP地址',
  `dest_mac` varchar(18) DEFAULT NULL COMMENT '目标MAC',
  `dest_belong` varchar(45) DEFAULT NULL COMMENT '目标IP归属地',
  `is_black` char(1) DEFAULT NULL COMMENT '是否黑名单: 0 否 1 是',
  `is_read` char(1) DEFAULT NULL COMMENT '是否 已读 ： 0 未读 1 已读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='口令信息记录';

#
# Data for table "t_password_record"
#





#
# Source for table "t_search_record"
#

DROP TABLE IF EXISTS `t_search_record`;
CREATE TABLE `t_search_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键：自增',
  `time` datetime DEFAULT NULL COMMENT '记录时间',
  `search_keyword` varchar(255) DEFAULT NULL COMMENT '搜索关键字',
  `classify` char(1) DEFAULT NULL COMMENT '搜索引擎分类：1. 百度， 2.Google，3. Bing， 4.soso，5.360  6.其他',
  `account` varchar(45) DEFAULT NULL COMMENT '登陆账户',
  `password` varchar(64) DEFAULT NULL COMMENT '登陆密码',
  `source_ip` varchar(20) DEFAULT NULL COMMENT '源IP地址',
  `source_mac` varchar(18) DEFAULT NULL COMMENT '源MAC',
  `source_belong` varchar(45) DEFAULT NULL COMMENT '源IP归属地',
  `dest_ip` varchar(20) DEFAULT NULL COMMENT '目标IP地址',
  `dest_mac` varchar(18) DEFAULT NULL COMMENT '目标MAC',
  `dest_belong` varchar(45) DEFAULT NULL COMMENT '目标IP归属地',
  `is_black` char(1) DEFAULT NULL COMMENT '是否黑名单： 0 否 1 是',
  `is_read` char(1) DEFAULT NULL COMMENT '是否已读： 0 否 1 是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='搜索引擎记录';

#
# Data for table "t_search_record"
#


#
# Source for table "t_social_record"
#

DROP TABLE IF EXISTS `t_social_record`;
CREATE TABLE `t_social_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键：自增',
  `time` datetime DEFAULT NULL COMMENT '记录时间',
  `classify` char(1) DEFAULT NULL COMMENT '社交网络分类：1 sina, 2 tencent, 3 sohu, 4 tianya 5 xici 6 mop 7 other',
  `url` varchar(255) DEFAULT NULL COMMENT '访问URL',
  `post` text COMMENT '发帖',
  `reply` text COMMENT '回复',
  `comment` text COMMENT '评论',
  `account` varchar(45) DEFAULT NULL COMMENT '登陆账户',
  `password` varchar(64) DEFAULT NULL COMMENT '登陆密码',
  `source_ip` varchar(20) DEFAULT NULL COMMENT '源IP地址',
  `source_mac` varchar(18) DEFAULT NULL COMMENT '源MAC',
  `source_belong` varchar(45) DEFAULT NULL COMMENT '源 IP归属地',
  `dest_ip` varchar(20) DEFAULT NULL COMMENT '目标IP地址',
  `dest_mac` varchar(18) DEFAULT NULL COMMENT '目标MAC',
  `dest_belong` varchar(45) DEFAULT NULL COMMENT '目标IP归属地',
  `is_black` char(1) DEFAULT NULL COMMENT '是否黑名单： 0 否 1 是',
  `is_read` char(1) DEFAULT NULL COMMENT '是否已读 ： 0 未读 1 已读',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='社交网络记录';

#
# Data for table "t_social_record"
#




#
# Source for table "t_through_record"
#

DROP TABLE IF EXISTS `t_through_record`;
CREATE TABLE `t_through_record` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键：自增',
  `time` datetime DEFAULT NULL COMMENT '记录时间',
  `tool_name` varchar(45) DEFAULT NULL COMMENT '破网工具名称',
  `account` varchar(45) DEFAULT NULL COMMENT '登陆账户',
  `password` varchar(64) DEFAULT NULL COMMENT '登陆密码',
  `source_ip` varchar(20) DEFAULT NULL COMMENT '源IP地址',
  `source_mac` varchar(18) DEFAULT NULL COMMENT '源MAC',
  `source_belong` varchar(45) DEFAULT NULL COMMENT '源IP归属地',
  `dest_ip` varchar(20) DEFAULT NULL COMMENT '目标IP地址',
  `dest_mac` varchar(18) DEFAULT NULL COMMENT '目标MAC',
  `dest_belong` varchar(45) DEFAULT NULL COMMENT '目标IP归属地',
  `is_black` char(1) DEFAULT NULL COMMENT '是否黑名单： 0 否 1 是',
  `is_read` char(1) DEFAULT NULL COMMENT '是否已读： 0 否 1 是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='破网行为记录';

#
# Data for table "t_through_record"
#


/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;



CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `created_time` datetime DEFAULT NULL,
  `created_user` varchar(45) DEFAULT NULL,
  `descn` varchar(200) DEFAULT NULL,
  `name` varchar(45) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `updated_user` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;





CREATE TABLE `t_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `code` varchar(45) NOT NULL,
  `created_time` datetime DEFAULT NULL,
  `created_user` varchar(45) DEFAULT NULL,
  `descn` varchar(200) DEFAULT NULL,
  `name` varchar(45) NOT NULL,
  `parent_code` varchar(45) NOT NULL,
  `status` int(11) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `updated_user` varchar(45) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `code` (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





CREATE TABLE `t_role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` datetime DEFAULT NULL,
  `created_user` varchar(45) DEFAULT NULL,
  `descn` varchar(200) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `updated_user` varchar(45) DEFAULT NULL,
  `resource_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK379F912C8BFD23F9` (`role_id`),
  KEY `FK379F912C69324579` (`resource_id`),
  CONSTRAINT `FK379F912C69324579` FOREIGN KEY (`resource_id`) REFERENCES `t_resource` (`id`),
  CONSTRAINT `FK379F912C8BFD23F9` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `chinese_name` varchar(45) DEFAULT NULL,
  `created_time` datetime DEFAULT NULL,
  `created_user` varchar(45) DEFAULT NULL,
  `descn` varchar(200) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `login_time` datetime DEFAULT NULL,
  `password` varchar(256) NOT NULL,
  `phone` varchar(45) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `updated_user` varchar(45) DEFAULT NULL,
  `username` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;


CREATE TABLE `t_role_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `created_time` datetime DEFAULT NULL,
  `created_user` varchar(45) DEFAULT NULL,
  `descn` varchar(200) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `updated_time` datetime DEFAULT NULL,
  `updated_user` varchar(45) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK32E9F7E98BFD23F9` (`role_id`),
  KEY `FK32E9F7E93127E7D9` (`user_id`),
  CONSTRAINT `FK32E9F7E93127E7D9` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK32E9F7E98BFD23F9` FOREIGN KEY (`role_id`) REFERENCES `t_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE `t_log_operation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `event_action` varchar(200) DEFAULT NULL,
  `event_method` varchar(45) DEFAULT NULL,
  `log_content` varchar(200) DEFAULT NULL,
  `log_ip` varchar(100) DEFAULT NULL,
  `log_level` varchar(45) DEFAULT NULL,
  `log_time` datetime DEFAULT NULL,
  `log_user` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;








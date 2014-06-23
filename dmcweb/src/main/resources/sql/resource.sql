/*
-- Query: SELECT * FROM dmc20131228.t_resource
LIMIT 0, 1000

-- Date: 2014-01-19 16:20
*/
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (1,'index',NULL,NULL,NULL,'数据管理系统','index',1,NULL,NULL,'/index.jsp');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (2,'surfing',NULL,NULL,NULL,'上网行为','index',1,NULL,NULL,'#');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (3,'http',NULL,NULL,NULL,'网页浏览','surfing',1,NULL,NULL,'/http.html');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (4,'search',NULL,NULL,NULL,'搜索引擎','surfing',1,NULL,NULL,'/search.html');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (5,'through',NULL,NULL,NULL,'破网行为','surfing',1,NULL,NULL,'/through.html');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (6,'commerce',NULL,NULL,NULL,'电子商务','surfing',1,NULL,NULL,'/commerce.html');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (7,'social',NULL,NULL,NULL,'社交网络','surfing',1,NULL,NULL,'/social.html');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (8,'password',NULL,NULL,NULL,'口令信息','surfing',1,NULL,NULL,'/password.html');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (10,'file',NULL,NULL,NULL,'文件传送','index',1,NULL,NULL,'#');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (11,'ftp',NULL,NULL,NULL,'FTP','file',1,NULL,NULL,'/ftp.html');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (12,'netdisk',NULL,NULL,NULL,'网盘','file',1,NULL,NULL,'/netdisk.html');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (13,'imfile',NULL,NULL,NULL,'即时通讯','file',1,NULL,NULL,'/imfile.html');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (14,'suspicious',NULL,NULL,NULL,'可疑行为','index',1,NULL,NULL,'#');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (15,'control',NULL,NULL,NULL,'远程控制','suspicious',1,NULL,NULL,'/control.html');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (16,'im',NULL,NULL,NULL,'网络聊天','index',1,NULL,NULL,'#');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (17,'msn',NULL,NULL,NULL,'MSN','im',1,NULL,NULL,'/msn.html');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (18,'qq',NULL,NULL,NULL,'QQ','im',1,NULL,NULL,'/qq.html');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (19,'fetion',NULL,NULL,NULL,'飞信','im',1,NULL,NULL,'/fetion.html');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (20,'wechat',NULL,NULL,NULL,'微信','im',1,NULL,NULL,'/wechat.html');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (21,'skype',NULL,NULL,NULL,'Skype','im',1,NULL,NULL,'/skype.html');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (22,'mail',NULL,NULL,NULL,'电子邮件','index',1,NULL,NULL,'#');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (23,'pop3',NULL,NULL,NULL,'POP3','mail',1,NULL,NULL,'/pop3.html');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (24,'smtp',NULL,NULL,NULL,'SMTP','mail',1,NULL,NULL,'/smtp.html');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (25,'webreceiving',NULL,NULL,NULL,'WEB收','mail',1,NULL,NULL,'/webreceiving.html');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (26,'websending',NULL,NULL,NULL,'WEB发','mail',1,NULL,NULL,'/websending.html');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (27,'draftsaving',NULL,NULL,NULL,'存草稿','mail',1,NULL,NULL,'/draftsaving.html');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (28,'draftreceiving',NULL,NULL,NULL,'收草稿','mail',1,NULL,NULL,'/draftreceiving.html');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (29,'penetration',NULL,NULL,NULL,'邮件渗透','mail',1,NULL,NULL,'/penetration.html');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (30,'system',NULL,NULL,NULL,'系统设置','index',1,NULL,NULL,'#');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (31,'user',NULL,NULL,NULL,'用户信息管理','system',1,NULL,NULL,'/user.html');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (32,'role',NULL,NULL,NULL,'角色信息管理','system',1,NULL,NULL,'/role.html');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (33,'right',NULL,NULL,NULL,'角色授权维护','system',1,NULL,NULL,'/rolerightaction_query');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (34,'blacklist',NULL,NULL,NULL,'黑名单管理','system',1,NULL,NULL,'/blacklist.html');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (35,'parameter',NULL,NULL,NULL,'参数配置','system',1,NULL,NULL,'/parameter.html');
INSERT INTO `t_resource` (`id`,`code`,`created_time`,`created_user`,`descn`,`name`,`parent_code`,`status`,`updated_time`,`updated_user`,`url`) VALUES (36,'log',NULL,NULL,NULL,'用户操作日志','system',1,NULL,NULL,'/log.html');

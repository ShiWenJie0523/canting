/*
MySQL Data Transfer
Source Host: localhost
Source Database: canting_db
Target Host: localhost
Target Database: canting_db
Date: 2015/6/24 22:54:59
*/
create database canting_db;
SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_bill
-- ----------------------------
DROP TABLE IF EXISTS `t_bill`;
CREATE TABLE `t_bill` (
  `id` int(11) NOT NULL auto_increment,
  `createTime` datetime default NULL,
  `effect` int(11) NOT NULL,
  `finish` int(11) NOT NULL,
  `guazhang` int(11) NOT NULL,
  `mobile` varchar(255) default NULL,
  `real_price` double NOT NULL,
  `searilNum` varchar(255) default NULL,
  `status` int(11) NOT NULL,
  `tableNum` varchar(255) default NULL,
  `total_price` double NOT NULL,
  `yudingfei` double NOT NULL,
  `zhaoli_price` double NOT NULL,
  `zhekou_price` double NOT NULL,
  `zhifu` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_billgoods
-- ----------------------------
DROP TABLE IF EXISTS `t_billgoods`;
CREATE TABLE `t_billgoods` (
  `id` int(11) NOT NULL auto_increment,
  `billNum` int(11) NOT NULL,
  `name` varchar(255) default NULL,
  `price` double NOT NULL,
  `shang` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `billid` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK16B0964121C7EDA` (`billid`),
  CONSTRAINT `FK16B0964121C7EDA` FOREIGN KEY (`billid`) REFERENCES `t_bill` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_custom
-- ----------------------------
DROP TABLE IF EXISTS `t_custom`;
CREATE TABLE `t_custom` (
  `id` int(11) NOT NULL auto_increment,
  `address` varchar(255) default NULL,
  `age` int(11) NOT NULL,
  `clevel` int(11) NOT NULL,
  `createTime` datetime default NULL,
  `customNum` varchar(255) default NULL,
  `deleteStatus` int(11) default NULL,
  `freshTime` datetime default NULL,
  `jifen` int(11) NOT NULL,
  `mobile` varchar(255) default NULL,
  `name` varchar(255) default NULL,
  `sex` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_diningtable
-- ----------------------------
DROP TABLE IF EXISTS `t_diningtable`;
CREATE TABLE `t_diningtable` (
  `id` int(11) NOT NULL auto_increment,
  `bookTime` datetime default NULL,
  `mobile` varchar(255) default NULL,
  `name` varchar(255) default NULL,
  `seatCount` int(11) NOT NULL,
  `status` int(11) NOT NULL,
  `tableNum` varchar(255) default NULL,
  `yudingfei` double NOT NULL,
  `zhifu` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods` (
  `id` int(11) NOT NULL auto_increment,
  `goodsNum` varchar(255) default NULL,
  `ground` int(11) NOT NULL,
  `kouwei` varchar(255) default NULL,
  `name` varchar(255) default NULL,
  `price` double NOT NULL,
  `season` varchar(255) default NULL,
  `tuijian` int(11) NOT NULL,
  `goodsCateid` int(11) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK9E8BE3AB76EB0A74` (`goodsCateid`),
  CONSTRAINT `FK9E8BE3AB76EB0A74` FOREIGN KEY (`goodsCateid`) REFERENCES `t_goodscate` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_goodscate
-- ----------------------------
DROP TABLE IF EXISTS `t_goodscate`;
CREATE TABLE `t_goodscate` (
  `id` int(11) NOT NULL auto_increment,
  `cateName` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL auto_increment,
  `realName` varchar(255) default NULL,
  `role` varchar(255) default NULL,
  `status` varchar(255) default NULL,
  `username` varchar(255) default NULL,
  `userpwd` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `t_bill` VALUES ('1', '2015-05-08 23:08:23', '1', '1', '0', '13912341234', '75', '20150508230823', '1', '002', '75', '0', '0', '75', null);
INSERT INTO `t_billgoods` VALUES ('1', '5', '凉粉', '50', '1', '0', '1');
INSERT INTO `t_billgoods` VALUES ('2', '1', '凉拌黄瓜', '5', '1', '0', '1');
INSERT INTO `t_billgoods` VALUES ('3', '1', '红烧肉', '20', '1', '0', '1');
INSERT INTO `t_custom` VALUES ('1', '河南', '30', '0', '2015-05-08 23:01:21', '0508230121', '0', '2015-05-08 23:08:23', '75', '13912341234', 'aaa', '1');
INSERT INTO `t_diningtable` VALUES ('1', null, null, null, '4', '0', '001', '0', null);
INSERT INTO `t_diningtable` VALUES ('2', null, '', '', '3', '0', '002', '0', '');
INSERT INTO `t_goods` VALUES ('1', '001', '1', '好', '凉拌黄瓜', '5', '春,夏,秋,冬,', '1', '2');
INSERT INTO `t_goods` VALUES ('2', '002', '1', '好', '凉粉', '10', '春,夏,秋,冬,', '1', '2');
INSERT INTO `t_goods` VALUES ('3', '003', '1', '好', '红烧肉', '20', '春,夏,秋,冬,', '1', '1');
INSERT INTO `t_goodscate` VALUES ('1', '热菜');
INSERT INTO `t_goodscate` VALUES ('2', '凉菜');
INSERT INTO `t_user` VALUES ('1', null, 'ROLE_ROOT', 'true', 'admin', '111111');
INSERT INTO `t_user` VALUES ('2', 'aaa', 'ROLE_CASH', 'true', 'user1', '111111');
INSERT INTO `t_user` VALUES ('3', 'bbb', 'ROLE_CASH', 'true', 'user2', '111111');

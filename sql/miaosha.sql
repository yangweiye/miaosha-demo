/*
 Navicat Premium Data Transfer

 Source Server         : tengxun
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 152.136.145.182:3306
 Source Schema         : miaosha

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 29/04/2021 18:55:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ms_goods
-- ----------------------------
DROP TABLE IF EXISTS `ms_goods`;
CREATE TABLE `ms_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(60) NOT NULL,
  `goods_title` varchar(60) NOT NULL,
  `goods_img` varchar(255) DEFAULT NULL,
  `goods_detail` text,
  `goods_price` int(11) NOT NULL,
  `goods_stock` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ms_goods
-- ----------------------------
BEGIN;
INSERT INTO `ms_goods` VALUES (1, 'iphone 12 pro max 512G', 'iphone 12', 'http://localhost/img/iphonex.png', 'iPhone 12是美国苹果公司于美国西部时间2020年10月13日上午10点（北京时间2020年10月14日凌晨1点）在Apple Park总部园区发布的手机产品。\niPhone 12采用了直面边框设计，有黑色、白色、红色、绿色、蓝色、紫色六种配色。\niPhone 12支持5G，搭载A14 Bionic芯片，双镜头后置摄像头系统。支持北斗导航。', 1899900, 176);
INSERT INTO `ms_goods` VALUES (2, 'HUAWEI MATE 40 512G', 'HUAWEI MATE 40', 'http://localhost/img/meta10.png', '华为Mate40是华为公司于2020年10月22日发布的手机，于2020年12月21日上市。\n华为Mate40是华为研发的智能手机，搭载麒麟9000E芯片，采用6.5英寸的68度曲面屏，分辨率为2376*1080，有亮黑色、秘银色、釉白色三种机身颜色。', 999900, 191);
COMMIT;

-- ----------------------------
-- Table structure for ms_miaosha_goods
-- ----------------------------
DROP TABLE IF EXISTS `ms_miaosha_goods`;
CREATE TABLE `ms_miaosha_goods` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `goods_id` bigint(20) NOT NULL,
  `miaosha_price` int(11) NOT NULL,
  `miaosha_stcok` int(11) NOT NULL,
  `start_time` timestamp NOT NULL,
  `end_time` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ms_miaosha_goods
-- ----------------------------
BEGIN;
INSERT INTO `ms_miaosha_goods` VALUES (1, 1, 100, 3, '2021-04-24 18:33:14', '2021-04-29 23:07:09');
INSERT INTO `ms_miaosha_goods` VALUES (2, 2, 100, 5, '2021-04-24 18:33:14', '2021-04-29 23:07:09');
COMMIT;

-- ----------------------------
-- Table structure for ms_miaosha_order
-- ----------------------------
DROP TABLE IF EXISTS `ms_miaosha_order`;
CREATE TABLE `ms_miaosha_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `goods_id` bigint(20) NOT NULL,
  `order_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ms_miaosha_order
-- ----------------------------
BEGIN;
INSERT INTO `ms_miaosha_order` VALUES (1, 1, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for ms_order_info
-- ----------------------------
DROP TABLE IF EXISTS `ms_order_info`;
CREATE TABLE `ms_order_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `goods_id` bigint(20) NOT NULL,
  `delivery_addr_id` bigint(20) NOT NULL,
  `goods_name` varchar(100) NOT NULL,
  `goods_count` int(11) NOT NULL,
  `goods_price` int(11) NOT NULL,
  `order_channel` tinyint(4) NOT NULL,
  `status` tinyint(4) NOT NULL,
  `create_time` timestamp NOT NULL,
  `pay_time` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ms_order_info
-- ----------------------------
BEGIN;
INSERT INTO `ms_order_info` VALUES (1, 1, 1, 0, 'iphone 12 pro max 512G', 1, 100, 1, 0, '2021-04-28 01:50:40', NULL);
COMMIT;

-- ----------------------------
-- Table structure for ms_role
-- ----------------------------
DROP TABLE IF EXISTS `ms_role`;
CREATE TABLE `ms_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ms_role
-- ----------------------------
BEGIN;
INSERT INTO `ms_role` VALUES (1, '经理');
INSERT INTO `ms_role` VALUES (2, 'CEO');
COMMIT;

-- ----------------------------
-- Table structure for ms_user
-- ----------------------------
DROP TABLE IF EXISTS `ms_user`;
CREATE TABLE `ms_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `head` varchar(255) DEFAULT NULL,
  `register_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `last_login_time` timestamp NULL DEFAULT NULL,
  `login_count` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ms_user
-- ----------------------------
BEGIN;
INSERT INTO `ms_user` VALUES (1, 'yangweiye', '$2a$10$S8lZvDHGornbtwXWprfx3OHpyavk.nEwEJzoz/fs8MUorTlOi0yf.', 'none', '2021-04-22 21:32:01', '2021-04-22 21:32:01', 0);
COMMIT;

-- ----------------------------
-- Table structure for ms_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `ms_user_role_relation`;
CREATE TABLE `ms_user_role_relation` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `role_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of ms_user_role_relation
-- ----------------------------
BEGIN;
INSERT INTO `ms_user_role_relation` VALUES (1, 1, 1);
INSERT INTO `ms_user_role_relation` VALUES (2, 1, 2);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

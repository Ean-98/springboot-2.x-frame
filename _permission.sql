/*
 Navicat Premium Data Transfer

 Source Server         : hlsd-rasberry
 Source Server Type    : MySQL
 Source Server Version : 50648
 Source Host           : 101.132.235.194:3307
 Source Schema         : mypay211

 Target Server Type    : MySQL
 Target Server Version : 50648
 File Encoding         : 65001

 Date: 04/08/2020 15:34:48
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for _permission
-- ----------------------------
DROP TABLE IF EXISTS `_permission`;
CREATE TABLE `_permission`  (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for _role
-- ----------------------------
DROP TABLE IF EXISTS `_role`;
CREATE TABLE `_role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for _role_permission
-- ----------------------------
DROP TABLE IF EXISTS `_role_permission`;
CREATE TABLE `_role_permission`  (
  `role_permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NULL DEFAULT NULL,
  `permission_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`role_permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for _user
-- ----------------------------
DROP TABLE IF EXISTS `_user`;
CREATE TABLE `_user`  (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_pass` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `user_gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `user_tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `user_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `uuid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `user_logo` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `u_acco` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '用户名',
  `u_pass` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '密码',
  `user_upper_a` int(11) NOT NULL DEFAULT 0 COMMENT '上级a，这里指的是代理商。-1代表没绑定，否则表示是代理商的用户id',
  `user_balance` int(11) NOT NULL DEFAULT 0 COMMENT '余额,如果提现了需要减去，这个字段普通用户跟代理商都会有，分销的时候是加到这个字段，',
  `user_total_balance` int(11) NOT NULL DEFAULT 0 COMMENT ' 总余额，不管有没有提现，都不会减少。-代理商收入跟分销收入都会加到这里',
  `user_upper_b` int(11) NOT NULL DEFAULT 0 COMMENT '上级b，这里指的是代理商。-1代表没绑定，否则表示是代理商的用户id',
  `createtime` bigint(13) NULL DEFAULT NULL,
  `user_sale_amount` int(11) NOT NULL DEFAULT 0 COMMENT ' 销售额度，代理商才会有，普通用户可以不用考虑该字段，代理商收入会添加到这个字段',
  `user_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT ' 二维码',
  `user_order_confirm_info` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT ' 用户下单默认的信息，直接保存json格式在数据库就好，前端自己读取或者修改',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 512 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for _user_role
-- ----------------------------
DROP TABLE IF EXISTS `_user_role`;
CREATE TABLE `_user_role`  (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL,
  `user_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `user_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `role_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL,
  `status` int(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`user_role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;

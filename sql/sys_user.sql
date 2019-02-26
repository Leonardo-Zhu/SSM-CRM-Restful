/*
Navicat MySQL Data Transfer

Source Server         : localhost:3306
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : crm

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2019-02-20 15:09:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `user_id` int(32) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `user_name` varchar(64) NOT NULL COMMENT '用户名称',
  `user_password` varchar(32) NOT NULL COMMENT '用户密码',
  `user_email` varchar(32) NOT NULL COMMENT '用户邮箱',
  `user_state` char(1) NOT NULL COMMENT '1:正常,0:暂停',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('5', '小军', '123', 'm0003', '1');
INSERT INTO `sys_user` VALUES ('6', '小红', '123', 'm0001', '1');
INSERT INTO `sys_user` VALUES ('7', '小明', '123', 'm0001', '1');
INSERT INTO `sys_user` VALUES ('8', '小红', '123', 'm0001', '1');
INSERT INTO `sys_user` VALUES ('9', '张三', '12345', '12356@qq.com', '1');
INSERT INTO `sys_user` VALUES ('10', '李四', '123', '273@qq.com', '1');

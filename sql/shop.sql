/*
Navicat MySQL Data Transfer

Source Server         : localhost:3306
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : crm

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2019-02-20 15:08:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `shop_order_item`
-- ----------------------------
DROP TABLE IF EXISTS `shop_order_item`;
CREATE TABLE `shop_order_item` (
  `item_id` int(32) NOT NULL,
  `item_num` int(11) NOT NULL,
  `subtotal` double NOT NULL,
  `order_id` varchar(32) NOT NULL,
  KEY `fk_0001` (`item_id`),
  KEY `fk_0002` (`order_id`),
  CONSTRAINT `shop_order_item_ibfk_1` FOREIGN KEY (`item_id`) REFERENCES `shop_item` (`item_id`),
  CONSTRAINT `shop_order_item_ibfk_2` FOREIGN KEY (`order_id`) REFERENCES `shop_order` (`order_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_order_item
-- ----------------------------
INSERT INTO `shop_order_item` VALUES ('2', '1', '2299', '18932383333');
INSERT INTO `shop_order_item` VALUES ('4', '1', '2789', '18932383333');
INSERT INTO `shop_order_item` VALUES ('9', '2', '2398', '18932383333');
INSERT INTO `shop_order_item` VALUES ('9', '1', '1199', '38966746161');
INSERT INTO `shop_order_item` VALUES ('7', '2', '6598', '38966746161');

-- ----------------------------
-- Table structure for `shop_order`
-- ----------------------------
DROP TABLE IF EXISTS `shop_order`;
CREATE TABLE `shop_order` (
  `order_id` varchar(32) NOT NULL,
  `create_time` varchar(32) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `state` int(11) DEFAULT NULL COMMENT '0：未支付；1：已支付',
  `address` varchar(30) DEFAULT NULL,
  `consignee_name` varchar(20) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `user_id` int(32) NOT NULL,
  PRIMARY KEY (`order_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `shop_order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_order
-- ----------------------------
INSERT INTO `shop_order` VALUES ('18932383333', '2019-02-11 09:48:51', '7486', '0', '安徽省六安市舒城县凤池苑牡丹苑10栋301', '朱晓龙', '15956404411', '9');
INSERT INTO `shop_order` VALUES ('38966746161', '2019-02-12 01:18:36', '7797', '1', '四川省成都市双流县成都信息工程大学', '张三', '18148136037', '9');

-- ----------------------------
-- Table structure for `shop_item`
-- ----------------------------
DROP TABLE IF EXISTS `shop_item`;
CREATE TABLE `shop_item` (
  `item_id` int(32) NOT NULL AUTO_INCREMENT,
  `item_name` varchar(100) NOT NULL,
  `market_price` double NOT NULL,
  PRIMARY KEY (`item_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_item
-- ----------------------------
INSERT INTO `shop_item` VALUES ('1', 'Apple iPhone X (A1865) 64GB 深空灰色 移动联通电信4G手机', '6299');
INSERT INTO `shop_item` VALUES ('2', '小米8 全面屏游戏智能手机 6GB+64GB 黑色 全网通4G 双卡双待', '2299');
INSERT INTO `shop_item` VALUES ('3', ' HUAWEI nova 4 极点全面屏手机 2000万超广角三摄 8GB+128GB 苏音蓝 全网通双卡双待', '3099');
INSERT INTO `shop_item` VALUES ('4', 'vivo X23全息幻彩版 6GB+128GB 北极晨曦 水滴屏全面屏 游戏手机 移动联通电信全网通4G手机', '2789');
INSERT INTO `shop_item` VALUES ('5', '一加手机6T 8GB+128GB 墨岩黑 光感屏幕指纹 高通骁龙845 全面屏双摄游戏手机 全网通4G 双卡双待', '3599');
INSERT INTO `shop_item` VALUES ('6', '荣耀10 GT游戏加速 AIS手持夜景 6GB+64GB 幻影蓝全网通 移动联通电信4G 双卡双待 游戏手机', '2199');
INSERT INTO `shop_item` VALUES ('7', '小米Mix3 6GB+128GB黑色 骁龙845 全网通4G 双卡双待 全面屏拍照游戏智能手机', '3299');
INSERT INTO `shop_item` VALUES ('8', '华为 HUAWEI P20 Pro 全面屏徕卡三摄游戏手机 6GB+128GB 极光色 全网通移动联通电信4G手机 双卡双待', '4888');
INSERT INTO `shop_item` VALUES ('9', ' 小米 红米Redmi Note7 幻彩渐变AI双摄 4GB+64GB 梦幻蓝 全网通4G 双卡双待 水滴全面屏拍照游戏智能手机', '1199');

-- ----------------------------
-- Table structure for `shop_cart`
-- ----------------------------
DROP TABLE IF EXISTS `shop_cart`;
CREATE TABLE `shop_cart` (
  `user_id` int(32) NOT NULL,
  `item_id` int(32) NOT NULL,
  `item_num` int(32) NOT NULL,
  KEY `user_id` (`user_id`),
  KEY `item_id` (`item_id`),
  CONSTRAINT `shop_cart_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`user_id`),
  CONSTRAINT `shop_cart_ibfk_2` FOREIGN KEY (`item_id`) REFERENCES `shop_item` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shop_cart
-- ----------------------------
INSERT INTO `shop_cart` VALUES ('9', '1', '1');

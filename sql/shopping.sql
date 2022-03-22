/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50717
 Source Host           : localhost:3306
 Source Schema         : shopping

 Target Server Type    : MySQL
 Target Server Version : 50717
 File Encoding         : 65001

 Date: 22/03/2022 11:06:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `information` varchar(300) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createtime` datetime(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES (1, 'javatest', '13546548654', '3038652743@qq.com', 'bobogewudi', '123456', '2022-03-15 20:29:52');
INSERT INTO `admin` VALUES (2, 'admin', '13546548654', '3038652743@qq.com', '波波哥是无敌的', '123456', '2022-03-16 11:00:27');
INSERT INTO `admin` VALUES (3, 'admin123123', '13546548654', '3038652743@qq.com', '波波哥是无敌的', '123456789', '2022-03-16 11:48:52');
INSERT INTO `admin` VALUES (4, 'admin', '13546548654', '3038652743@qq.com', '波波哥是无敌的', '123456', '2022-03-16 11:00:28');
INSERT INTO `admin` VALUES (5, 'admin', '13546548654', '3038652743@qq.com', '波波哥是无敌的', '123456', '2022-03-16 11:00:28');
INSERT INTO `admin` VALUES (6, 'admin', '13546548654', '3038652743@qq.com', '波波哥是无敌的', '123456', '2022-03-16 11:00:29');
INSERT INTO `admin` VALUES (9, 'admin', '13546548654', '3038652743@qq.com', '波波哥是无敌的', '123456', '2022-03-16 11:00:29');
INSERT INTO `admin` VALUES (13, 'admin', '13546548654', '3038652743@qq.com', '波波哥是无敌的', '123456', '2022-03-16 11:00:30');
INSERT INTO `admin` VALUES (14, 'admin', '13546548654', '3038652743@qq.com', '波波哥是无敌的', '123456', '2022-03-16 11:00:30');
INSERT INTO `admin` VALUES (17, 'admin', '13546548654', '3038652743@qq.com', '波波哥是无敌的', '123456', '2022-03-16 11:00:31');
INSERT INTO `admin` VALUES (18, 'admin', '13546548654', '3038652743@qq.com', '波波哥是无敌的', '123456', '2022-03-16 11:00:31');
INSERT INTO `admin` VALUES (22, 'admin', '13546548654', '3038652743@qq.com', '波波哥是无敌的', '123456', '2022-03-16 11:00:32');
INSERT INTO `admin` VALUES (23, 'admin', '13546548654', '3038652743@qq.com', '波波哥是无敌的', '123456', '2022-03-16 11:00:32');
INSERT INTO `admin` VALUES (24, 'admin', '13546548654', '3038652743@qq.com', '波波哥是无敌的', '123456', '2022-03-16 11:00:32');
INSERT INTO `admin` VALUES (28, 'admin', '13546548654', '3038652743@qq.com', '波波哥是无敌的', '123456', '2022-03-16 11:00:33');
INSERT INTO `admin` VALUES (29, 'admin', '13546548654', '3038652743@qq.com', '波波哥是无敌的', '123456', '2022-03-16 11:00:33');
INSERT INTO `admin` VALUES (30, 'admin', '13546548654', '3038652743@qq.com', '波波哥是无敌的', '123456', '2022-03-16 11:00:33');
INSERT INTO `admin` VALUES (31, 'admin', '13546548654', '3038652743@qq.com', '波波哥是无敌的', '123456', '2022-03-16 11:00:34');
INSERT INTO `admin` VALUES (32, 'admin', '13546548654', '3038652743@qq.com', '波波哥是无敌的', '123456', '2022-03-16 11:00:34');
INSERT INTO `admin` VALUES (33, 'javaweb', '13546548650', '123@qq.com', '123456', '123456789', '2022-03-16 11:08:02');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `isleaf` decimal(9, 0) NOT NULL,
  `grade` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createtime` datetime(0) NOT NULL,
  `descr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES (1, -1, '所有类别', 1, '1', '2022-03-04 16:03:29', '');
INSERT INTO `category` VALUES (12, 1, '伯伯个', 1, '2', '2022-03-18 16:46:58', '1');
INSERT INTO `category` VALUES (13, 12, '徐哥', 0, '3', '2022-03-18 20:06:53', '2');
INSERT INTO `category` VALUES (14, 12, '鸡哥', 0, '3', '2022-03-18 20:06:57', '123');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `id` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `totalprice` decimal(11, 2) NOT NULL,
  `totalcount` int(50) NOT NULL,
  `createtime` datetime(0) NOT NULL,
  `userid` int(50) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('1641448826965', 3998.00, 2, '2022-01-06 16:12:46', 1);
INSERT INTO `order` VALUES ('1641465196239', 579572.00, 28, '2022-01-06 18:33:16', 1);
INSERT INTO `order` VALUES ('1641467997157', 20699.00, 1, '2022-01-06 19:19:57', 1);
INSERT INTO `order` VALUES ('1641468522665', 35982.00, 18, '2022-01-06 19:28:43', 1);
INSERT INTO `order` VALUES ('1641468529706', 35982.00, 18, '2022-01-06 19:28:50', 1);
INSERT INTO `order` VALUES ('1641468693252', 35982.00, 18, '2022-01-06 19:31:33', 1);
INSERT INTO `order` VALUES ('1641468881795', 41398.00, 2, '2022-01-06 19:34:42', 1);
INSERT INTO `order` VALUES ('1641469084860', 41979.00, 21, '2022-01-06 19:38:05', 1);
INSERT INTO `order` VALUES ('1641469218014', 124194.00, 6, '2022-01-06 19:40:18', 1);
INSERT INTO `order` VALUES ('1641469293266', 22107.00, 3, '2022-01-06 19:41:33', 1);
INSERT INTO `order` VALUES ('1641469482944', 90792.00, 8, '2022-01-06 19:44:43', 1);
INSERT INTO `order` VALUES ('1641470152512', 90792.00, 8, '2022-01-06 19:55:53', 1);

-- ----------------------------
-- Table structure for orderitem
-- ----------------------------
DROP TABLE IF EXISTS `orderitem`;
CREATE TABLE `orderitem`  (
  `id` int(30) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` decimal(11, 2) NOT NULL,
  `url` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `count` int(50) NOT NULL,
  `totalprice` decimal(11, 2) NOT NULL,
  `orderid` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderitem
-- ----------------------------
INSERT INTO `orderitem` VALUES (17, '贵州茅台酒', 20699.00, 'static/images/product/maotai.jpg', 28, 579572.00, '1641465196239');
INSERT INTO `orderitem` VALUES (18, '贵州茅台酒', 20699.00, 'static/images/product/maotai.jpg', 1, 20699.00, '1641467997157');
INSERT INTO `orderitem` VALUES (19, '波波哥手机', 1999.00, 'static/images/product/boboge.jpg', 18, 35982.00, '1641468522665');
INSERT INTO `orderitem` VALUES (20, '波波哥手机', 1999.00, 'static/images/product/boboge.jpg', 18, 35982.00, '1641468529706');
INSERT INTO `orderitem` VALUES (21, '波波哥手机', 1999.00, 'static/images/product/boboge.jpg', 18, 35982.00, '1641468693252');
INSERT INTO `orderitem` VALUES (22, '贵州茅台酒', 20699.00, 'static/images/product/maotai.jpg', 2, 41398.00, '1641468881795');
INSERT INTO `orderitem` VALUES (23, '波波哥手机', 1999.00, 'static/images/product/boboge.jpg', 21, 41979.00, '1641469084860');
INSERT INTO `orderitem` VALUES (24, '贵州茅台酒', 20699.00, 'static/images/product/maotai.jpg', 6, 124194.00, '1641469218014');
INSERT INTO `orderitem` VALUES (25, '神舟(HASEE)战神G8-TA7NP ', 7369.00, 'static/images/product/zhanshen.jpg', 3, 22107.00, '1641469293266');
INSERT INTO `orderitem` VALUES (26, '波波哥手机', 1999.00, 'static/images/product/boboge.jpg', 4, 7996.00, '1641469482944');
INSERT INTO `orderitem` VALUES (27, '贵州茅台酒', 20699.00, 'static/images/product/maotai.jpg', 4, 82796.00, '1641469482944');
INSERT INTO `orderitem` VALUES (28, '波波哥手机', 1999.00, 'static/images/product/boboge.jpg', 4, 7996.00, '1641470152512');
INSERT INTO `orderitem` VALUES (29, '贵州茅台酒', 20699.00, 'static/images/product/maotai.jpg', 4, 82796.00, '1641470152512');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `url` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `price` decimal(9, 0) NOT NULL,
  `descr` varchar(1000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `createtime` datetime(0) NOT NULL,
  `categoryid` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `fk`(`categoryid`) USING BTREE,
  CONSTRAINT `fk` FOREIGN KEY (`categoryid`) REFERENCES `category` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '波波哥手机', 'static/images/product/boboge.jpg', 1999, '波波哥宇宙无敌超级大帅哥手机！ 你值得拥有！', '2022-01-03 10:35:29', 13);
INSERT INTO `product` VALUES (2, '贵州茅台酒', 'static/images/product/maotai.jpg', 20699, '53度 500ml*6瓶 整箱装 酱香型白酒（新老包装随机发货） 你值得拥有！', '2022-01-03 10:35:32', 13);
INSERT INTO `product` VALUES (3, '八享时焦糖味瓜子500g', 'static/images/product/guazi.jpg', 1, ' 葵花籽 坚果炒货 休闲坚果零食 京东自有品牌八享时', '2022-01-03 10:35:35', 14);
INSERT INTO `product` VALUES (4, '神舟(HASEE)战神G8-TA7NP ', 'static/images/product/zhanshen.jpg', 7369, '17.3英寸游戏笔记本电脑(11代酷睿i7-11800H RTX3060 6G 16G 512GSSD 144Hz高色域)', '2022-01-03 10:35:37', 14);
INSERT INTO `product` VALUES (5, '雷鸟FF1', 'static/images/product/leiniao.jpg', 1999, '6.67英寸120Hz高刷无界屏 66W疾速闪充6400万像素超清影像8 128GB冰川湖翠全网通5G手机【智选手机】', '2022-01-03 10:35:42', 13);
INSERT INTO `product` VALUES (6, '三星 SAMSUNG Galaxy S21 5G（SM-G9910）', 'static/images/product/sanxings21.jpg', 3599, '5G手机 骁龙888 超高清摄像 120Hz护目屏 游戏手机 8G 128G 梵梦紫', '2022-01-03 10:35:45', 13);
INSERT INTO `product` VALUES (7, '华为nova8se', 'static/images/product/huawei8.jpg', 2069, '新品手机 搭载HarmonyOS系统 幻夜黑 8GB 128GB（66W充电套装）', '2022-01-03 10:35:49', 13);
INSERT INTO `product` VALUES (8, 'vivo iQOO Neo5', 'static/images/product/iqoo5.jpg', 2599, '高通骁龙870独立显示芯片 66W闪充 双模5G 电竞游戏智能手机 12GB 256GB夜影黑 官方标配', '2022-01-03 10:35:51', 13);
INSERT INTO `product` VALUES (12, '华为 HUAWEI Mate 40 Pro', 'static/images/product/huaweimeta40.jpg', 6599, '4G 全网通 麒麟9000旗舰芯片 8GB 256GB亮黑色华为手机【搭载HarmonyOS 2】', '2022-01-03 13:21:27', 13);
INSERT INTO `product` VALUES (15, ' 斯伯丁篮球', 'static/images/product/eeb99a5d4cc3ad1a[1].jpg', 155, '7号比赛PU室内外耐磨成人儿童篮球74-604Y/77-198Y', '2022-01-04 08:49:33', 13);
INSERT INTO `product` VALUES (37, '撒旦飞洒地方', 'static/images/product/wallhaven-rdekew_1920x1080.png', 234234, '23sdfas', '2022-03-19 08:52:57', 13);
INSERT INTO `product` VALUES (38, '阿斯顿发生', 'static/images/product/wallhaven-rdekew_1920x1080.png', 132, 'sadfsdf', '2022-03-21 10:32:03', 14);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `phone` varchar(22) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `createtime` datetime(0) NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 187 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', '13546548650', '2021-12-25 12:51:09', 'xiaobiao0518@gemail.com');
INSERT INTO `user` VALUES (2, 'boboge', 'boboge', '13546548650', '2021-12-28 15:43:40', 'xiaobiao0518@gemail.com');
INSERT INTO `user` VALUES (157, 'javatest123', '123456789', '13546548650', '2022-01-03 14:41:57', 'xiaobiao0518@gemail.com');
INSERT INTO `user` VALUES (158, 'leijinbiao', 'leijinbiao', '13546548658', '2022-03-04 11:20:25', '123@qq.com');
INSERT INTO `user` VALUES (159, 'java', '123456', '13546548658', '2022-03-15 09:29:35', '123@qq.com');
INSERT INTO `user` VALUES (160, 'java', '123456', '13546548658', '2022-03-15 09:29:36', '123@qq.com');
INSERT INTO `user` VALUES (161, 'java', '123456', '13546548658', '2022-03-15 09:29:36', '123@qq.com');
INSERT INTO `user` VALUES (162, 'java', '123456', '13546548658', '2022-03-15 09:29:36', '123@qq.com');
INSERT INTO `user` VALUES (163, 'java', '123456', '13546548658', '2022-03-15 09:29:37', '123@qq.com');
INSERT INTO `user` VALUES (164, 'java', '123456', '13546548658', '2022-03-15 09:29:37', '123@qq.com');
INSERT INTO `user` VALUES (165, 'java', '123456', '13546548658', '2022-03-15 09:29:37', '123@qq.com');
INSERT INTO `user` VALUES (166, 'java', '123456', '13546548658', '2022-03-15 09:29:37', '123@qq.com');
INSERT INTO `user` VALUES (167, 'java', '123456', '13546548658', '2022-03-15 09:29:38', '123@qq.com');
INSERT INTO `user` VALUES (168, 'java', '123456', '13546548658', '2022-03-15 09:29:38', '123@qq.com');
INSERT INTO `user` VALUES (169, 'java', '123456', '13546548658', '2022-03-15 09:29:38', '123@qq.com');
INSERT INTO `user` VALUES (170, 'java', '123456', '13546548658', '2022-03-15 09:29:38', '123@qq.com');
INSERT INTO `user` VALUES (171, 'java', '123456', '13546548658', '2022-03-15 09:29:39', '123@qq.com');
INSERT INTO `user` VALUES (172, 'java', '123456', '13546548658', '2022-03-15 09:29:39', '123@qq.com');
INSERT INTO `user` VALUES (173, 'java', '123456', '13546548658', '2022-03-15 09:29:39', '123@qq.com');
INSERT INTO `user` VALUES (174, 'java', '123456', '13546548658', '2022-03-15 09:29:39', '123@qq.com');
INSERT INTO `user` VALUES (175, 'java', '123456', '13546548658', '2022-03-15 09:29:39', '123@qq.com');
INSERT INTO `user` VALUES (176, 'java', '123456', '13546548658', '2022-03-15 09:29:39', '123@qq.com');
INSERT INTO `user` VALUES (177, 'java', '123456', '13546548658', '2022-03-15 09:29:40', '123@qq.com');
INSERT INTO `user` VALUES (178, 'java', '123456', '13546548658', '2022-03-15 09:29:40', '123@qq.com');
INSERT INTO `user` VALUES (179, 'java', '123456', '13546548658', '2022-03-15 09:29:40', '123@qq.com');
INSERT INTO `user` VALUES (180, 'java', '123456', '13546548658', '2022-03-15 09:29:40', '123@qq.com');
INSERT INTO `user` VALUES (181, 'java', '123456', '13546548658', '2022-03-15 09:29:40', '123@qq.com');
INSERT INTO `user` VALUES (182, 'java', '123456', '13546548658', '2022-03-15 09:29:41', '123@qq.com');
INSERT INTO `user` VALUES (183, 'java', '123456', '13546548658', '2022-03-15 09:29:41', '123@qq.com');
INSERT INTO `user` VALUES (184, 'java', '123456', '13546548658', '2022-03-15 09:29:41', '123@qq.com');
INSERT INTO `user` VALUES (185, 'java', '123456', '13546548658', '2022-03-15 09:29:41', '123@qq.com');
INSERT INTO `user` VALUES (186, 'java', '123456', '13546548658', '2022-03-15 09:29:41', '123@qq.com');

SET FOREIGN_KEY_CHECKS = 1;

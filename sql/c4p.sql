/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50733
 Source Host           : localhost:3306
 Source Schema         : c4p

 Target Server Type    : MySQL
 Target Server Version : 50733
 File Encoding         : 65001

 Date: 03/03/2022 20:34:39
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for c4p_user
-- ----------------------------
DROP TABLE IF EXISTS `c4p_user`;
CREATE TABLE `c4p_user`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '用户唯一id',
  `username` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `nickname` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `avatar` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '用户头像',
  `is_deleted` tinyint(1) UNSIGNED NULL DEFAULT NULL COMMENT '删除标志 0未删除 1删除',
  `is_enabled` tinyint(1) UNSIGNED NULL DEFAULT NULL COMMENT '激活标识 0未激活 1激活',
  `gmt_created` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c4p_user
-- ----------------------------
INSERT INTO `c4p_user` VALUES (1453633566243028992, '张三', '$2a$10$UExzO.v85pcRYG6svEm3y.Mi9VFaKiyfGIA6M16Sk/aBNFnDoUCIe', '法外狂徒', 'https://image.baidu.com/search/detail?ct=503316480&z=undefined&tn=baiduimagedetail&ipn=d&word=%E7%8B%97%E5%AD%90&step_word=&ie=utf-8&in=&cl=2&lm=-1&st=undefined&hd=undefined&latest=undefined&copyright=undefined&cs=3944984977,1770626713&os=1294330248,3156432077&simid=3321981086,172527004&pn=1&rn=1&di=201850&ln=1869&fr=&fmq=1635782394469_R&fm=&ic=undefined&s=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&is=0,0&istype=0&ist=&jit=&bdtype=0&spn=0&pi=0&gsm=0&objurl=https%3A%2F%2Fgimg2.baidu.com%2Fimage_search%2Fsrc%3Dhttp%253A%252F%252Fb-ssl.duitang.com%252Fuploads%252Fitem%252F201810%252F31%252F20181031045755_vc2KY.thumb.700_0.jpeg%26refer%3Dhttp%253A%252F%252Fb-ssl.duitang.com%26app%3D2002%26size%3Df9999%2C10000%26q%3Da80%26n%3D0%26g%3D0n%26fmt%3Djpeg%3Fsec%3D1638374391%26t%3D63d30320b1c1c9c349e198c6f4f545f6&rpstart=0&rpnum=0&adpicid=0&nojc=undefined&dyTabStr=MCwzLDEsNiw0LDUsOCw3LDIsOQ%3D%3D', 0, 1, '2021-10-28 16:03:44', '2022-03-03 17:21:41');

-- ----------------------------
-- Table structure for c4p_user_auth
-- ----------------------------
DROP TABLE IF EXISTS `c4p_user_auth`;
CREATE TABLE `c4p_user_auth`  (
  `id` bigint(20) UNSIGNED NOT NULL COMMENT '主键ID',
  `user_id` bigint(20) UNSIGNED NULL DEFAULT NULL COMMENT '用户ID',
  `identity_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录类型',
  `identifier` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '登录标识',
  `credential` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码凭证',
  `is_deleted` tinyint(1) UNSIGNED NULL DEFAULT NULL COMMENT '删除标识',
  `gmt_created` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of c4p_user_auth
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;

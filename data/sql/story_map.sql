/*
Navicat MySQL Data Transfer

Source Server         : s_m
Source Server Version : 50724
Source Host           : 47.101.194.161:3306
Source Database       : story_map

Target Server Type    : MYSQL
Target Server Version : 50724
File Encoding         : 65001

Date: 2018-12-22 15:32:26
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity_card
-- ----------------------------
DROP TABLE IF EXISTS `activity_card`;
CREATE TABLE `activity_card` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `creator_id` int(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `color` varchar(255) DEFAULT '',
  `story_map_id` int(255) NOT NULL,
  `order` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity_card
-- ----------------------------

-- ----------------------------
-- Table structure for image
-- ----------------------------
DROP TABLE IF EXISTS `image`;
CREATE TABLE `image` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `url` varchar(255) DEFAULT NULL,
  `category` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of image
-- ----------------------------

-- ----------------------------
-- Table structure for release
-- ----------------------------
DROP TABLE IF EXISTS `release`;
CREATE TABLE `release` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `creator_id` int(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `story_map_id` int(255) NOT NULL,
  `order` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of release
-- ----------------------------

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `image_id` int(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `story_map_id` int(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for role_activity
-- ----------------------------
DROP TABLE IF EXISTS `role_activity`;
CREATE TABLE `role_activity` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `role_id` int(255) NOT NULL,
  `activity_id` int(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role_activity
-- ----------------------------

-- ----------------------------
-- Table structure for story_map
-- ----------------------------
DROP TABLE IF EXISTS `story_map`;
CREATE TABLE `story_map` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `work_space_id` int(255) NOT NULL,
  `user_id` int(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of story_map
-- ----------------------------

-- ----------------------------
-- Table structure for sub_task_card
-- ----------------------------
DROP TABLE IF EXISTS `sub_task_card`;
CREATE TABLE `sub_task_card` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `creator_id` int(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `task_id` int(255) NOT NULL,
  `order` varchar(255) NOT NULL,
  `release_id` int(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sub_task_card
-- ----------------------------

-- ----------------------------
-- Table structure for task_card
-- ----------------------------
DROP TABLE IF EXISTS `task_card`;
CREATE TABLE `task_card` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `creator_id` int(255) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `color` varchar(255) DEFAULT NULL,
  `activity_id` int(255) NOT NULL,
  `order` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of task_card
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL COMMENT 'user',
  `description` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `image_id` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '$2a$10$X3piVAjtdUlcmayzg.BrWuezxWt21ab7hgXZLIFcUSVm8CD53wc72', '123456', null, null, null, null);

-- ----------------------------
-- Table structure for user_storymap
-- ----------------------------
DROP TABLE IF EXISTS `user_storymap`;
CREATE TABLE `user_storymap` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `user_id` int(255) NOT NULL,
  `story_map_id` int(255) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_storymap
-- ----------------------------

-- ----------------------------
-- Table structure for work_space
-- ----------------------------
DROP TABLE IF EXISTS `work_space`;
CREATE TABLE `work_space` (
  `id` int(255) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `user_id` int(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of work_space
-- ----------------------------

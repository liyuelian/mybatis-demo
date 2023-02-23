-- 创建数据库
CREATE DATABASE `li_mybatis`;
USE `li_mybatis`;
-- 创建monster表
CREATE TABLE `monster`(
`id` INT NOT NULL AUTO_INCREMENT,
`age` INT NOT NULL,
`birthday` DATE DEFAULT NULL,
`email` VARCHAR(255) NOT NULL,
`gender` TINYINT NOT NULL,-- 1 male，0 female
`name` VARCHAR(255) NOT NULL,
`salary` DOUBLE NOT NULL,
PRIMARY KEY(`id`)
)CHARSET=utf8
-- insert
INSERT INTO `monster` VALUES(NULL,200,'2000-11-11','nmw@qq.com',1,'牛魔王',8888);
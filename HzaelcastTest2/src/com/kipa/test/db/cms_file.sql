/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.5.21 : Database - cms
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`cms` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `cms`;

/*Table structure for table `cms_file` */

DROP TABLE IF EXISTS `cms_file`;

CREATE TABLE `cms_file` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `filename` varchar(300) NOT NULL,
  `filesize` varchar(100) NOT NULL,
  `filepath` varchar(300) NOT NULL,
  `filetype` varchar(100) NOT NULL,
  `timestamp` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1588 DEFAULT CHARSET=utf8;



/*
SQLyog Ultimate v12.08 (32 bit)
MySQL - 5.7.24 : Database - test
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`test` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `test`;

/*Table structure for table `person` */

DROP TABLE IF EXISTS `person`;

CREATE TABLE `person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `age` tinyint(5) DEFAULT NULL,
  `gender` tinyint(5) DEFAULT NULL,
  `height` decimal(18,6) DEFAULT NULL,
  `weight` decimal(18,6) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `birthday` timestamp(6) NOT NULL DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `person` */

insert  into `person`(`id`,`name`,`age`,`gender`,`height`,`weight`,`address`,`birthday`) values (7,'zjx',1,1,'170.000000','60.000000','深圳宝安','2019-01-01 08:00:00.000000'),(8,'zjx',1,1,'170.000000','60.000000','深圳宝安','2019-01-01 08:00:00.000000'),(9,'zjx',1,1,'170.000000','60.000000','深圳宝安','2019-01-01 08:00:00.000000'),(10,'zjx',1,1,'170.000000','60.000000','深圳宝安','2019-01-01 08:00:00.000000'),(11,'zjx',1,1,'170.000000','60.000000','深圳宝安','2019-01-01 08:00:00.000000'),(12,'zjx',1,1,'170.000000','60.000000','深圳宝安','2019-01-01 08:00:00.000000'),(13,'zjx',1,1,'170.000000','60.000000','深圳宝安','2019-01-01 08:00:00.000000'),(14,'zjx',1,1,'170.000000','60.000000','深圳宝安','2019-01-01 08:00:00.000000'),(15,'zjx',1,1,'170.000000','60.000000','深圳宝安','2019-01-01 08:00:00.000000'),(16,'zjx',1,1,'170.000000','60.000000','深圳宝安','2019-01-01 08:00:00.000000'),(17,'zjx',1,1,'170.000000','60.000000','深圳宝安','2019-01-01 08:00:00.000000');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

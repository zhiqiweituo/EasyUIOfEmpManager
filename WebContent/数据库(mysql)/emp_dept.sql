/*
SQLyog Ultimate v11.33 (64 bit)
MySQL - 5.5.24 : Database - emp_dept
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`emp_dept` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `emp_dept`;

/*Table structure for table `dept` */

DROP TABLE IF EXISTS `dept`;

CREATE TABLE `dept` (
  `deptno` int(11) NOT NULL,
  `dname` varchar(20) DEFAULT NULL,
  `loc` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`deptno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dept` */

insert  into `dept`(`deptno`,`dname`,`loc`) values (10,'Accounting','New York'),(20,'Research','Dallas'),(30,'Sales','Chicago'),(40,'Operations','Boston'),(50,'a2','a2'),(60,'b','b'),(70,'c','c');

/*Table structure for table `emp` */

DROP TABLE IF EXISTS `emp`;

CREATE TABLE `emp` (
  `empno` int(11) NOT NULL,
  `ename` varchar(20) DEFAULT NULL,
  `job` varchar(20) DEFAULT NULL,
  `mgr` int(11) DEFAULT NULL,
  `hiredate` date DEFAULT NULL,
  `sal` decimal(7,2) DEFAULT NULL,
  `comm` decimal(7,2) DEFAULT NULL,
  `deptno` int(11) DEFAULT NULL,
  PRIMARY KEY (`empno`),
  KEY `deptno` (`deptno`),
  CONSTRAINT `emp_ibfk_1` FOREIGN KEY (`deptno`) REFERENCES `dept` (`deptno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `emp` */

insert  into `emp`(`empno`,`ename`,`job`,`mgr`,`hiredate`,`sal`,`comm`,`deptno`) values (7369,'Smith','clerk',7902,'1980-12-17','800.00',NULL,20),(7499,'Allen','salesman',7698,'1981-02-20','1600.00','300.00',30),(7521,'Ward','salesman',7698,'1981-02-22','1250.00','500.00',30),(7566,'Jones','manager',7839,'1981-04-02','2975.00',NULL,20),(7654,'Martin','salesman',7698,'1981-09-28','1250.00','1400.00',30),(7698,'Blake','manager',7839,'1981-05-01','2850.00',NULL,30),(7782,'Clark','manager',7839,'1981-06-09','2450.00',NULL,10),(7788,'Scott','analyst',7566,'1987-04-19','3000.00',NULL,20),(7839,'King','president',NULL,'1981-11-17','5000.00',NULL,10),(7844,'Turner','salesman',7698,'1981-09-08','1500.00','0.00',30),(7876,'Adams','clerk',7788,'1987-05-23','1100.00',NULL,20),(7900,'James','clerk',7698,'1981-12-03','950.00',NULL,30),(7902,'Ford','analyst',7566,'1981-12-03','3000.00',NULL,20),(7934,'Miller','clerk',7782,'1982-01-23','1300.00',NULL,10),(7936,'Gosling',NULL,NULL,'1981-02-19',NULL,NULL,NULL);

/*Table structure for table `salgrade` */

DROP TABLE IF EXISTS `salgrade`;

CREATE TABLE `salgrade` (
  `grade` int(11) DEFAULT NULL,
  `losal` int(11) DEFAULT NULL,
  `hisal` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `salgrade` */

insert  into `salgrade`(`grade`,`losal`,`hisal`) values (1,700,1200),(2,1201,1400),(3,1401,2000),(4,2001,3000),(5,3001,9999);

/*Table structure for table `tree_lines` */

DROP TABLE IF EXISTS `tree_lines`;

CREATE TABLE `tree_lines` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(30) DEFAULT NULL,
  `parent_id` int(11) DEFAULT NULL,
  `state` varchar(30) DEFAULT NULL,
  `icon_cls` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

/*Data for the table `tree_lines` */

insert  into `tree_lines`(`id`,`text`,`parent_id`,`state`,`icon_cls`) values (1,'My Documents',0,NULL,'icon-application-double'),(2,'Photos',1,NULL,'icon-application-add'),(3,'Program Files',1,'closed','icon-application-add'),(4,'index.html',1,NULL,'icon-application'),(5,'about.html',1,NULL,'icon-application'),(6,'welcome.html',1,NULL,'icon-application'),(7,'Friend',2,NULL,'icon-add'),(8,'Wife',2,NULL,'icon-edit'),(9,'Company',2,NULL,'icon-clear'),(10,'Intel',3,NULL,NULL),(11,'Java',3,NULL,NULL),(12,'Microsoft Office',3,NULL,NULL),(13,'Games',3,NULL,NULL),(14,'退出系统',1,NULL,'icon-application');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `usr` varchar(20) DEFAULT NULL,
  `pwd` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_usr` (`usr`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`usr`,`pwd`) values (1,'www','111'),(2,'a','1');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- MySQL dump 10.13  Distrib 5.7.15, for osx10.11 (x86_64)
--
-- Host: localhost    Database: cms
-- ------------------------------------------------------
-- Server version	5.7.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `content`
--

DROP TABLE IF EXISTS `content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `content` (
  `id` char(13) NOT NULL,
  `fid` char(13) NOT NULL,
  `cover` varchar(64) NOT NULL,
  `title` varchar(128) NOT NULL,
  `keyword` varchar(120) NOT NULL,
  `data` varchar(500) NOT NULL,
  `detail` text NOT NULL,
  `sort` int(11) NOT NULL,
  `createTime` int(11) NOT NULL,
  `changeTime` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `content`
--

LOCK TABLES `content` WRITE;
/*!40000 ALTER TABLE `content` DISABLE KEYS */;
INSERT INTO `content` VALUES ('169e0ef0bf15f','','','2019世界拉力锦标赛实况','拉力 WRC 赛场实况','{\"url\":\"http://www.baidu.com\"}','',2,1554256956,1554354452,2),('169e1b734878e','','','我的市场细分2','简单 灯饰','{\"name\":\"hongbo\",\"age\":\"30\",\"job\":\"程序员\"}','',5,1554270073,1554354237,1),('169e6c479317c','169e1b734878e','','二级内容','','{}','',3,1554354829,1554367345,1),('169e6c9d688a6','169e6c479317c','','三级栏目内容','','{}','',1,1554355181,1554355194,0),('169e70bc9708a','169e6c9d688a6','','新建栏目内容','','{}','',1,1554359503,0,0),('169e78303f524','169e0ef0bf15f','','二级内容B','','{}','',2,1554367316,1554369595,0),('169e7a65b73f9','','','新建栏目内容3','','{}','',6,1554369633,1554459196,1),('169ed0f5368b0','','','新建栏目内容','','{}','',11,1554460398,0,0);
/*!40000 ALTER TABLE `content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `resource` (
  `id` char(13) NOT NULL,
  `bid` char(13) NOT NULL,
  `type` char(4) NOT NULL,
  `path` varchar(128) NOT NULL,
  `name` varchar(64) NOT NULL,
  `size` int(11) NOT NULL,
  `sort` int(11) NOT NULL,
  `time` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resource`
--

LOCK TABLES `resource` WRITE;
/*!40000 ALTER TABLE `resource` DISABLE KEYS */;
INSERT INTO `resource` VALUES ('169f5ff043b47','','jpg','19/04/07/169f5ff043ae4.jpg','',174750,0,0),('169f6c5ec146c','','jpg','19/04/07/169f6c5ebcc7a.jpg','',85380,1,1554623360),('169f7364cbfdb','','png','19/04/07/169f7364cb7f3.png','',130599,2,1554630724),('169f7371a1891','112358','jpg','19/04/07/169f7371a15d6.jpg','',188284,1,1554630777);
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-07 18:08:37

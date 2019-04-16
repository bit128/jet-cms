-- MySQL dump 10.13  Distrib 8.0.15, for osx10.14 (x86_64)
--
-- Host: localhost    Database: cms
-- ------------------------------------------------------
-- Server version	8.0.15

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
 SET NAMES utf8mb4 ;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `comment` (
  `id` char(13) COLLATE utf8mb4_general_ci NOT NULL,
  `bid` char(13) COLLATE utf8mb4_general_ci NOT NULL,
  `uid` char(13) COLLATE utf8mb4_general_ci NOT NULL,
  `content` text COLLATE utf8mb4_general_ci NOT NULL,
  `reply` text COLLATE utf8mb4_general_ci NOT NULL,
  `createTime` int(11) NOT NULL,
  `replyTime` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `content`
--

DROP TABLE IF EXISTS `content`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
INSERT INTO `content` VALUES ('169e0ef0bf15f','','19/04/13/16a15c8adbcc0.jpg','2019世界拉力锦标赛实况','拉力 WRC 赛场实况','{\"url\":\"http://www.baidu.com\"}','<table class=\"content-table\"><thead><tr><th><br></th><th><br></th><th><br></th><th><br></th></tr></thead><tbody><tr><td><br></td><td><br></td><td><br></td><td><br></td></tr><tr><td><br></td><td><br></td><td><br></td><td><br></td></tr><tr><td><br></td><td><br></td><td><br></td><td><br></td></tr></tbody></table><tbody<tr></tbody<tr><br><div>这里是内容</div><br><div>最开始，我以为不是这样的</div><br><div><script type=\"text/javascript\"></div><div>    console.log(\'log\');<br></div><div></script><br><table class=\"content-table\"><thead><tr><th>table<br></th><th>name<br></th><th>attributes<br></th><th>params<br></th></tr></thead><tbody><tr><td>name<br></td><td>top<br></td><td>good<br></td><td>active<br></td></tr><tr><td><br></td><td><br></td><td><br></td><td><br></td></tr><tr><td><br></td><td><br></td><td><br></td><td><br></td></tr></tbody></table><tbody<tr></tbody<tr><br></div>',2,1554256956,1555402917,2),('169e1b734878e','','','我的市场细分2','简单 灯饰','{\"name\":\"hongbo\",\"age\":\"30\",\"job\":\"程序员\"}','',5,1554270073,1554354237,1),('169e6c479317c','169e1b734878e','','二级内容','','{}','',3,1554354829,1554367345,1),('169e6c9d688a6','169e6c479317c','','三级栏目内容','','{}','',1,1554355181,1554355194,0),('169e70bc9708a','169e6c9d688a6','','新建栏目内容','','{}','',1,1554359503,0,0),('169e78303f524','169e0ef0bf15f','','二级内容B','','{}','那最深的角落',2,1554367316,1555148875,0),('169e7a65b73f9','','','新建栏目内容3','','{}','',6,1554369633,1554459196,1),('169ed0f5368b0','','','新建栏目内容','','{}','',11,1554460398,0,0);
/*!40000 ALTER TABLE `content` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resource`
--

DROP TABLE IF EXISTS `resource`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
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
INSERT INTO `resource` VALUES ('169f5ff043b47','','jpg','19/04/07/169f5ff043ae4.jpg','wahaha',174750,0,1555405890),('169f6c5ec146c','','jpg','19/04/07/169f6c5ebcc7a.jpg','',85380,1,1554623360),('169f7364cbfdb','','png','19/04/07/169f7364cb7f3.png','',130599,2,1554630724),('169f7371a1891','112358','jpg','19/04/07/169f7371a15d6.jpg','',188284,1,1554630777),('16a0c81f1e048','169e0ef0bf15f','zip','19/04/11/16a0c81f1dd5b.zip','压缩包',8800,2,1554988009),('16a15c8adbf3b','169e0ef0bf15f','jpg','19/04/13/16a15c8adbcc0.jpg','清洁工',217866,3,1555143645);
/*!40000 ALTER TABLE `resource` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
 SET character_set_client = utf8mb4 ;
CREATE TABLE `user` (
  `id` char(13) NOT NULL,
  `account` varchar(32) NOT NULL,
  `password` char(32) NOT NULL,
  `token` char(32) NOT NULL,
  `name` varchar(32) NOT NULL,
  `avatar` varchar(64) NOT NULL,
  `gender` tinyint(4) NOT NULL,
  `role` int(11) NOT NULL,
  `count` int(11) NOT NULL,
  `ip` varchar(15) NOT NULL,
  `createTime` int(11) NOT NULL,
  `activeTime` int(11) NOT NULL,
  `status` tinyint(4) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('16a24a5335b58','hongerbo@qq.com','e10adc3949ba59abbe56e057f20f883e','a593980a325a7e8408e598b38b0d446a','秘法师','',0,7,1,'127.0.0.1',1555392967,1555402751,9);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-04-16 18:12:38

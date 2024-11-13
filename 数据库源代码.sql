-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: system
-- ------------------------------------------------------
-- Server version	8.0.34

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `breed`
--

DROP TABLE IF EXISTS `breed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `breed` (
  `breed_id` int NOT NULL AUTO_INCREMENT,
  `breed_name` char(20) DEFAULT NULL,
  PRIMARY KEY (`breed_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `breed`
--

LOCK TABLES `breed` WRITE;
/*!40000 ALTER TABLE `breed` DISABLE KEYS */;
INSERT INTO `breed` VALUES (1,'猫'),(2,'狗'),(3,'鸟'),(5,'鱼'),(6,'虫'),(7,'羊');
/*!40000 ALTER TABLE `breed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `records`
--

DROP TABLE IF EXISTS `records`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `records` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_name` char(20) DEFAULT NULL,
  `zoon_id` int DEFAULT NULL,
  `zoon_name` char(20) DEFAULT NULL,
  `time` date DEFAULT NULL,
  `spend` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `records`
--

LOCK TABLES `records` WRITE;
/*!40000 ALTER TABLE `records` DISABLE KEYS */;
INSERT INTO `records` VALUES (1,'安娜',4,'中户田园猫','2024-02-16',120),(2,'张三丰',3,'贵宾犬','2024-05-16',200),(3,'安东',4,'中华田园猫','2024-05-26',120),(4,'安娜',5,'布偶猫','2021-03-24',120),(5,'邢梦琪',1,'拉布拉多','2024-06-27',300),(6,'安娜',1,'拉布拉多','2024-06-28',300),(7,'一',1,'拉布拉多','2024-06-28',300),(8,'一',3,'贵宾犬','2024-06-27',200),(9,'安娜',3,'贵宾犬','2024-07-20',200),(10,'安娜',8,'牧羊犬','2024-07-10',2900);
/*!40000 ALTER TABLE `records` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` char(20) DEFAULT NULL,
  `password` char(20) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `role` int DEFAULT '1',
  `sex` char(20) DEFAULT NULL,
  `phone` char(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (3,'鱼','2580432','2000-04-16',1,'男','15643935847'),(4,'刘富贵','5623147','1999-03-24',2,'男','16586214897'),(5,'安娜','654654','2001-05-09',1,'女','15647896548'),(6,'尤娜','4230432','2000-04-18',1,'女','17543935847'),(7,'安东','123321','2000-10-16',1,'男','15642354624'),(8,'小六','456654','2001-05-16',1,'女','19216848567'),(11,'小米','123456','1994-06-08',1,'男','12365847542'),(12,'小七','123654','2004-08-09',1,'女','15648795645'),(14,'妲己','456654','2004-08-16',2,'女','15648795897'),(15,'邢梦琪','456456','2003-11-16',1,'女','15648795547'),(17,'一','789789','2023-08-14',1,'女','11111111111'),(18,'二','321321','2023-08-18',2,'女','22222222222'),(19,'小明','78948967','2006-01-05',1,'男','15698745624'),(20,'小红','78948569','2006-01-06',1,'女','15698745678'),(21,'蘑菇头','78948576','2006-01-08',1,'男','15698747856'),(22,'朱小明','78948587','2006-01-17',1,'男','15698744561'),(23,'杜文婷','78948512','2006-01-22',1,'女','15698746541'),(24,'小九','78948967','2006-01-05',1,'男','15698745624'),(25,'高媛玲','78948741','2006-01-08',1,'女','15698745625'),(26,'小吴','78948754','2004-04-08',1,'女','15698745414'),(27,'王五','456964321','2004-06-07',1,'男','15698745513'),(28,'黑马','2580423','2000-04-20',1,'男','156439358561'),(29,'闰土','5623317','1999-06-14',1,'男','165862148135');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `zoon`
--

DROP TABLE IF EXISTS `zoon`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zoon` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` char(20) DEFAULT NULL,
  `type_id` int DEFAULT NULL,
  `sex` char(20) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `unit` int DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zoon`
--

LOCK TABLES `zoon` WRITE;
/*!40000 ALTER TABLE `zoon` DISABLE KEYS */;
INSERT INTO `zoon` VALUES (1,'拉布拉多',2,'雌','2024-06-12',300),(2,'萨摩耶',2,'雌','2023-12-16',3000),(3,'贵宾犬',2,'雄','2023-05-24',200),(4,'中华田园猫',1,'雌','2024-04-14',120),(5,'布偶猫',1,'雌','2024-01-19',120),(7,'柴犬',2,'雄','2018-06-12',2500),(8,'牧羊犬',2,'雌','2022-12-16',2900);
/*!40000 ALTER TABLE `zoon` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-07-11 23:20:39

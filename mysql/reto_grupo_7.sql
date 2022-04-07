CREATE DATABASE  IF NOT EXISTS `reto_grupo_7` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `reto_grupo_7`;
-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: localhost    Database: reto_grupo_7
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `car` (
  `carRegistration` varchar(10) NOT NULL,
  `numDoors` int unsigned NOT NULL,
  `trunkCapacity` int unsigned NOT NULL,
  PRIMARY KEY (`carRegistration`),
  UNIQUE KEY `idcar_UNIQUE` (`carRegistration`),
  CONSTRAINT `carRegistration FK` FOREIGN KEY (`carRegistration`) REFERENCES `vehicle` (`registration`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES ('12345',2,120),('310ALP',2,0),('3333GT',2,120),('3434GTR',2,150),('4040FFF',2,50),('4040FGT',2,0),('M3E30BMW',2,180);
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `history` (
  `idhistory` int unsigned NOT NULL AUTO_INCREMENT,
  `event` varchar(45) NOT NULL,
  `registration` varchar(10) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`idhistory`),
  UNIQUE KEY `idhistory_UNIQUE` (`idhistory`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
INSERT INTO `history` VALUES (21,'BOUGHT','ABCDEF','2022-04-06'),(22,'PAINTED','ABCDEF','2022-04-06'),(23,'SOLD','ABCDEF','2022-04-06');
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `series`
--

DROP TABLE IF EXISTS `series`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `series` (
  `serieNum` int unsigned NOT NULL AUTO_INCREMENT,
  `brand` varchar(45) NOT NULL,
  `model` varchar(45) NOT NULL,
  `year` int unsigned NOT NULL,
  PRIMARY KEY (`serieNum`),
  UNIQUE KEY `serieNum_UNIQUE` (`serieNum`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `series`
--

LOCK TABLES `series` WRITE;
/*!40000 ALTER TABLE `series` DISABLE KEYS */;
INSERT INTO `series` VALUES (4,'Nissan','Skyline R34',1999),(7,'Ford','GT40',1964),(8,'Renault','Alpine',1984),(10,'BMW','M3 E30',1986),(11,'Ferrari','F40',1987),(14,'Volkswagen','California',1950),(15,'Ford','Mustang Shelby GT500',1967),(25,'Volkswagen','California t2',1967);
/*!40000 ALTER TABLE `series` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `truck`
--

DROP TABLE IF EXISTS `truck`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `truck` (
  `truckRegistration` varchar(10) NOT NULL,
  `load` int unsigned NOT NULL,
  `merchandiseType` varchar(1) NOT NULL,
  PRIMARY KEY (`truckRegistration`),
  UNIQUE KEY `idtruck_UNIQUE` (`truckRegistration`),
  CONSTRAINT `truckRegistration FK` FOREIGN KEY (`truckRegistration`) REFERENCES `vehicle` (`registration`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `truck`
--

LOCK TABLES `truck` WRITE;
/*!40000 ALTER TABLE `truck` DISABLE KEYS */;
INSERT INTO `truck` VALUES ('VW00CAL',400,'A'),('VWT1950',700,'A');
/*!40000 ALTER TABLE `truck` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `vehicle` (
  `serieNum` int unsigned NOT NULL,
  `registration` varchar(10) NOT NULL,
  `numFrame` varchar(17) NOT NULL,
  `colour` varchar(45) NOT NULL,
  `numOfSeats` int unsigned NOT NULL,
  `price` int unsigned NOT NULL,
  `painted` tinyint(1) NOT NULL,
  PRIMARY KEY (`registration`),
  UNIQUE KEY `id_vehicle_UNIQUE` (`registration`),
  KEY `serieNum FK_idx` (`serieNum`),
  CONSTRAINT `serieNum FK` FOREIGN KEY (`serieNum`) REFERENCES `series` (`serieNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES (15,'12345','12345678901234567','grey and blue',2,95000,1),(8,'310ALP','98765432101234567','red',2,45000,0),(15,'3333GT','12345678901234567','dark grey',2,150000,0),(4,'3434GTR','12345678901234567','blue',2,110000,0),(11,'4040FFF','98765432101234567','ferrari red',2,1500000,0),(7,'4040FGT','09876543211234567','red and blue',2,2000000,0),(10,'M3E30BMW','12345678901234567','white',5,75000,0),(25,'VW00CAL','lkgtrvg2678452gt9','orange',7,70000,1),(14,'VWT1950','lkgtrvg2678452gt9','pink',7,180000,1);
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `vehicleIsBought` AFTER INSERT ON `vehicle` FOR EACH ROW BEGIN
DECLARE s1 VARCHAR(10)character set utf8;
DECLARE s2 VARCHAR(10) character set utf8;
SET s1 = "BOUGHT";
SET s2 =  NEW.registration;
INSERT INTO history(event, registration, date) values(s1, s2, NOW());
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `vehicleIsPainted` AFTER UPDATE ON `vehicle` FOR EACH ROW BEGIN
DECLARE s1 VARCHAR(10)character set utf8;
DECLARE s2 VARCHAR(10) character set utf8;
IF (NEW.colour != OLD.colour OR NEW.painted != OLD.painted) THEN
	SET s1 = "PAINTED";
	SET s2 =  OLD.registration;
	INSERT INTO history(event, registration, date) values(s1, s2, NOW());
END IF;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `vehicleIsSold` BEFORE DELETE ON `vehicle` FOR EACH ROW BEGIN
DECLARE s1 VARCHAR(10)character set utf8;
DECLARE s2 VARCHAR(10) character set utf8;
SET s1 = "SOLD";
SET s2 =  OLD.registration;
INSERT INTO history(event, registration, date) values(s1, s2, NOW());
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-07  8:42:22

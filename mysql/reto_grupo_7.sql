CREATE DATABASE  IF NOT EXISTS `reto_grupo_7`;
USE `reto_grupo_7`;

--
-- Table structure for table `series`
--

DROP TABLE IF EXISTS `series`;
CREATE TABLE `series` (
  `serieNum` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `brand` varchar(45) NOT NULL,
  `model` varchar(45) NOT NULL,
  `year` int(10) unsigned NOT NULL,
  PRIMARY KEY (`serieNum`),
  UNIQUE KEY `serieNum_UNIQUE` (`serieNum`)
) ENGINE=InnoDB AUTO_INCREMENT=39;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `series`
--

LOCK TABLES `series` WRITE;
/*!40000 ALTER TABLE `series` DISABLE KEYS */;
INSERT INTO `series` VALUES (4,'Nissan','Skyline R34',1999),(7,'Ford','GT40',1964),(8,'Renault','Alpine',1984),(10,'BMW','M3 E30',1986),(11,'Ferrari','F40',1987),(14,'Volkswagen','California',1950),(15,'Ford','Mustang Shelby GT500',1967),(25,'Volkswagen','California t2',1967),(38,'Mercedes Benz','300 SL Roadster',1963);
/*!40000 ALTER TABLE `series` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `vehicle`
--

DROP TABLE IF EXISTS `vehicle`;
CREATE TABLE `vehicle` (
  `serieNum` int(10) unsigned NOT NULL,
  `registration` varchar(10) NOT NULL,
  `numFrame` varchar(17) NOT NULL,
  `colour` varchar(45) NOT NULL,
  `numOfSeats` int(10) unsigned NOT NULL,
  `price` int(10) unsigned NOT NULL,
  `painted` tinyint(1) NOT NULL,
  PRIMARY KEY (`registration`),
  UNIQUE KEY `id_vehicle_UNIQUE` (`registration`),
  KEY `serieNum FK_idx` (`serieNum`),
  CONSTRAINT `serieNum FK` FOREIGN KEY (`serieNum`) REFERENCES `series` (`serieNum`)
) ENGINE=InnoDB;

--
-- Dumping data for table `vehicle`
--

LOCK TABLES `vehicle` WRITE;
/*!40000 ALTER TABLE `vehicle` DISABLE KEYS */;
INSERT INTO `vehicle` VALUES (15,'12345','12345678901234567','grey and blue',2,95000,1),(8,'310ALP','98765432101234567','red',2,45000,0),(15,'3333GT','12345678901234567','dark grey',2,150000,0),(4,'3434GTR','12345678901234567','blue',2,110000,0),(11,'4040FFF','98765432101234567','ferrari red',2,1500000,0),(7,'4040FGT','09876543211234567','red and blue',2,2000000,0),(10,'M3E30BMW','12345678901234567','white',5,75000,0),(38,'MB300SLR','863ghy479064rtujs','grey',2,1200000,0),(25,'VW00CAL','lkgtrvg2678452gt9','orange',7,70000,1),(14,'VWT1950','lkgtrvg2678452gt9','pink',7,180000,1);
/*!40000 ALTER TABLE `vehicle` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `car`
--

DROP TABLE IF EXISTS `car`;
CREATE TABLE `car` (
  `carRegistration` varchar(10) NOT NULL,
  `numDoors` int(10) unsigned NOT NULL,
  `trunkCapacity` int(10) unsigned NOT NULL,
  PRIMARY KEY (`carRegistration`),
  UNIQUE KEY `idcar_UNIQUE` (`carRegistration`),
  CONSTRAINT `carRegistration FK` FOREIGN KEY (`carRegistration`) REFERENCES `vehicle` (`registration`)
) ENGINE=InnoDB;

--
-- Dumping data for table `car`
--

LOCK TABLES `car` WRITE;
/*!40000 ALTER TABLE `car` DISABLE KEYS */;
INSERT INTO `car` VALUES ('12345',2,120),('310ALP',2,0),('3333GT',2,120),('3434GTR',2,150),('4040FFF',2,50),('4040FGT',2,0),('M3E30BMW',2,180),('MB300SLR',2,80);
/*!40000 ALTER TABLE `car` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `truck`
--

DROP TABLE IF EXISTS `truck`;
CREATE TABLE `truck` (
  `truckRegistration` varchar(10) NOT NULL,
  `load` int(10) unsigned NOT NULL,
  `merchandiseType` varchar(1) NOT NULL,
  PRIMARY KEY (`truckRegistration`),
  UNIQUE KEY `idtruck_UNIQUE` (`truckRegistration`),
  CONSTRAINT `truckRegistration FK` FOREIGN KEY (`truckRegistration`) REFERENCES `vehicle` (`registration`)
) ENGINE=InnoDB;

--
-- Dumping data for table `truck`
--

LOCK TABLES `truck` WRITE;
/*!40000 ALTER TABLE `truck` DISABLE KEYS */;
INSERT INTO `truck` VALUES ('VW00CAL',400,'A'),('VWT1950',700,'A');
/*!40000 ALTER TABLE `truck` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `history`
--

DROP TABLE IF EXISTS `history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
CREATE TABLE `history` (
  `idhistory` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `event` varchar(45) NOT NULL,
  `registration` varchar(10) NOT NULL,
  `date` date NOT NULL,
  PRIMARY KEY (`idhistory`),
  UNIQUE KEY `idhistory_UNIQUE` (`idhistory`),
  KEY `registration FK_idx` (`registration`),
  CONSTRAINT `registration FK` FOREIGN KEY (`registration`) REFERENCES `vehicle` (`registration`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25;

--
-- Dumping data for table `history`
--

LOCK TABLES `history` WRITE;
/*!40000 ALTER TABLE `history` DISABLE KEYS */;
INSERT INTO `history` VALUES (21,'BOUGHT','ABCDEF','2022-04-06'),(22,'PAINTED','ABCDEF','2022-04-06'),(23,'SOLD','ABCDEF','2022-04-06'),(24,'BOUGHT','MB300SLR','2022-04-07');
/*!40000 ALTER TABLE `history` ENABLE KEYS */;
UNLOCK TABLES;

DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `vehicleIsBought` AFTER INSERT ON `vehicle` FOR EACH ROW BEGIN
DECLARE s1 VARCHAR(10)character set utf8;
DECLARE s2 VARCHAR(10) character set utf8;
SET s1 = "BOUGHT";
SET s2 =  NEW.registration;
INSERT INTO history(event, registration, date) values(s1, s2, NOW());
END */;;
DELIMITER ;

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

DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `vehicleIsSold` BEFORE DELETE ON `vehicle` FOR EACH ROW BEGIN
DECLARE s1 VARCHAR(10)character set utf8;
DECLARE s2 VARCHAR(10) character set utf8;
SET s1 = "SOLD";
SET s2 =  OLD.registration;
INSERT INTO history(event, registration, date) values(s1, s2, NOW());
END */;;
DELIMITER ;


-- Dump completed on 2022-04-07 20:00:50

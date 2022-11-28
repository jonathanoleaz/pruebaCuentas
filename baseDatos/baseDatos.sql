-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
-- Este script es ejecutable via comando "SOURCE pathArchivo\baseDatos.sql", descomentar secciones de creacion de usuario y 
-- schema si es la primera vez que se ejecutan.
-- Host: localhost    Database: prueba_cuentas
-- ------------------------------------------------------
-- Server version	5.5.5-10.5.10-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

---CREATE USER 'usr_prueba_cuentas'@'localhost' IDENTIFIED BY 'password';



---FLUSH PRIVILEGES;

--CREATE DATABASE bd_prueba_cuentas;

---GRANT ALL PRIVILEGES ON * . * TO 'new_user'@'localhost';

--USE prueba_cuentas;
--
-- Table structure for table `cliente`
--



DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cliente` (
  `pk_cliente` bigint(20) NOT NULL AUTO_INCREMENT,
  `cliente_id` varchar(45) NOT NULL,
  `contrasenia` varchar(45) NOT NULL,
  `estado` tinyint(4) NOT NULL,
  `fk_persona` bigint(20) NOT NULL,
  PRIMARY KEY (`pk_cliente`),
  KEY `fk_TCLIENTE_TPERSONA_idx` (`fk_persona`),
  CONSTRAINT `fk_TCLIENTE_TPERSONA` FOREIGN KEY (`fk_persona`) REFERENCES `persona` (`pk_persona`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cliente`
--

LOCK TABLES `cliente` WRITE;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` VALUES (2,'d000','33223',1,2),(3,'d000','33223',1,3),(4,'d000','33223',1,4),(5,'d000','33223',1,5),(6,'d000','33223',1,6),(7,'d000','33223',1,7),(8,'d000','33223',1,8),(9,'d000','33223',1,9),(10,'d000','33223',1,10),(12,'d000','33223',1,12),(13,'d000','33223',1,13),(14,'d000','33223',1,14),(15,'d000','33223',1,15),(16,'d000','33223',1,16),(17,'d000','33223',1,17);
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cuenta`
--

DROP TABLE IF EXISTS `cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cuenta` (
  `pk_cuenta` bigint(20) NOT NULL AUTO_INCREMENT,
  `numero_cuenta` varchar(45) NOT NULL,
  `saldo_inicial` double NOT NULL,
  `fk_cliente` bigint(20) NOT NULL,
  `fk_tipo_cuenta` bigint(20) NOT NULL,
  `estado` tinyint(4) NOT NULL,
  PRIMARY KEY (`pk_cuenta`),
  KEY `fk_TCUENTA_TCLIENTE1_idx` (`fk_cliente`),
  KEY `fk_TCUENTA_CTIPOCUENTA1_idx` (`fk_tipo_cuenta`),
  CONSTRAINT `fk_TCUENTA_CTIPOCUENTA1` FOREIGN KEY (`fk_tipo_cuenta`) REFERENCES `tipo_cuenta` (`pk_tipo_cuenta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_TCUENTA_TCLIENTE1` FOREIGN KEY (`fk_cliente`) REFERENCES `cliente` (`pk_cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cuenta`
--

LOCK TABLES `cuenta` WRITE;
/*!40000 ALTER TABLE `cuenta` DISABLE KEYS */;
INSERT INTO `cuenta` VALUES (3,'23258532',10000,3,1,1),(4,'122222',10000,7,1,1),(5,'23258532',10000,10,1,1),(6,'23258532',10000,10,1,1),(7,'23258532',10000,10,1,1),(8,'23258532',10000,10,1,1),(9,'23258532',10000,10,1,1),(10,'23258532',10000,10,1,1),(13,'23258532',10000,5,1,1),(14,'232585321',10000,5,1,1);
/*!40000 ALTER TABLE `cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `genero`
--

DROP TABLE IF EXISTS `genero`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `genero` (
  `pk_genero` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`pk_genero`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `genero`
--

LOCK TABLES `genero` WRITE;
/*!40000 ALTER TABLE `genero` DISABLE KEYS */;
INSERT INTO `genero` VALUES (1,'MASC'),(2,'FEM');
/*!40000 ALTER TABLE `genero` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `movimiento`
--

DROP TABLE IF EXISTS `movimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `movimiento` (
  `pk_movimiento` bigint(20) NOT NULL AUTO_INCREMENT,
  `fecha` datetime NOT NULL,
  `valor` double NOT NULL,
  `saldo` double NOT NULL,
  `fk_cuenta` bigint(20) NOT NULL,
  `fk_tipo_movimiento` bigint(20) NOT NULL,
  `clave` varchar(45) NOT NULL,
  PRIMARY KEY (`pk_movimiento`),
  KEY `fk_TMOVIMIENTO_TCUENTA1_idx` (`fk_cuenta`),
  KEY `fk_TMOVIMIENTO_TTIPO_MOVIMIENTO1_idx` (`fk_tipo_movimiento`),
  CONSTRAINT `fk_TMOVIMIENTO_TCUENTA1` FOREIGN KEY (`fk_cuenta`) REFERENCES `cuenta` (`pk_cuenta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_TMOVIMIENTO_TTIPO_MOVIMIENTO1` FOREIGN KEY (`fk_tipo_movimiento`) REFERENCES `tipo_movimiento` (`pk_tipo_movimiento`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `movimiento`
--

LOCK TABLES `movimiento` WRITE;
/*!40000 ALTER TABLE `movimiento` DISABLE KEYS */;
INSERT INTO `movimiento` VALUES (1,'2022-11-25 01:04:24',1,1,3,1,'1'),(2,'2022-11-25 05:13:28',-1,9995,4,1,'00656110-65d9-4549-998b-af53756843fc'),(3,'2022-11-25 01:43:00',1,1,3,1,'1'),(4,'2022-11-25 01:43:00',1,1,3,1,'1'),(5,'2022-11-25 01:43:01',1,1,3,1,'1'),(6,'2022-11-25 01:43:02',1,1,3,1,'1'),(7,'2022-11-25 01:43:02',1,1,3,1,'1'),(8,'2022-11-25 01:58:35',1,1,3,1,'1'),(9,'2022-11-25 01:58:47',1,1,3,1,'1a24856e-eb28-413e-967e-645b8cecbbbd'),(10,'2022-11-25 01:58:48',1,1,3,1,'ffe956ad-601b-4e30-b3ea-d7e862ac9bef'),(11,'2022-11-25 03:01:17',-1,0,3,1,'4446cb5a-c4f2-4c7d-b1d0-fb859387051c'),(12,'2022-11-25 03:10:00',1,1,3,2,'39c2bce2-9d11-4de9-b24f-c6e130d14350'),(13,'2022-11-25 03:10:05',1,2,3,2,'4ff3817e-5a3c-4737-8c52-3bd51d48800d'),(14,'2022-11-25 03:10:06',1,3,3,2,'7cf3ef69-7a12-43a0-844c-f246a9d18781'),(15,'2022-11-25 03:10:07',1,4,3,2,'49a4ba34-5f21-4142-9338-7324433c98ca'),(16,'2022-11-25 03:10:07',1,5,3,2,'64bd668d-8c82-4fbe-888c-5e7c0b1bf03c'),(17,'2022-11-25 03:10:08',1,6,3,2,'da99bd35-34aa-4a00-b524-b26293a04778'),(18,'2022-11-25 03:10:09',1,7,3,2,'f4d4a92c-4560-4050-829a-2d3979ccbb75'),(19,'2022-11-25 03:10:09',1,8,3,2,'688906e2-f3fc-455e-8345-3ef31aeded43'),(20,'2022-11-25 03:10:10',1,9,3,2,'9053e4a4-e423-4277-8441-8256ad98c967'),(21,'2022-11-25 03:10:10',1,10,3,2,'66e5414b-218c-4d93-b9d6-1c90eb7482de'),(22,'2022-11-25 03:10:16',-1,9,3,1,'f130b49e-61b9-4a92-bfaa-31dcc59e1fc9'),(23,'2022-11-25 03:10:17',-1,8,3,1,'b48323e1-dee2-42d3-807a-7260b608a8b9'),(24,'2022-11-25 03:10:18',-1,7,3,1,'cc4e9836-4023-4e47-b049-f8202bcb78c9'),(25,'2022-11-25 03:10:18',-1,6,3,1,'4445427b-5e99-4612-9fee-b2a141479e6a'),(26,'2022-11-25 03:10:19',-1,5,3,1,'e4c21787-1864-428f-8f6a-14bb006001a1'),(27,'2022-11-25 03:10:19',-1,4,3,1,'0c25c43f-c12e-463c-a117-5a99232624e9'),(28,'2022-11-25 03:10:20',-1,3,3,1,'8786f7d8-0bbe-4077-a72e-3774dd4d5d72'),(29,'2022-11-25 05:09:31',-1,9999,4,1,'141b9754-a657-4eca-949e-7d43bc74bfb3'),(30,'2022-11-25 05:09:34',-1,9998,4,1,'ca949951-86f8-4d2f-a67f-8e9cec3f66d6'),(31,'2022-11-25 05:09:34',-1,9997,4,1,'b505a508-f22b-4849-bebb-c7ee2274b6c4'),(32,'2022-11-25 05:09:35',-1,9997,4,1,'66408bfd-fb18-4bb8-a809-79a4cf37be88'),(33,'2022-11-25 05:09:37',-1,9996,4,1,'8b9f2002-1cbf-4091-a7cd-822af38d559d'),(34,'2022-11-25 06:53:38',-1,9999,14,1,'8dfca56d-2b73-4482-9967-f2f97d532d44'),(35,'2022-11-25 06:53:40',-1,9998,14,1,'32130d3c-bb1f-4017-a9c7-bc73ffe327ee');
/*!40000 ALTER TABLE `movimiento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persona`
--

DROP TABLE IF EXISTS `persona`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `persona` (
  `pk_persona` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `edad` int(11) NOT NULL,
  `identificacion` varchar(45) NOT NULL,
  `direccion` varchar(45) NOT NULL,
  `telefono` varchar(45) NOT NULL,
  `fk_genero` bigint(20) NOT NULL,
  PRIMARY KEY (`pk_persona`),
  KEY `fk_TPERSONA_CGENERO1_idx` (`fk_genero`),
  CONSTRAINT `fk_TPERSONA_CGENERO1` FOREIGN KEY (`fk_genero`) REFERENCES `genero` (`pk_genero`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persona`
--

LOCK TABLES `persona` WRITE;
/*!40000 ALTER TABLE `persona` DISABLE KEYS */;
INSERT INTO `persona` VALUES (2,'12224',12,'S','V','F22DF',1),(3,'AXCVB',12,'S','V','F22DF',1),(4,'12226',12,'S','V','F22DF',1),(5,'12227',12,'S','V','F22DF',1),(6,'12228',12,'S','V','F22DF',1),(7,'1222',12,'S','V','F22DF',1),(8,'1222',12,'S','V','F22DF',1),(9,'1222',12,'S','V','F22DF',1),(10,'AXCVB',12,'S','V','F22DF',1),(12,'AXCVB',12,'S','V','F22DF',1),(13,'AXCVB',12,'S','V','F22DF',1),(14,'AXCVB',12,'S','V','F22DF',1),(15,'AXCVB',12,'S','V','F22DF',1),(16,'AXCVB',12,'S','V','F22DF',1),(17,'AXCVB',12,'S','V','F22DF',1);
/*!40000 ALTER TABLE `persona` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_cuenta`
--

DROP TABLE IF EXISTS `tipo_cuenta`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_cuenta` (
  `pk_tipo_cuenta` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`pk_tipo_cuenta`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_cuenta`
--

LOCK TABLES `tipo_cuenta` WRITE;
/*!40000 ALTER TABLE `tipo_cuenta` DISABLE KEYS */;
INSERT INTO `tipo_cuenta` VALUES (1,'AHORRO'),(2,'CORRIENTE');
/*!40000 ALTER TABLE `tipo_cuenta` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tipo_movimiento`
--

DROP TABLE IF EXISTS `tipo_movimiento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `tipo_movimiento` (
  `pk_tipo_movimiento` bigint(20) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  PRIMARY KEY (`pk_tipo_movimiento`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tipo_movimiento`
--

LOCK TABLES `tipo_movimiento` WRITE;
/*!40000 ALTER TABLE `tipo_movimiento` DISABLE KEYS */;
INSERT INTO `tipo_movimiento` VALUES (1,'DEBITO'),(2,'CREDITO');
/*!40000 ALTER TABLE `tipo_movimiento` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-11-28  8:06:38

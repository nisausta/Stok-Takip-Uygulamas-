CREATE DATABASE  IF NOT EXISTS `stok_takip` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci */;
USE `stok_takip`;
-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: stok_takip
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.28-MariaDB

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
-- Table structure for table `urunler`
--

DROP TABLE IF EXISTS `urunler`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `urunler` (
  `urun_no` int(11) NOT NULL AUTO_INCREMENT,
  `urun_adi` varchar(45) DEFAULT NULL,
  `urun_adet` int(11) DEFAULT NULL,
  `urun_kategori` varchar(45) DEFAULT NULL,
  `urun_beden` varchar(45) DEFAULT NULL,
  `urun_renk` varchar(45) DEFAULT NULL,
  `urun_fiyat` float DEFAULT NULL,
  PRIMARY KEY (`urun_no`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `urunler`
--

LOCK TABLES `urunler` WRITE;
/*!40000 ALTER TABLE `urunler` DISABLE KEYS */;
INSERT INTO `urunler` VALUES (1,'Pantolon',100,'Erkek','M','Mavi',629.99),(2,'Ceket',15,'Kadın','S','Siyah',1299.99),(3,'Mont',56,'Erkek','L','Beyaz',1299.99),(4,'Pijama',78,'Kadın','XL','Pembe',279.99),(5,'Etek',26,'Kadın','S','Kırmızı',159.99),(6,'Şort',200,'Çocuk','XS','Turuncu',254.9),(7,'Pijama',79,'Kadın','XL','Pembe',289.99),(8,'Gömlek',256,'Erkek','M','Lacivert',356.99),(9,'Body',45,'Çocuk','XS','Mor',74.9),(10,'Hırka',257,'Kadın','L','Yeşil',399.9),(11,'Kaban',55,'Kadın','L','Gri',459.99),(12,'Elbise',82,'Çocuk','XS','Pembe',119.9),(13,'Pantolon',150,'Kadın','S','Siyah',629.99),(14,'Pantolon',147,'Kadın','M','Siyah',629.99),(15,'Etek',14,'Kadın','XS','Pembe',179.99),(16,'Gömlek',142,'Erkek','L','Turuncu',356.99),(17,'Elbise',74,'Kadın','XL','Pembe',119.9),(18,'Şort',206,'Çocuk','XS','Mavi',254.9),(19,'Kaban',45,'Kadın','L','Siyah',479.99),(20,'Pijama',47,'Kadın','L','Mor',279.99),(21,'Mont',65,'Kadın','M','Siyah',1299.99),(22,'Mont',45,'Kadın','S','Siyah',1299.99),(23,'Hırka',251,'Kadın','L','Kahverengi',399.9),(24,'Kaban',80,'Kadın','M','Kahverengi',1459.99);
/*!40000 ALTER TABLE `urunler` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `urunler_yetkililer`
--

DROP TABLE IF EXISTS `urunler_yetkililer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `urunler_yetkililer` (
  `urun_no` int(11) NOT NULL,
  `yetkili_no` int(11) NOT NULL,
  PRIMARY KEY (`urun_no`,`yetkili_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `urunler_yetkililer`
--

LOCK TABLES `urunler_yetkililer` WRITE;
/*!40000 ALTER TABLE `urunler_yetkililer` DISABLE KEYS */;
/*!40000 ALTER TABLE `urunler_yetkililer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `yetkililer`
--

DROP TABLE IF EXISTS `yetkililer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `yetkililer` (
  `yetkili_no` int(11) NOT NULL,
  `yetkili_adsoyad` varchar(45) DEFAULT NULL,
  `yetkili_parola` varchar(45) NOT NULL,
  PRIMARY KEY (`yetkili_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_turkish_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `yetkililer`
--

LOCK TABLES `yetkililer` WRITE;
/*!40000 ALTER TABLE `yetkililer` DISABLE KEYS */;
INSERT INTO `yetkililer` VALUES (12345,'Nisa Usta','12345'),(23456,'Şimal Bülbül','12345'),(34567,'Eray Güngör','12345'),(45678,'Öznur Ayazoğlu','12345'),(56789,'Rıdvan Beyiş','12345'),(67890,'Ceydanur Özgür','12345');
/*!40000 ALTER TABLE `yetkililer` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-12-27  1:47:11

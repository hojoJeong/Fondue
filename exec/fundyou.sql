-- MySQL dump 10.19  Distrib 10.3.37-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: fundyou
-- ------------------------------------------------------
-- Server version	10.3.37-MariaDB-0ubuntu0.20.04.1

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Sequence structure for `hibernate_sequence`
--

DROP SEQUENCE IF EXISTS `hibernate_sequence`;
CREATE SEQUENCE `hibernate_sequence` start with 1 minvalue 1 maxvalue 9223372036854775806 increment by 1 cache 1000 nocycle ENGINE=InnoDB;
SELECT SETVAL(`hibernate_sequence`, 1001, 0);

--
-- Table structure for table `ar_image`
--

DROP TABLE IF EXISTS `ar_image`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ar_image` (
  `arimage_id` bigint(20) NOT NULL,
  `funding_item_id` bigint(20) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`arimage_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ar_image`
--

LOCK TABLES `ar_image` WRITE;
/*!40000 ALTER TABLE `ar_image` DISABLE KEYS */;
/*!40000 ALTER TABLE `ar_image` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ar_model`
--

DROP TABLE IF EXISTS `ar_model`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ar_model` (
  `armodel_id` bigint(20) NOT NULL,
  `item_id` bigint(20) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`armodel_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ar_model`
--

LOCK TABLES `ar_model` WRITE;
/*!40000 ALTER TABLE `ar_model` DISABLE KEYS */;
/*!40000 ALTER TABLE `ar_model` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cart`
--

DROP TABLE IF EXISTS `cart`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cart` (
  `cart_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `item_id` bigint(20) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `FK9fhia6b3ekuddn6pkxlsks7rr` (`item_id`),
  KEY `FKix170nytunweovf2v9137mx2o` (`member_id`),
  CONSTRAINT `FK9fhia6b3ekuddn6pkxlsks7rr` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`),
  CONSTRAINT `FKix170nytunweovf2v9137mx2o` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cart`
--

LOCK TABLES `cart` WRITE;
/*!40000 ALTER TABLE `cart` DISABLE KEYS */;
INSERT INTO `cart` VALUES (1,1,100,1);
/*!40000 ALTER TABLE `cart` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `category` (
  `category_id` bigint(20) NOT NULL,
  `category_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category` DISABLE KEYS */;
INSERT INTO `category` VALUES (1,'인테리어'),(2,'디지털/가전'),(3,'주방용품'),(4,'기타');
/*!40000 ALTER TABLE `category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `description`
--

DROP TABLE IF EXISTS `description`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `description` (
  `description_id` bigint(20) NOT NULL,
  `content` varchar(255) DEFAULT NULL,
  `item_type` varchar(255) DEFAULT NULL,
  `item_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`description_id`),
  KEY `FKeywqbt0xtuy46pp0bc7gb950v` (`item_id`),
  CONSTRAINT `FKeywqbt0xtuy46pp0bc7gb950v` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `description`
--

LOCK TABLES `description` WRITE;
/*!40000 ALTER TABLE `description` DISABLE KEYS */;
INSERT INTO `description` VALUES (1,' AX060B510RPD ','모델명',5),(2,'360 X 783 X 318 mm','크기(mm)',5),(3,'2021년 12월','출시년월',5),(4,'60m^2 ','사용 면적',5),(6,' AX100N4020WD ','모델명',10),(7,'720 X 620 X 225 mm','크기(mm)',10),(8,'2018년 2월','출시년월',10),(9,'100m^2 ','사용 면적',10),(11,' AX100N4020WD ','모델명',15),(12,'380 X 1150 X 406 mm','크기(mm)',15),(13,'2021년 11월','출시년월',15),(14,'123m^2 ','사용 면적',15),(16,' VS20B956D5U ','모델명',20),(17,'300 X 755 X 300 mm','크기(mm)',20),(18,'2022년 2월','출시년월',20),(19,'220W','충전시간',20),(21,' VC33M2110LP ','모델명',25),(22,'272 X 243 X 398 mm','크기(mm)',25),(23,'2017년 3월','출시년월',25),(24,'1.5L','먼지용량',25),(26,' VR30T85513B ','모델명',30),(27,'432 X 406 X 578 mm','크기(mm)',30),(28,'2017년 3월','출시년월',30),(29,'2.5L','먼지봉투용량',30),(31,' VR50T95936W ','모델명',35),(32,'305 X 544 X 450 mm','크기(mm)',35),(33,'2022년 2월','출시년월',35),(34,'2.0L','먼지봉투용량',35),(36,' VS20B957G5C ','모델명',40),(37,'250 X 930 X 202 mm','크기(mm)',40),(38,'2022년 2월','출시년월',40),(39,'1.2L','먼지봉투용량',40),(41,' VR30T85513P ','모델명',45),(42,'432 X 406 X 578 mm','크기(mm)',45),(43,'2022년 2월','출시년월',45),(44,'2.5L','먼지봉투용량',45),(46,' MG23A5379CF ','모델명',50),(47,'489 X 275 X 381 mm','크기(mm)',50),(48,'2021년 3월','출시년월',50),(49,'23L','용량',50),(51,' VS15A680AFN','모델명',55),(52,'250 X 1143 X 194 mm','크기(mm)',55),(53,'2021년 6월','출시년월',55),(54,'0.3L','먼지용량',55),(56,' CRS25T9500P ','모델명',60),(57,'367 X 440 X 396 mm','크기(mm)',60),(58,'2020년 10월','출시년월',60),(59,'25L','용량',60),(61,' CRS25T950007','모델명',65),(62,'367 X 440 X 396 mm','크기(mm)',65),(63,'2020년 10월','출시년월',65),(64,'25L','용량',65),(66,' VR010L','모델명',70),(67,'240 X 330 X 280 mm','크기(mm)',70),(68,'4.3kg','무게',70),(69,'10L','용량',70),(71,'W3100xD1650xH970mm','크기',75),(72,'미스트그레이','색상',75),(73,'마이크로화이바솜, 35kg고탄성스펀지','내장재',75),(74,'이태리 통가죽','외장재',75),(76,'W3100xD1650xH970mm','크기',80),(77,'라이트그레이','색상',80),(78,'아쿠아텍스 패브릭','자재',80),(79,'중국','원산지',80),(81,'W2750xD900xH940mm','크기',85),(82,'라이트그레이','색상',85),(83,'아쿠아텍스 패브릭','자재',85),(84,'중국','원산지',85),(86,'W2300xD910xH740mm','크기',90),(87,'브라운','색상',90),(88,'천연 탑 그레인 가죽','소재',90),(89,'미국','원산지',90),(91,'W920xD930xH880mm','크기',95),(92,'핑크','색상',95),(93,'부클 원단, 마이크로화이버','소재',95),(94,'중국','제조국',95),(96,'W920xD930xH880mm','크기',100),(97,'핑크','색상',100),(98,'부클 원단, 마이크로화이버','소재',100),(99,'중국','제조국',100),(101,'W1900xD950xH400mm','크기',105),(102,'핑크','색상',105),(103,'폴란드 정품 조야 원단','소재',105),(104,'중국','제조국',105),(106,'W100xD200xH49cm','크기',110),(107,'화이트','색상',110),(108,'독립스프링+PU폼','내장재',110),(109,'천연 원목','다릿발',110),(111,'W1160xD2180xH1010mm','크기',115),(112,'내추럴','색상',115),(113,'PB,MDF','소재',115),(114,'대한민국','원산지',115),(116,'W1200xD2177xH1045mm','크기',120),(117,'화이트','색상',120),(118,'PB,MDF,PVC','소재',120),(119,'대한민국','원산지',120),(121,'W117xD213.2xH95cm','크기',125),(122,'충전식 핀조명, USB 2포트','침대헤드',125),(123,'PB','소재',125),(124,'콩코드 메모리폼 오리지널 매트리스 SS','구성',125),(126,'W1533xD2160xH955mm','크기',130),(127,'메이플, 브라운, 화이트','색상',130),(128,'PB, MDF','소재',130),(129,'대한민국','원산지',130),(131,'W900xD2030xH800mm','크기',135),(132,'오크','색상',135),(133,'PB, 고급목재합판','소재',135),(134,'대한민국','원산지',135),(136,'W1510xD2160xH1050mm','크기',140),(137,'초코브라운 / 다크그레이 / 오크베이지 / 화이트그레이','색상',140),(138,'스프루스 원목, 도트원단, 스틸','소재',140),(139,'대한민국','원산지',140),(146,'26.2x45.3x52.7cm','크기',150),(147,'폴리에틸렌','소재',150),(148,'2kg','중량',150),(149,'6h','지속시간',150),(151,'35x24x32cm','크기',155),(152,'폴리에틸렌','소재',155),(153,'1.4kg','중량',155),(154,'6h','지속시간',155),(156,'다크그린','색상',160),(157,'CAST Aluminium + Power coated','소재',160),(158,'지름 46 x 높이 55 cm','크기',160),(159,'네덜란드','원산지',160),(161,'아이보리','색상',165),(162,'패브릭, 스펀지','소재',165),(163,'3400x1200x780mm','크기',165),(164,'대한민국','제조국',165),(166,'브라운','색상',170),(167,'패브릭','소재',170),(168,'W200xD120xH86cm','크기',170),(169,'이탈리아','원산지',170),(171,'샌드베이지','색상',175),(172,'강화유리섬유, 친환경 분체도장','소재',175),(173,'W960xH1865xT70mm','크기',175),(174,'대한민국','원산지',175),(176,'아크릴','소재',179),(177,'65x150mm','크기',179),(178,'대한민국','원산지',179),(180,'벨기에 심포니밀즈','원단',184),(181,'폴리에스테르','커버소재',184),(182,'W360xD360xH50','크기',184),(183,'1.5kg','무게',184),(185,'지름 10cm/ 높이 13cm/ 손잡이 6cm','크기',189),(186,'로즈골드','컬러',189),(187,'copper 100%','소재',189),(188,'INDIA','제조사',189),(190,'35.5x25x2cm','크기',194),(191,'0.8kg','무게',194),(192,'목재','재질',194),(193,'대한민국','제조국',194),(195,'지름 8cm x 높이 7cm','컵 크기',199),(196,'지름 14.5cm x 높이 2cm','컵 받침',199),(197,'유기','재질',199),(198,'대한민국','제조국',199),(200,'133mm*41mm','국그릇',204),(201,'100mm*35mm','찬기 소 2개',204),(202,'128mm*35mm','찬기 중 1개',204),(203,'205mm*40mm / 215mm*12mm','수저세트',204),(205,'HAA01','상품코드',209),(206,'스테인레스 스틸,탄화목,WPC볼스터','소재',209),(207,'중식도+식도+과도','구성',209),(208,'대한민국','제조국',209),(210,'유기','소재',214),(211,'전체지름:24cm, 높이:5cm','사이즈[소]',214),(212,'전체지름:27.5cm, 높이:5.5cm','사이즈[대]',214),(213,'대한민국','원산지',214),(215,'원키친101협력사','상품코드',219),(216,'지름 105 x h250(mm)','사이즈',219),(217,'18-8 스테인레스 스틸, 내열유리,폴리프로필렌PET','소재',219),(218,'중국','원산지',219),(220,'33x45cm','사각 매트',224),(221,'38cm','원형 매트',224),(222,'합성피혁','소재',224),(223,'프랑스','원산지',224),(225,'21cm','접시',229),(226,'8.5xH9cm','머그',229),(227,'Fine Bone China','소재',229),(228,'한국','원산지',229),(230,'AAM23 LAZ','상품코드',234),(231,'스테인레스 스틸, 플라스틱','소재',234),(232,'지름 6cm, 높이 21cm','사이즈',234),(233,'중국','원산지',234),(235,'WH211223039','상품코드',239),(236,'Fine Bone China','소재',239),(237,'W 250mm X H 130mm , 1200ml','사이즈',239),(238,'한국','원산지',239),(240,'HRS27','상품코드',244),(241,'월넛,스테인레스','소재',244),(242,'45.6 x 8.8 x 1.8','사이즈',244),(243,'중국','제조국',244),(245,'MP1504','상품코드',249),(246,'법랑','소재',249),(247,'KAPKA','제조사',249),(248,'터키','원산지',249),(250,'알루미늄 주물, 베이크라이트, 유리제, 나무제, 실리콘 외','재질',254),(251,'데이지냄비: 18cm 편수, 20cm 양수','구성',254),(252,'데이지 24cm 프라이팬, 24cm궁중팬','구성',254),(253,'대한민국','제조국',254),(255,'파머스그린','색상',259),(256,'스테인레스 스틸, 고무제, 무착색유리제','재질',259),(257,'1년','품질보증기간',259),(258,'중국','제조국',259),(260,'1000458605977','상품번호',264),(261,'금속제(알루미늄), 코팅제(내면-불소수지, 외면-세라믹코팅)','재질',264),(262,'지름: 28.6cm','프라이팬',264),(263,'지름: 28.6cm','궁중팬',264),(265,'1000056367223','상품번호',269),(266,'불소수지','재질',269),(267,'24cm : 지름 24cm,높이6cm / 28cm : 지름 28cm,높이6cm','크기',269),(268,'프랑스','제조국',269),(270,'1000428142048','상품번호',274),(271,'스테인리스','재질',274),(272,'40cm x 32cm x 4.7cm','크기',274),(273,'중국','제조국',274),(275,'1000528568537','상품번호',279),(276,'알루미늄 합금','재질',279),(277,'400x400x4(H)mm','규격',279),(278,'한국','제조국',279),(280,'1000043409870','상품번호',284),(281,'유기','재질',284),(282,'23.8x5.4cm','규격',284),(283,'한국','제조국',284),(285,'1000391106113','상품번호',289),(286,'알루미늄','재질',289),(287,'30x24cm','크기',289),(288,'한국','제조국',289),(290,'1000391106113','상품번호',294),(291,'무쇠주물','재질',294),(292,'18x13cm','사이즈',294),(293,'2.0L','용량',294),(295,'10~20일','물주기',299),(296,'1000~2500)','물양(ml)',299),(297,'통풍필요함','특징',299),(298,'추위에약함','특징',299),(300,'1000035392385','상품번호',304),(301,'권장사용대상: 8kg 미만','용량',304),(302,'1200W','소비전력',304),(303,'457x681x526mm','크기',304),(305,'미니캣타워 투명해먹형','모델',309),(306,'614x590x1118.5mm','사이즈',309),(307,'PB+LPM+ABS+아크릴','소재',309),(308,'한국','제조국',309),(310,'히말라야 핑크솔트&화이트솔트, 히노키정유 100%','소재',314),(311,'OKAYAMA, JP','원산지',314),(312,'BMD.Co,.It.','제조사',314),(313,'300g','용량',314),(315,'1000529603513','상품번호',319),(316,'SHELL(겉감): 95% COTTON 5% POLYURETHANE LINING(안감): 100% COTTON','소재',319),(317,'아이보리','색상',319),(318,'중국','제조국',319),(320,'1000532136634','상품번호',324),(321,'SHELL(겉감): 95% COTTON 5% POLYURETHANE','소재',324),(322,'스트라이프','색상',324),(323,'중국','제조국',324),(325,'1000506766905','상품번호',329),(326,'브라운','색상',329),(327,'겉감-폴리에스터','소재',329),(328,'베트남','제조국',329),(330,'106x53x100cm','사이즈',334),(331,'11kg','무게',334),(332,'스틸, 폴리에스테르','재질',334),(333,'중국','원산지',334),(336,'품명 및 모델명','GANSO 충전식 무선 LED 스탠드',338),(337,'원산지','중국',338);
/*!40000 ALTER TABLE `description` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `firebase_token`
--

DROP TABLE IF EXISTS `firebase_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `firebase_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `member_id` bigint(20) DEFAULT NULL,
  `status` bit(1) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `firebase_token`
--

LOCK TABLES `firebase_token` WRITE;
/*!40000 ALTER TABLE `firebase_token` DISABLE KEYS */;
INSERT INTO `firebase_token` VALUES (1,1,'','dEhCy1nnRZ67cBdFGgHTq_:APA91bEzEKoDS_u89RROOy3hWXJ1JxDPzjMs9HApj_WYt82zwCjMUFB1aOJ226LXvVbgPk8-9NPC3s7LRmNEZOLRFrLE9lI5Q0p2AfHyVPSPyUv5lNPdLC1tPaMtbBLVQGeeaVqPu35C'),(2,4,'\0','eU-mIy6JSBW1KR8NRSM1LU:APA91bFDMoed2H_68sXS-Az9S2VsU9ZD771blZWHn52ac7H543xDdhOHssMNT79aujJO-2CNqqtsrGBZ861QifJa27L-k9PZyX6aAxZxd9n7k5ByZnQvWOZnZd5m7aOOEg3eBoeCPT3J'),(3,2,'\0','cJ00wkohTOSBoVUu_wRdDW:APA91bHq0w45G-RfA0lJ5WnNYq9aR5vbaPDXjN5q_ajt957t8gfZB6i3XvDiKvMmV6-EMOaYi8JcowXf7lt3GrgfAqUISNXYlJPqMOHp06L2uIB3AEQzaflmbzENc_EZ2yhnucWxTXYW');
/*!40000 ALTER TABLE `firebase_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funding`
--

DROP TABLE IF EXISTS `funding`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funding` (
  `funding_id` bigint(20) NOT NULL,
  `end_date` bigint(20) DEFAULT NULL,
  `funding_name` varchar(255) DEFAULT NULL,
  `funding_status` bit(1) DEFAULT NULL,
  `start_date` bigint(20) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`funding_id`),
  KEY `FK5cxch4qfn9ynsvod79uyulcvj` (`member_id`),
  CONSTRAINT `FK5cxch4qfn9ynsvod79uyulcvj` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funding`
--

LOCK TABLES `funding` WRITE;
/*!40000 ALTER TABLE `funding` DISABLE KEYS */;
/*!40000 ALTER TABLE `funding` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funding_item`
--

DROP TABLE IF EXISTS `funding_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funding_item` (
  `funding_item_id` bigint(20) NOT NULL,
  `count` int(11) NOT NULL,
  `current_funding_price` int(11) NOT NULL,
  `funding_item_status` bit(1) NOT NULL,
  `item_total_price` int(11) NOT NULL,
  `participants_count` int(11) NOT NULL,
  `funding_id` bigint(20) DEFAULT NULL,
  `item_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`funding_item_id`),
  KEY `FK28yeesgxgjgkaev7h3e5eov52` (`funding_id`),
  KEY `FKrc3x08wxl747amgk6ukb0niju` (`item_id`),
  CONSTRAINT `FK28yeesgxgjgkaev7h3e5eov52` FOREIGN KEY (`funding_id`) REFERENCES `funding` (`funding_id`),
  CONSTRAINT `FKrc3x08wxl747amgk6ukb0niju` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funding_item`
--

LOCK TABLES `funding_item` WRITE;
/*!40000 ALTER TABLE `funding_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `funding_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `funding_item_member`
--

DROP TABLE IF EXISTS `funding_item_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `funding_item_member` (
  `funding_item_member_id` bigint(20) NOT NULL,
  `funding_item_price` int(11) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `funding_item_id` bigint(20) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`funding_item_member_id`),
  KEY `FK9whekjuq90309yrgnta9divrd` (`funding_item_id`),
  KEY `FK8q4s9mi1l157n97u3ots2k2rw` (`member_id`),
  CONSTRAINT `FK8q4s9mi1l157n97u3ots2k2rw` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FK9whekjuq90309yrgnta9divrd` FOREIGN KEY (`funding_item_id`) REFERENCES `funding_item` (`funding_item_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `funding_item_member`
--

LOCK TABLES `funding_item_member` WRITE;
/*!40000 ALTER TABLE `funding_item_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `funding_item_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `invited_member`
--

DROP TABLE IF EXISTS `invited_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `invited_member` (
  `invited_member_id` bigint(20) NOT NULL,
  `funding_id` bigint(20) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`invited_member_id`),
  KEY `FKe4j0nvfq676fi4f27091e1jdu` (`funding_id`),
  KEY `FKa2s8ox1deej43vcc2eli0gc3i` (`member_id`),
  CONSTRAINT `FKa2s8ox1deej43vcc2eli0gc3i` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`),
  CONSTRAINT `FKe4j0nvfq676fi4f27091e1jdu` FOREIGN KEY (`funding_id`) REFERENCES `funding` (`funding_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `invited_member`
--

LOCK TABLES `invited_member` WRITE;
/*!40000 ALTER TABLE `invited_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `invited_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `item`
--

DROP TABLE IF EXISTS `item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `item` (
  `item_id` bigint(20) NOT NULL,
  `brand` varchar(255) DEFAULT NULL,
  `description_img` varchar(5000) NOT NULL,
  `image` varchar(5000) NOT NULL,
  `is_ar` bit(1) DEFAULT NULL,
  `is_favorite` tinyint(1) DEFAULT 0,
  `price` int(11) DEFAULT NULL,
  `selling_count` int(11) DEFAULT 0,
  `title` varchar(255) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`item_id`),
  KEY `FK2n9w8d0dp4bsfra9dcg0046l4` (`category_id`),
  CONSTRAINT `FK2n9w8d0dp4bsfra9dcg0046l4` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `item`
--

LOCK TABLES `item` WRITE;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` VALUES (5,'삼성','https://d15zs6bxpcjiwz.cloudfront.net/Home/Air-Purifiers/AX060B510RPD.jpg','https://sitem.ssgcdn.com/95/11/44/item/1000287441195_i1_1100.jpg https://sitem.ssgcdn.com/95/11/44/item/1000287441195_i2_550.jpg https://sitem.ssgcdn.com/95/11/44/item/1000287441195_i3_550.jpg https://sitem.ssgcdn.com/95/11/44/item/1000287441195_i4_550.jpg https://sitem.ssgcdn.com/95/11/44/item/1000287441195_i5_550.jpg','\0',0,493000,0,'상성 공기청정기 블루스카이 [AX060B510RPD]',2),(10,'삼성','https://ai.esmplus.com/happycall07/detail/2021/AX100N4020WD-s1.jpg','https://sitem.ssgcdn.com/14/30/87/item/1000297873014_i1_1100.jpg','\0',0,493000,0,'상성 공기청정기 블루스카이 [AX100N4020WD]',2),(15,'삼성','https://d15zs6bxpcjiwz.cloudfront.net/Home/Air-Purifiers/AX123B850SBD.jpg','https://sitem.ssgcdn.com/46/16/16/item/1000026161646_i1_550.jpg','\0',0,1160000,0,'상성 공기청정기 큐브 [AX100N4020WD]',2),(20,'삼성','https://img.shinsegaetvshopping.com/htmleditor/227/VS20B956D5U1650343840312.jpg','https://img.shinsegaetvshopping.com/htmleditor/227/VS20B956D5U1650343840312.jpg https://sitem.ssgcdn.com/22/70/47/item/1000528477022_i2_550.jpg','\0',0,764100,0,'비스포크 제트 청소기 220W 모닝 블루 VS20B956D5U',2),(25,'삼성','https://img.shinsegaetvshopping.com/htmleditor/169/VC33M2110LP1663809706032.jpg','https://sitem.ssgcdn.com/37/52/45/item/1000517455237_i1_1100.jpg','\0',0,96000,0,'삼성 싸이클론 엉킴방지 진공청소기 VC33M2110LP',2),(30,'삼성','https://d15zs6bxpcjiwz.cloudfront.net/Home/Vacuums/VR30T85513B.jpg','https://sitem.ssgcdn.com/04/91/75/item/1000529759104_i1_1100.jpg','\0',0,549000,0,'삼성 비스포크 제트봇 로봇청소기 VR30T85513B',2),(35,'삼성','https://d15zs6bxpcjiwz.cloudfront.net/Home/Vacuums/VR50T95936W.jpg','https://sitem.ssgcdn.com/38/10/76/item/1000529761038_i1_1100.jpg','\0',0,1017000,0,'삼성 비스포크 제트봇 AI 로봇청소기 VR50T95936W',2),(40,'삼성','https://d15zs6bxpcjiwz.cloudfront.net/Home/Vacuums/VS20B957G5C_spec.jpg',' https://sitem.ssgcdn.com/15/32/12/item/1000412123215_i1_1100.jpg ','\0',0,1297000,0,'삼성 비스포크 제트 무선청소기 220@ [VS20B957G5C] 일체형 청정스테이션',2),(45,'삼성','https://d15zs6bxpcjiwz.cloudfront.net/Home/Vacuums/VR30T85513P.jpg ',' https://sitem.ssgcdn.com/53/47/99/item/1000068994753_i1_550.jpg https://sitem.ssgcdn.com/53/47/99/item/1000068994753_i2_550.jpg https://sitem.ssgcdn.com/53/47/99/item/1000068994753_i3_550.jpg https://sitem.ssgcdn.com/53/47/99/item/1000068994753_i4_550.jpg ','\0',0,575000,0,'삼성 비스포크 제트봇 로봇청소기 VR30T85513P',2),(50,'삼성','https://d15zs6bxpcjiwz.cloudfront.net/Kitchen/Microwaves/MG23A5378CF.jpg ','https://sitem.ssgcdn.com/69/02/79/item/1000068790269_i2_550.jpg https://sitem.ssgcdn.com/69/02/79/item/1000068790269_i3_550.jpg https://sitem.ssgcdn.com/69/02/79/item/1000068790269_i5_550.jpg','\0',0,198000,0,'삼성 비스포크 전자레인지 MG23A5379CF',2),(55,'삼성','https://sstatic.ssgcdn.com/cmpt/edit/202201/27/182022012717431398541587932258_993.jpg','https://sitem.ssgcdn.com/98/43/61/item/1000298614398_i1_1100.jpg','\0',0,345000,0,'삼성 비스포크 슬림 스틱 무선 청소기 VS15A680AFN',2),(60,'삼성','https://ai.esmplus.com/kjretail/samsung/211006/CRS25T9500PSM_spec.jpg','https://sitem.ssgcdn.com/59/78/53/item/1000258537859_i1_1100.jpg','\0',0,669200,0,'삼성전자 비스포크 큐브 냉장고 CRS25T9500P SM 프라임핑크 멀티수납존',2),(65,'삼성','https://oga888.godohosting.com/defo/brand/valutec/15L700.jpg','https://sitem.ssgcdn.com/62/70/50/item/1000264507062_i1_1100.jpg','\0',0,629000,0,'삼성 비스포크 큐브 냉장고 25L 펀 그린 CRS25T950007',2),(70,'삼성','https://oga888.godohosting.com/defo/brand/valutec/10L700.jpg','https://sitem.ssgcdn.com/65/12/83/item/1000049831265_i1_1100.jpg','\0',0,103040,0,'벨류텍 10리터 냉온장고 VR010L',2),(75,'막스앤','https://www.maxnmall.com/01_in_maxn_group/23_handmade.jpg','https://maxn.co.kr/web/product/big/201904/39cb6d02feef661ea865bcca1be97c78.jpg https://maxn.co.kr/web/product/extra/big/20200403/17e13c3b595f395c41042ef8323c8f26.jpg https://maxn.co.kr/web/product/extra/big/20200403/9ee9e29ff67de2e191c214836b45836b.jpg https://maxn.co.kr/web/product/extra/big/20200403/acfd4e976fecf3db4f20761e92d9831b.jpg https://maxn.co.kr/web/product/extra/big/20200403/9ee9e29ff67de2e191c214836b45836b.jpg','\0',0,3280000,0,'막스앤 카르멘 4인 카우치형 이태리 통가죽 소파',1),(80,'웰퍼니쳐','http://gi.esmplus.com/btbone/html/sofa/aiden/aiden1.jpg','https://image.daisomall.co.kr/data/daiso_data/images/addimg/00/05/34/32/52/m_02943699_add.gif https://image.daisomall.co.kr/data/daiso_data/images/addimg/00/05/34/32/52/m_02943700_add.gif https://image.daisomall.co.kr/data/daiso_data/images/addimg/00/05/34/32/52/m_02943701_add.gif ','\0',0,435000,0,'에이든 패브릭 3인용 소파',1),(85,'로체스터','https://ssl.pstatic.net/imgshopping/spec/267/74/33/26774337523_0_20210415175321.jpg','https://shopping-phinf.pstatic.net/main_2677433/26774337523.20210423162405.jpg?type=f640 https://shopping-phinf.pstatic.net/main_2677433/26774337523.1.20210423162405.jpg?type=f640 https://shopping-phinf.pstatic.net/main_2677433/26774337523.2.20210423162406.jpg?type=f640 ','\0',0,489000,0,'로체스터 하프문 4인용 아쿠아텍스 클린 소파',1),(90,'MUSE','https://www.mirage.co.kr/images/11810-Sofa_190114-3.jpg','https://www.mirage.co.kr/data/item/1534910430/thumb-850_545x545.jpg https://www.mirage.co.kr/data/item/1534910430/thumb-7001_545x545.jpg https://www.mirage.co.kr/data/item/1534910430/thumb-7002_545x545.jpg https://www.mirage.co.kr/data/item/1534910430/thumb-7003_545x545.jpg https://www.mirage.co.kr/data/item/1534910430/thumb-7009_545x545.jpg https://www.mirage.co.kr/data/item/1534910430/thumb-7007_545x545.jpg https://www.mirage.co.kr/data/item/1534910430/thumb-7006_545x545.jpg ','\0',0,3850000,0,'11810 Leather Sofa',1),(95,'로체스터','https://shopping-phinf.pstatic.net/main_3371635/33716350618.jpg','https://shopping-phinf.pstatic.net/main_3371635/33716350618.20220727103905.jpg?type=f640 https://shopping-phinf.pstatic.net/main_3371635/33716350618.1.20220727103906.jpg?type=f640 https://shopping-phinf.pstatic.net/main_3371635/33716350618.2.20220727103906.jpg?type=f640 https://shopping-phinf.pstatic.net/main_3371635/33716350618.3.20220727103907.jpg?type=f640 https://shopping-phinf.pstatic.net/main_3371635/33716350618.4.20220727103907.jpg?type=f640 ','\0',0,198000,0,'바비 양털 부클레 1인용 소파 ',1),(100,'웰퍼니처','https://ssl.pstatic.net/imgshopping/spec/341/45/35/34145356619_0_20220818154744.jpg','https://shopping-phinf.pstatic.net/main_3414535/34145356619.20220818154753.jpg?type=f640 https://shopping-phinf.pstatic.net/main_3414535/34145356619.1.20220818154754.jpg?type=f640 https://shopping-phinf.pstatic.net/main_3414535/34145356619.2.20220818154754.jpg?type=f640 https://shopping-phinf.pstatic.net/main_3414535/34145356619.3.20220818154754.jpg?type=f640 https://shopping-phinf.pstatic.net/main_3414535/34145356619.4.20220818154755.jpg?type=f640 https://shopping-phinf.pstatic.net/main_3414535/34145356619.7.20220818154756.jpg?type=f640 ','',0,539000,0,'모아나 6인용 아쿠아텍스 이지클린 패브릭 소파 ',1),(105,'모던홈즈','https://ssl.pstatic.net/imgshopping/spec/375/46/29/37546297619_0_20230131100613.jpg','https://shopping-phinf.pstatic.net/main_3754629/37546297619.20230202105952.jpg?type=f640 https://shopping-phinf.pstatic.net/main_3754629/37546297619.1.20230202105953.jpg?type=f640 https://shopping-phinf.pstatic.net/main_3754629/37546297619.2.20230202105953.jpg?type=f640 https://shopping-phinf.pstatic.net/main_3754629/37546297619.3.20230202105954.jpg?type=f640 https://shopping-phinf.pstatic.net/main_3754629/37546297619.4.20230202105954.jpg?type=f640 https://shopping-phinf.pstatic.net/main_3754629/37546297619.5.20230202105954.jpg?type=f640 https://shopping-phinf.pstatic.net/main_3754629/37546297619.6.20230202105954.jpg?type=f640 https://shopping-phinf.pstatic.net/main_3754629/37546297619.7.20230202105955.jpg?type=f640 ','\0',0,199000,0,'트레스 모듈형 3인용 패브릭소파 ',1),(110,'한샘','https://ssl.pstatic.net/imgshopping/spec/197/17/92/19717928090_0_20210125110519.jpg','https://shopping-phinf.pstatic.net/main_1971792/19717928090.20210125110409.jpg?type=f640 https://shopping-phinf.pstatic.net/main_1971792/19717928090.1.20210125110409.jpg?type=f640 https://shopping-phinf.pstatic.net/main_1971792/19717928090.2.20210125110409.jpg?type=f640 https://shopping-phinf.pstatic.net/main_1971792/19717928090.3.20210125110409.jpg?type=f640','\0',0,248380,0,'한샘 일체형 침대 S ',1),(115,'동서가수','https://ssl.pstatic.net/imgshopping/spec/334/93/83/33493835618_0_20220714154010.JPG','https://shopping-phinf.pstatic.net/main_3349383/33493835618.20220714154059.jpg?type=f640 https://shopping-phinf.pstatic.net/main_3349383/33493835618.1.20220714154059.jpg?type=f640 https://shopping-phinf.pstatic.net/main_3349383/33493835618.2.20220714154100.jpg?type=f640 https://shopping-phinf.pstatic.net/main_3349383/33493835618.3.20220714154100.jpg?type=f640 https://shopping-phinf.pstatic.net/main_3349383/33493835618.4.20220714154101.jpg?type=f640','\0',0,249000,0,'동서가구 이즈 리베라 수납 침대 SS',1),(120,'이노센트','https://ssl.pstatic.net/imgshopping/spec/280/02/70/28002700522_0_20210715094739.jpg','https://shopping-phinf.pstatic.net/main_2800270/28002700522.20220512164011.jpg?type=f640 https://shopping-phinf.pstatic.net/main_2800270/28002700522.1.20220512164011.jpg?type=f640 https://shopping-phinf.pstatic.net/main_2800270/28002700522.2.20220512164012.jpg?type=f640 https://shopping-phinf.pstatic.net/main_2800270/28002700522.3.20220512164012.jpg?type=f640','\0',0,209000,0,'이노센트 씨엘로 LED 침대 SS',1),(125,'한샘','https://ssl.pstatic.net/imgshopping/spec/245/41/50/24541509522_0_20201021172312.jpg','https://shopping-phinf.pstatic.net/main_2454150/24541509522.20201021165440.jpg?type=f640 https://shopping-phinf.pstatic.net/main_2454150/24541509522.1.20201021165441.jpg?type=f640 https://shopping-phinf.pstatic.net/main_2454150/24541509522.2.20201021165441.jpg?type=f640 https://shopping-phinf.pstatic.net/main_2454150/24541509522.3.20201021165441.jpg?type=f640','\0',0,561000,0,'한샘 아임빅 조명헤드형 수납침대 SS',1),(130,'에몬스','https://ssl.pstatic.net/imgshopping/spec/173/07/09/17307098430_0_20190201111807.jpg','https://shopping-phinf.pstatic.net/main_2454150/24541509522.20201021165440.jpg?type=f640 https://shopping-phinf.pstatic.net/main_2454150/24541509522.1.20201021165441.jpg?type=f640 https://shopping-phinf.pstatic.net/main_2454150/24541509522.2.20201021165441.jpg?type=f640 https://shopping-phinf.pstatic.net/main_2454150/24541509522.3.20201021165441.jpg?type=f640','\0',0,542320,0,'에몬스 클레어 에디션 침대 Q',1),(135,'라자가구','https://red.lotteon.com/_m2_/_rsc_/LI1051428641/?sref=http://inbesco.godohosting.com/RAJA/newdeep/Oak%20900deepH.jpg','https://shopping-phinf.pstatic.net/main_3110455/31104553622.20220228122440.jpg?type=f640','\0',0,254000,0,'900 오크 Deep 시크릿H 멀티수납 침대세트',1),(140,'에몬스홈','https://shopping-phinf.pstatic.net/20220525_21_13/ad1c8021-3f8e-4d75-a86f-a5f54b18028c/1910860333.jpg','https://shopping-phinf.pstatic.net/main_1436605/14366056361.20211026170912.jpg?type=f640','\0',0,1018000,0,'에몬스홈 휴에디션 침대 Q',1),(150,'퀴부','https://pjh5023.cdn-nhncommerce.com/data/goods_desc_images/1000022450/model/1652337925-220425_Rabbit_lamp_large.jpg','https://pjh5023.cdn-nhncommerce.com/data/goods/22/05/19/1000022450/1000022450_detail_075.jpg','\0',0,500000,0,'[퀴부] 이탈리아 디자인브랜드 래빗 램프_Large',1),(155,'퀴부','https://pjh5023.cdn-nhncommerce.com/data/goods_desc_images/1000022433/model/1652334804-Teddygirl.jpg','https://pjh5023.cdn-nhncommerce.com/data/goods/22/05/19/1000022433/1000022433_detail_04.jpg','\0',0,350000,0,'[퀴부] 이탈리아 디자인브랜드 테디걸 램프',1),(160,'POLSPOTTEN','https://www.gettt.com/data/goods_desc_images/1000311901/model/1675327270-4.classic_detail.jpg','https://pjh5023.cdn-nhncommerce.com/data/goods/23/01/04/1000311901/1000311901_detail_063.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/23/01/04/1000311901/1000311901_detail_137.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/23/01/04/1000311901/1000311901_detail_299.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/23/01/04/1000311901/1000311901_detail_361.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/23/01/04/1000311901/1000311901_detail_495.jpg','\0',0,630000,0,'POLSPOTTEN 클래식테이블_다크그린',1),(165,'바이헤이데이','https://pjh5023.cdn-nhncommerce.com/data/goods_desc_images/1000173650/model/1660874964-LINEAR_MAIN01.jpg','https://pjh5023.cdn-nhncommerce.com/data/goods/22/08/33/1000173650/1000173650_detail_029.png https://pjh5023.cdn-nhncommerce.com/data/goods/22/08/33/1000173650/1000173650_detail_111.jpg','\0',0,498000,0,'리니어 소파 카우치',1),(170,'ILVA','https://pjh5023.cdn-nhncommerce.com/data/goods_desc_images/1000314215/model/1675217856-Heim-2.jpg','https://pjh5023.cdn-nhncommerce.com/data/goods/23/01/05/1000314215/1000314215_detail_027.jpg','\0',0,2541500,0,'[ILVA]헤임 2.5인 소파',1),(175,'커넥토리얼','https://www.gettt.com/data/goods_desc_images/1000017179/model/1641484812-%E1%84%8B%E1%85%B0%E1%84%8B%E1%85%B5%E1%84%87%E1%85%B3-%E1%84%86%E1%85%B5%E1%84%85%E1%85%A5-L-%E1%84%89%E1%85%A2%E1%86%AB%E1%84%83%E1%85%B3%E1%84%87%E1%85%A6%E1%84%8B%E1%85%B5%E1%84%8C%E1%85%B5.jpg','https://pjh5023.cdn-nhncommerce.com/data/goods/22/01/01/1000017179/1000017179_detail_074.jpg','\0',0,1180000,0,'[커넥토리얼] 웨이브미러',1),(179,'STUDIO RIPOSO','https://www.gettt.com/data/goods_desc_images/1000017774/model/1642071473-%EC%95%84%ED%81%AC%EB%A6%B4-%ED%94%8C%EB%9D%BC%EC%9B%8C_M.jpg','https://pjh5023.cdn-nhncommerce.com/data/goods/22/01/02/1000017774/1000017774_detail_078.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/22/01/02/1000017774/1000017774_detail_154.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/22/01/02/1000017774/1000017774_detail_297.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/22/01/02/1000017774/1000017774_detail_317.jpg','\0',0,1180000,0,'아크릴 플라워_M',1),(184,'HIBROW','https://pjh5023.cdn-nhncommerce.com/data/goods_desc_images/1000007185/model/1620349973-MILKSOFASTOOL_BL-1.jpg','https://pjh5023.cdn-nhncommerce.com/data/goods/21/05/18/1000007185/1000007185_detail_067.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/21/05/18/1000007185/1000007185_detail_153.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/21/05/18/1000007185/1000007185_detail_236.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/21/05/18/1000007185/1000007185_detail_34.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/21/05/18/1000007185/1000007185_detail_432.jpg','\0',0,53000,0,'밀크 소파 스툴 :블랙',1),(189,'해튼','https://www.gettt.com/data/goods_desc_images/1000276873/model/1671088244-%EC%A0%81%EB%8F%99-%EC%8B%9C%EC%97%90%EB%9D%BC-%EC%84%B8%ED%8A%B8_%EC%83%81%EC%84%B8%ED%8E%98%EC%9D%B4%EC%A7%80.jpg','https://pjh5023.cdn-nhncommerce.com/data/goods/22/12/50/1000276873/1000276873_detail_066.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/22/12/50/1000276873/1000276873_detail_135.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/22/12/50/1000276873/1000276873_detail_260.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/22/12/50/1000276873/1000276873_detail_328.jpg','\0',0,110000,0,'[해튼] 적동 시에라 세트 500ml',3),(194,'헤리터','https://www.gettt.com/data/goods_desc_images/1000017056/model/1644219595-%ED%8C%94%EA%B0%81%EB%8F%84%EB%A7%88%20%EC%84%B8%ED%8A%B8.jpg','https://pjh5023.cdn-nhncommerce.com/data/goods/22/01/01/1000017056/1000017056_detail_094.jpg','\0',0,319000,0,'헤리터 시그니처 팔각도마 2개+프레임',3),(199,'아우릇','https://www.gettt.com/data/goods_desc_images/1000013797/model/1635906129-%E1%84%80%E1%85%A6%E1%86%BA%E1%84%90%E1%85%B3.jpg','https://pjh5023.cdn-nhncommerce.com/data/goods/21/10/42/1000013797/1000013797_detail_01.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/21/10/42/1000013797/1000013797_detail_127.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/21/10/42/1000013797/1000013797_detail_266.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/21/10/42/1000013797/1000013797_detail_37.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/21/10/42/1000013797/1000013797_detail_467.jpg','\0',0,360000,0,'아우릇 유기와인잔(2p)',3),(204,'아우릇','https://www.gettt.com/data/goods_desc_images/1000014091/model/1634894700-%EA%B2%9F%ED%8A%B8.jpg','https://pjh5023.cdn-nhncommerce.com/data/goods/21/10/42/1000014091/1000014091_detail_037.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/21/10/42/1000014091/1000014091_detail_167.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/21/10/42/1000014091/1000014091_detail_2100.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/21/10/42/1000014091/1000014091_detail_383.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/21/10/42/1000014091/1000014091_detail_48.jpg','\0',0,360000,0,'아우릇 베이직 1인반상기세트(10p)',3),(209,'헤리터','https://www.gettt.com/data/goods_desc_images/1000020888/model/1644220049-%ED%97%A4%EB%A6%AC%ED%84%B0%20%EC%B9%BC%202%EC%A2%85%EC%84%B8%ED%8A%B8.jpg','https://pjh5023.cdn-nhncommerce.com/data/goods/22/04/14/1000020888/1000020888_detail_072.jpg','\0',0,228000,0,'헤리터 칼 3종 세트',3),(214,'아우릇','https://www.gettt.com/data/goods_desc_images/1000014024/model/1634885718-%E1%84%83%E1%85%A2%E1%84%8C%E1%85%B5%201.jpg','https://pjh5023.cdn-nhncommerce.com/data/goods/21/10/42/1000014024/1000014024_detail_07.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/21/10/42/1000014024/1000014024_detail_26.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/21/10/42/1000014024/1000014024_detail_393.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/21/10/42/1000014024/1000014024_detail_44.jpg','\0',0,124000,0,'아우릇 유기 파스타볼(2size)',3),(219,'킨토','https://pjh5023.cdn-nhncommerce.com/data/goods_desc_images/1000086059/model/1654738506-c1.jpg','https://pjh5023.cdn-nhncommerce.com/data/goods/22/06/23/1000086059/1000086059_detail_02.jpg','\0',0,83000,0,'킨토 루체 콜드 브루 카라페 !L',3),(224,'PODEVACHE','https://www.gettt.com/data/goods_desc_images/1000168693/model/1660284776-1_%ED%85%8C%EC%9D%B4%EB%B8%94.jpg','https://pjh5023.cdn-nhncommerce.com/data/goods/22/08/32/1000168693/1000168693_detail_063.jpg','\0',0,38000,0,'테이블 메트 집셋',3),(229,'트위그뉴욕','https://alt729.com/web/upload/NNEditor/20210601/ED8FACEAB29FEBAFB8EB82AB26EB8DB0EC9DB4ECA780ECB2B4EC9DB820EB9494ECA080ED8AB8EC84B8ED8AB8.jpg','https://pjh5023.cdn-nhncommerce.com/data/goods/22/03/13/1000020567/1000020567_detail_061.jpg','\0',0,132000,0,'[트위그뉴욕] 포겟미낫&데이지체인 디저트세트',3),(234,'Alessi','https://leeonco.cafe24.com/alessi/alessi-aam23-laz.jpg','https://pjh5023.cdn-nhncommerce.com/data/goods/21/10/40/1000013436/1000013436_detail_051.jpg','\0',0,90000,0,'[Alessi]알레시 알레산드로 엠 와인오프너_라이트 블루/AAM23 LAZ',3),(239,'화이트블룸','https://www.gettt.com/data/goods_desc_images/1000016592/model/1640249416-Origin%20Teapot%20%EC%83%81%ED%8E%98.jpg','https://pjh5023.cdn-nhncommerce.com/data/goods/21/12/51/1000016592/1000016592_detail_015.jpg','\0',0,105000,0,'[화이트블룸] 오리진 티팟',3),(244,'헤리터','https://www.gettt.com/data/goods_desc_images/1000018448/model/1644219486-%ED%96%89%EA%B1%B0%201.jpg','https://pjh5023.cdn-nhncommerce.com/data/goods/22/02/05/1000018448/1000018448_detail_039.jpg','\0',0,85000,0,'헤리터 키네틱 키친행거',3),(249,'헤리터','http://kapkakorea.cafe24.com/web/upload/NNEditor/20210417/ECBA85ECB9B4_EC8AA4EBAAB0EC8390EB9FACEB939CEBB3BC-EC9890EBA19CEC9AB0-EC8381EC84B8.jpg','https://pjh5023.cdn-nhncommerce.com/data/goods/21/09/37/1000012900/1000012900_detail_076.jpg','\0',0,37000,0,'마인드팝 스몰 샐러드볼 옐로',3),(254,'None','https://exit.ohou.se/deac8b56f3a747f79b4c97d56c5650ebcac9a934/nt1.ipdisk.co.kr:80/publist/VOL1/KAUFMANN/KDIC-A6P-2WEB.jpg','https://image.ohou.se/i/bucketplace-v2-development/uploads/productions/166071768028857357.jpg?gif=1&w=850&h=850&c=c&webp=1 https://image.ohou.se/i/bucketplace-v2-development/uploads/productions/images/166009708418541552.jpg?gif=1&w=720&h=720&c=c&webp=1 https://image.ohou.se/i/bucketplace-v2-development/uploads/productions/images/166009711672252886.jpg?gif=1&w=720&h=720&c=c&webp=1 https://image.ohou.se/i/bucketplace-v2-development/uploads/productions/images/166009722787456970.jpg?gif=1&w=720&h=720&c=c&webp=1 https://image.ohou.se/i/bucketplace-v2-development/uploads/productions/images/166009723293443944.jpg?gif=1&w=720&h=720&c=c&webp=1','\0',0,278000,0,'국내생산 독일브랜드 IH 인덕션 냄비/프라이팬 4P 세트',3),(259,'None','https://hs214.cafe24.com/main/ik12686.jpg','https://cdn.011st.com/11dims/resize/600x600/quality/75/11src/pd/v2/9/2/8/2/6/3/FuJQd/5226928263_B.jpg','\0',0,80000,0,'인덕션 세라믹 스텐냄비 3종 세트 그린',3),(264,'PN풍년','https://gi.esmplus.com/pnmarket1/2022%20%EB%A7%88%EB%A0%88%20%ED%9E%88%ED%8A%B8%ED%8C%ACIH/1%20%EB%A7%88%EB%A0%88%20%ED%9E%88%ED%8A%B8%ED%8C%AC%20IH.jpg','https://sitem.ssgcdn.com/77/59/60/item/1000458605977_i1_1100.jpg','\0',0,34000,0,'PN풍년 마레 히트팬IH 2종세트',3),(269,'테팔','https://sstatic.ssgcdn.com/cmpt/edit/202011/27/132020112713322343689449170054_360.jpg','https://sitem.ssgcdn.com/23/72/36/item/1000056367223_i1_1100.jpg','\0',0,118700,0,'테팔 언리미티드 인덕션 후라이팬24+28cm',3),(274,'남양키친플라워','https://sstatic.ssgcdn.com/cmpt/edit/202208/18/211000428142048_d1.jpg','https://sitem.ssgcdn.com/48/20/14/item/1000428142048_i1_1100.jpg','\0',0,58900,0,'이연복셰프시그니처그리들팬32cm',3),(279,'셰프웨이','https://hs214.cafe24.com/main/cw10001.jpg','https://sitem.ssgcdn.com/37/85/56/item/1000528568537_i1_1100.jpg','\0',0,79800,0,'셰프웨이 인덕션 전립투 그리들[31877662]',3),(284,'놋담','https://sstatic.ssgcdn.com/cmpt/edit/202112/31/182021123118061731797329170832_336.jpg','https://sitem.ssgcdn.com/70/98/40/item/1000043409870_i1_1100.jpg https://sitem.ssgcdn.com/70/98/40/item/1000043409870_i2_550.jpg https://sitem.ssgcdn.com/70/98/40/item/1000043409870_i3_550.jpg','\0',0,143000,0,'유기 모던 전골냄비 3인용',3),(289,'닥터하우스','https://drhows1.cafe24.com/Drhows/brio/DRCW02006014_Detail.jpg','https://sitem.ssgcdn.com/13/61/10/item/1000391106113_i1_1100.jpg','\0',0,140000,0,'[닥터하우스] 브리오 IH인덕션 사각 그릴팬 30x24cm',3),(294,'르크루제','https://lecreuset.isware.co.kr/ITEM_IMAGE/2232531182259965545_D01.jpg','https://sitem.ssgcdn.com/43/36/22/item/1000040223643_i1_500.jpg','\0',0,228000,0,'[르크루제] 고메밥솥18cm(2.0L)',3),(299,'브라더가든','http://marmorene.img13.kr/plant2/997-1.jpg','https://www.brother-garden.co.kr/shopimages/marmorene/0160030002432.jpg?1669778967','\0',0,167000,0,'뱅갈고무나무',4),(304,'넬로','https://ai.esmplus.com/cuckooel/cuckoo/spec/elec_etc/etc/ND-A0610FG_spec.jpg','https://sitem.ssgcdn.com/85/23/39/item/1000035392385_i1_1100.jpg https://sitem.ssgcdn.com/85/23/39/item/1000035392385_i2_550.jpg https://sitem.ssgcdn.com/85/23/39/item/1000035392385_i5_550.jpg','\0',0,696000,0,'쿠쿠 넬로 ND-A0610FG 펫 샤워&드라이룸',4),(309,'일룸','https://shop-cdn.sidiz.com/_outside/iloom/%EC%BB%A4%EC%8A%A4%ED%85%80%EC%BA%A3%ED%83%80%EC%9B%8C%EB%AF%B8%EB%8B%88_%ED%88%AC%EB%AA%85%ED%95%B4%EB%A8%B9%ED%98%95.jpg','https://sitem.ssgcdn.com/57/67/79/item/1000527796757_i1_500.jpg','\0',0,390000,0,'[일룸] 캐스터네츠 커스텀 캣타워 미니_투명해먹형',4),(314,'HINOKI LAB','https://www.gettt.com/data/goods_desc_images/1000007296/model/1620957399-2%EB%B0%B0%EC%93%B0%EC%8F%A0%ED%8A%B8%EB%A7%88%EB%B8%94%EB%B8%8C%EB%9E%9C%EC%B9%98%20(1).jpg','https://pjh5023.cdn-nhncommerce.com/data/goods/21/05/19/1000007296/1000007296_detail_062.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/21/05/19/1000007296/1000007296_detail_165.jpg https://pjh5023.cdn-nhncommerce.com/data/goods/21/05/19/1000007296/1000007296_detail_283.jpg','\0',0,48000,0,'배쓰솔트, 마블 브랜치',4),(319,'베베드피노','https://foxyecho01.cafe24.com/bebedepino/2023_SPRING/product/Caniche-baby-stripe-bodysuit-set_2.jpg','https://sitem.ssgcdn.com/13/35/60/item/1000529603513_i1_1100.jpg','\0',0,69000,0,'카니슈베이비바디수트세트_BP31ST180',4),(324,'베베드피노','https://foxyecho01.cafe24.com/bebedepino/2022_Spring/product/Apple-vertical-stripe-cotton-overall_2.jpg','https://sitem.ssgcdn.com/34/66/13/item/1000532136634_i1_1100.jpg','\0',0,41300,0,'애플코튼오버롤 BP21OV385_P320192796',4),(329,'아가방','https://item.nextmom.co.kr/magaline_img/goods/agabang/2021/01O72750531.jpg','https://sitem.ssgcdn.com/05/69/76/item/1000506766905_i1_1100.jpg','\0',0,37170,0,'보아곰 우주복 BROWN 01O72750531_P329165843',4),(334,'콜맨','https://sstatic.ssgcdn.com/cmpt/edit/202212/29/121000525748088_d1.jpg','https://sitem.ssgcdn.com/88/80/74/item/1000525748088_i1_1100.jpg https://sitem.ssgcdn.com/88/80/74/item/1000525748088_i2_550.jpg https://sitem.ssgcdn.com/88/80/74/item/1000525748088_i3_550.jpg https://sitem.ssgcdn.com/88/80/74/item/1000525748088_i4_550.jpg','\0',0,149000,0,'콜맨 아웃도어 웨건_네이비X화이트3',4),(338,'오엘라','https://static.skmagic.com/image/editor/goods_desc/202211/1669796366390160.jpg','https://static.skmagic.com/image/goods/G000065173/G000065173_2_480x480.jpg','',0,22800,0,'GANSO 충전식 무선 LED 스탠드',2);
/*!40000 ALTER TABLE `item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `likes` (
  `likes_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `item_id` bigint(20) DEFAULT NULL,
  `member_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`likes_id`),
  KEY `FKa4vkf1skcfu5r6o5gfb5jf295` (`member_id`),
  CONSTRAINT `FKa4vkf1skcfu5r6o5gfb5jf295` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
INSERT INTO `likes` VALUES (1,338,1),(2,65,1);
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `member` (
  `member_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) NOT NULL,
  `deleted_at` datetime(6) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `login_id` varchar(32) NOT NULL,
  `mail` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `point` int(11) DEFAULT NULL,
  `profile_img` varchar(255) DEFAULT NULL,
  `member_status` bit(1) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`member_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (1,'2023-02-16 14:40:50.369695',NULL,'2023-02-16 14:40:50.369718','ROLE_USER','2655877981','hojo0423@naver.com','$2a$10$hy1wWDtn2Q8H5TcRrUpgP.fXsGBoNuKuqSvE.V7LwEkL9A9ursBam',100000,'http://k.kakaocdn.net/dn/bkklEp/btryRtPG1qK/LKRumD2OP1rzeQSrLfulF0/img_640x640.jpg','','정호조'),(2,'2023-02-16 15:34:53.262439',NULL,'2023-02-16 15:34:53.262461','ROLE_USER','2649888107','tndyd5713@naver.com','$2a$10$gWZZsHCtl4pgZktbn8E7vO0y705OZvfLIclnkCKXmlmV5fO8F90eK',100000,'http://k.kakaocdn.net/dn/btOhdH/btrTgCeJC7y/99gNdEKdL6r0kr56L5N6C0/img_640x640.jpg','','이수용');
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `refresh_token`
--

DROP TABLE IF EXISTS `refresh_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `refresh_token` (
  `rt_key` varchar(255) NOT NULL,
  `rt_value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rt_key`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `refresh_token`
--

LOCK TABLES `refresh_token` WRITE;
/*!40000 ALTER TABLE `refresh_token` DISABLE KEYS */;
INSERT INTO `refresh_token` VALUES ('1','eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NzcxNzE4NzN9.7uM5TvMEXWBnnVC5_Z2RghpLvX7O9aCVj2lGiPxFEMyx4z1gVgv2OwUeNtFVFcOhyLbUrCHZqexygJhM4QLDAA'),('2','eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2NzcxNjY5MzZ9.WYjoNitvLd2iWhv65hTGW-lSSXnYeembPdkvnBM_KvB6K1MH7ARMrVGfYD03k0__9XowS28cwsjra9nLgHaqHA');
/*!40000 ALTER TABLE `refresh_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `search`
--

DROP TABLE IF EXISTS `search`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `search` (
  `id` bigint(20) NOT NULL,
  `keyword` varchar(255) DEFAULT NULL,
  `search_count` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `search`
--

LOCK TABLES `search` WRITE;
/*!40000 ALTER TABLE `search` DISABLE KEYS */;
INSERT INTO `search` VALUES (335,'메리토',6),(339,'샤워',1),(340,'냉장고',1),(341,'비스포크',1),(342,'무드등',1),(343,'책상',1),(344,'데스크',1),(345,'의자',1),(346,'서랍',1),(347,'큐브',4);
/*!40000 ALTER TABLE `search` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-02-16 17:06:04

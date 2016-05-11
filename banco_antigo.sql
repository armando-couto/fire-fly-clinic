-- MySQL dump 10.13  Distrib 5.5.9, for Win32 (x86)
--
-- Host: localhost    Database: sisclinic
-- ------------------------------------------------------
-- Server version	5.5.11

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
-- Current Database: `sisclinic`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `sisclinic` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `sisclinic`;

--
-- Table structure for table `acompanhamento`
--

DROP TABLE IF EXISTS `acompanhamento`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `acompanhamento` (
  `id_acompanhamento` bigint(20) NOT NULL AUTO_INCREMENT,
  `id_paciente` bigint(20) NOT NULL,
  `descricao` varchar(1000) NOT NULL,
  `data_cadastro` datetime NOT NULL,
  PRIMARY KEY (`id_acompanhamento`),
  KEY `FK_ACOMPANHAMENTO_PACIENTE` (`id_paciente`),
  CONSTRAINT `FK_ACOMPANHAMENTO_PACIENTE` FOREIGN KEY (`id_paciente`) REFERENCES `paciente` (`id_paciente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `acompanhamento`
--

LOCK TABLES `acompanhamento` WRITE;
/*!40000 ALTER TABLE `acompanhamento` DISABLE KEYS */;
INSERT INTO `acompanhamento` VALUES (1,12,'GGGGGGGGGGGGGG','2011-05-29 00:00:00'),(2,12,'kjkgfkghçssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssssggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggggiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiiidddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddiiiiiiiii','2011-05-29 00:00:00'),(3,12,'fdjgfjgjfg','2010-04-28 00:00:00'),(4,13,'gfgujdfiotry','0000-00-00 00:00:00');
/*!40000 ALTER TABLE `acompanhamento` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paciente`
--

DROP TABLE IF EXISTS `paciente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paciente` (
  `id_paciente` bigint(20) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  `idade` varchar(2) NOT NULL,
  `data_nascimento` datetime NOT NULL,
  `email` varchar(50) DEFAULT NULL,
  `escolaridade` varchar(60) NOT NULL,
  `profissao` varchar(60) DEFAULT NULL,
  `naturalidade` varchar(60) NOT NULL,
  `nome_pai` varchar(100) DEFAULT NULL,
  `escolaridade_pai` varchar(60) DEFAULT NULL,
  `nome_mae` varchar(100) DEFAULT NULL,
  `escolaridade_mae` varchar(60) DEFAULT NULL,
  `numero_irmaos` varchar(2) DEFAULT NULL,
  `estado_civil` varchar(30) NOT NULL,
  `nome_esposa` varchar(100) DEFAULT NULL,
  `endereco` varchar(255) NOT NULL,
  `numero` varchar(4) NOT NULL,
  `complemento` varchar(30) DEFAULT NULL,
  `bairro` varchar(60) DEFAULT NULL,
  `cep` varchar(10) DEFAULT NULL,
  `local_trabalho` varchar(60) DEFAULT NULL,
  `telefone_residencial_ddd` varchar(2) NOT NULL,
  `telefone_residencial` varchar(9) NOT NULL,
  `telefone_comercial_ddd` varchar(2) DEFAULT NULL,
  `telefone_comercial` varchar(9) DEFAULT NULL,
  `telefone_celular_ddd` varchar(2) DEFAULT NULL,
  `telefone_celular` varchar(9) DEFAULT NULL,
  `data_cadastro` datetime DEFAULT NULL,
  PRIMARY KEY (`id_paciente`,`nome`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paciente`
--

LOCK TABLES `paciente` WRITE;
/*!40000 ALTER TABLE `paciente` DISABLE KEYS */;
INSERT INTO `paciente` VALUES (12,'Bianca Nunes Reboucasssss','19','2011-10-19 00:00:00','Bihh_nunes@hotmail.com','Superior Incomp','Estudante','Fortalezense','Jose','Superior Comp','Vania','Superior Incomp','02','Namorando','Jeferson de Sa','Rua Jose Candido','137 ','','Monte Castelo','60.325-490','','85','3021-1518','  ','    -    ','85','8733-4366','2011-10-19 00:00:00'),(13,'Jeferson Sa','20','2011-10-19 00:00:00','jack_3091@hotmail.com','Superior Incomp','Estudandte','Fortalezense','Evaldo','Superior Incomp','Celly','Mestrado','01','Namorando','Bianca','Rua Padre Januario','68','Casa 26','Parque Manibura','60.325-490','UFC','85','3271-0332','85','3369-5710','85','9921-2962','2011-10-19 00:00:00'),(14,'Jessica Adamy','20','2011-10-19 00:00:00','jessica_2@hotmail.com','Superior Incomp','Estudante','Fortalezense','Flavio','Superior Comp','Val','Médio Comp','01','Namorando','Armando','Rua batista','780','apto 505','coco','30.303-030','Via logica','85','3020-2020','85','1515-6151','85','1516-5156','2011-10-19 00:00:00'),(16,'Armando Couto','58','2011-10-19 00:00:00','','Fundamental Incomp','','hjjk','','-------Selecione-------','','-------Selecione-------','  ','Solteiro(a)','','bdjdk','5555','','','  .   -   ','','85','5555-5555','  ','    -    ','  ','    -    ','2011-10-19 00:00:00'),(17,'Armando Coutooooooo','22','2011-10-19 00:00:00','','Doutorado','','fgdfj','','-------Selecione-------','','-------Selecione-------','  ','Namorando','','dhfl','0022','','','60.192-035','','88','0055-5555','  ','    -    ','  ','    -    ','2011-10-19 00:00:00'),(18,'Fatima Plutarco','22','2011-10-19 00:00:00','','Fundamental Comp','','Cearense','','-------Selecione-------','','-------Selecione-------','  ','Namorando','','hdjfk','2222','','','  .   -   ','','85','8888-8888','  ','    -    ','  ','    -    ','2011-10-19 00:00:00'),(19,'fdfhj','22','2223-10-22 00:00:00','','Fundamental Comp','','dhfjk','','-------Selecione-------','','-------Selecione-------','  ','Namorando','','hfjfj','2555','','','  .   -   ','','88','8888-8888','  ','    -    ','  ','    -    ','2011-10-19 00:00:00'),(20,'Niedja','22','2011-10-20 00:00:00','','Fundamental Incomp','','EUA','','-------Selecione-------','','-------Selecione-------','  ','Casado(a)','','Perto da Mosenhor Tabosa','0000','','','  .   -   ','','55','8888-8888','  ','    -    ','  ','    -    ','2011-10-20 00:00:00'),(21,'AAA','21','1212-12-12 00:00:00','','Fundamental Incomp','','S','','-------Selecione-------','','-------Selecione-------','  ','Solteiro(a)','','OKOK','8888','','','  .   -   ','','88','8888-8888','  ','    -    ','  ','    -    ','2011-10-20 00:00:00'),(22,'eee','56','2010-01-01 00:00:00','','Fundamental Incomp','','rrrrr','','-------Selecione-------','','-------Selecione-------','  ','Solteiro(a)','','ttttt','5656','','','60.000-000','','65','5656-5656','  ','    -    ','  ','    -    ','2011-10-20 00:00:00'),(23,'fsddfsf','55','5559-08-24 00:00:00','','Fundamental Comp','','dsdff','','-------Selecione-------','','-------Selecione-------','45','Noívo(a)','','dfgfh','5555','','','55.555-555','','55','5555-5555','55','5555-5555','55','6666-6666','2011-10-20 00:00:00');
/*!40000 ALTER TABLE `paciente` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `login` varchar(50) NOT NULL,
  `senha` varchar(20) NOT NULL,
  `papel` varchar(50) NOT NULL,
  PRIMARY KEY (`login`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES ('AA','AA','PSICOLOGA'),('BB','bb','PIAO');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2011-10-22 10:44:43


CREATE TABLE `tipo_cliente` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creado_en` datetime DEFAULT NULL,
  `creado_por` varchar(255) DEFAULT NULL,
  `modificado_en` datetime DEFAULT NULL,
  `modificado_por` varchar(255) DEFAULT NULL,
  `codigo` varchar(255) NOT NULL,
  `estado` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `version` bigint(20) DEFAULT NULL,
  `tipo_ncf_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKigkr8a7kt98cp0dkobqglhroy` (`tipo_ncf_id`),
  CONSTRAINT `FKigkr8a7kt98cp0dkobqglhroy` FOREIGN KEY (`tipo_ncf_id`) REFERENCES `tipo_ncf` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-05 11:51:54


CREATE TABLE `detalle_tipo_servicio` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creado_en` datetime DEFAULT NULL,
  `creado_por` varchar(255) DEFAULT NULL,
  `modificado_en` datetime DEFAULT NULL,
  `modificado_por` varchar(255) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `servicio_id` bigint(20) DEFAULT NULL,
  `tipo_servicio_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK4bow151fqmh45uyw14v96rg9m` (`servicio_id`),
  KEY `FKkgs6vv8ol77esg244k6l9mrio` (`tipo_servicio_id`),
  CONSTRAINT `FK4bow151fqmh45uyw14v96rg9m` FOREIGN KEY (`servicio_id`) REFERENCES `servicio` (`id`),
  CONSTRAINT `FKkgs6vv8ol77esg244k6l9mrio` FOREIGN KEY (`tipo_servicio_id`) REFERENCES `tipo_servicio` (`id`)
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

-- Dump completed on 2017-06-13 12:32:08

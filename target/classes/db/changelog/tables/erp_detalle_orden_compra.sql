
CREATE TABLE `detalle_orden_compra` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creado_en` datetime DEFAULT NULL,
  `creado_por` varchar(255) DEFAULT NULL,
  `modificado_en` datetime DEFAULT NULL,
  `modificado_por` varchar(255) DEFAULT NULL,
  `cantidad` bigint(20) DEFAULT NULL,
  `estado` int(11) NOT NULL,
  `precio` decimal(19,2) DEFAULT NULL,
  `monto` decimal(19,2) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `orden_compra_id` bigint(20) DEFAULT NULL,
  `producto_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7oeckpu1s80v9h85i6l0abmmb` (`producto_id`),
  KEY `UK_hhoa953inyfwvum22fgn1k7oy` (`orden_compra_id`),
  CONSTRAINT `FK7oeckpu1s80v9h85i6l0abmmb` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`),
  CONSTRAINT `FKbhncrqsfa8ueay6to7leb6yb7` FOREIGN KEY (`orden_compra_id`) REFERENCES `orden_compra` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-30 21:33:03

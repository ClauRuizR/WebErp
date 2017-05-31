
CREATE TABLE `factura` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creado_en` datetime DEFAULT NULL,
  `creado_por` varchar(255) DEFAULT NULL,
  `modificado_en` datetime DEFAULT NULL,
  `modificado_por` varchar(255) DEFAULT NULL,
  `comprobante_fiscal` bit(1) NOT NULL,
  `descuento` decimal(19,2) DEFAULT NULL,
  `estado` int(11) NOT NULL,
  `estatus` varchar(255) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `numero_comprobante_fiscal` varchar(255) DEFAULT NULL,
  `numero_documento` varchar(255) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `cliente_id` bigint(20) DEFAULT NULL,
  `tipo_documento_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2602efsrpmevi8yxg464stfn5` (`cliente_id`),
  KEY `FK2ljjnstcnpyni0ifcoumgabvl` (`tipo_documento_id`),
  CONSTRAINT `FK2602efsrpmevi8yxg464stfn5` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`),
  CONSTRAINT `FK2ljjnstcnpyni0ifcoumgabvl` FOREIGN KEY (`tipo_documento_id`) REFERENCES `tipo_documento` (`id`)
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

-- Dump completed on 2017-05-30 21:33:02

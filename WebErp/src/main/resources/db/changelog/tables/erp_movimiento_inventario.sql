
CREATE TABLE `movimiento_inventario` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creado_en` datetime DEFAULT NULL,
  `creado_por` varchar(255) DEFAULT NULL,
  `modificado_en` datetime DEFAULT NULL,
  `modificado_por` varchar(255) DEFAULT NULL,
  `cantidad` bigint(20) DEFAULT NULL,
  `estado` int(11) NOT NULL,
  `numero_documento` varchar(255) DEFAULT NULL,
  `tipo_movimiento` varchar(255) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `almacen_id` bigint(20) DEFAULT NULL,
  `producto_id` bigint(20) DEFAULT NULL,
  `tipo_documento_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd6bavqmj4o0mvn82ve46jac9v` (`producto_id`),
  KEY `FK1bl1vbilcmrolo34fxq223f08` (`tipo_documento_id`),
  KEY `UK_2i1tpsntnr7whcqfct2am2eqd` (`almacen_id`),
  CONSTRAINT `FK1bl1vbilcmrolo34fxq223f08` FOREIGN KEY (`tipo_documento_id`) REFERENCES `tipo_documento` (`id`),
  CONSTRAINT `FK94wggfjaggy2avspex2ohs7tf` FOREIGN KEY (`almacen_id`) REFERENCES `almacen` (`id`),
  CONSTRAINT `FKd6bavqmj4o0mvn82ve46jac9v` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`)
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

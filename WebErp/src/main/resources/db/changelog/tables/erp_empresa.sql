
CREATE TABLE `empresa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creado_en` datetime DEFAULT NULL,
  `creado_por` varchar(255) DEFAULT NULL,
  `modificado_en` datetime DEFAULT NULL,
  `modificado_por` varchar(255) DEFAULT NULL,
  `estado` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `logo` VARCHAR(255) NOT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `rnc` varchar(255) DEFAULT NULL,
  `website` varchar(255) DEFAULT NULL,
  `razon_social` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `fax` varchar(255) DEFAULT NULL,
  `grupo_empresa_id` Bigint(20) NOT NULL,
  `version` bigint(20) DEFAULT NULL,
    PRIMARY KEY (`id`),
   CONSTRAINT `fkGrupoEmpresa` FOREIGN KEY (`grupo_empresa_id`) REFERENCES `grupo_empresa` (`id`)
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

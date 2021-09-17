CREATE TABLE `diario_general` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creado_en` datetime DEFAULT NULL,
  `creado_por` varchar(255) DEFAULT NULL,
  `modificado_en` datetime DEFAULT NULL,
  `modificado_por` varchar(255) DEFAULT NULL,
  `credito` decimal(19,2) DEFAULT NULL,
  `debito` decimal(19,2) DEFAULT NULL,
  `descripcion` varchar(255) DEFAULT NULL,
  `estado` int(11) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `numero_documento` varchar(255) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

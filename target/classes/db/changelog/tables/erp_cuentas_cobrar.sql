
CREATE TABLE `cuentas_cobrar` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creado_en` datetime DEFAULT NULL,
  `creado_por` varchar(255) DEFAULT NULL,
  `modificado_en` datetime DEFAULT NULL,
  `modificado_por` varchar(255) DEFAULT NULL,
  `descuento` decimal(19,2) NOT NULL,
  `estado` int(11) DEFAULT NULL,
  `importe` decimal(19,2) NOT NULL,
  `itbis` decimal(19,2) NOT NULL,
  `numero_documento` varchar(255) NOT NULL,
  `fecha_documento` datetime DEFAULT NULL,
  `comentario` varchar(255) NOT NULL,
  `empresa_id` bigint(20) NOT NULL ,
  `cliente_id` bigint(20) NOT NULL ,
  `sub_total` decimal(19,2) NOT NULL,
  `total` decimal(19,2) NOT NULL,
  `version` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

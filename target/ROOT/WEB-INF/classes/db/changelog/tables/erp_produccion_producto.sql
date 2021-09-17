CREATE TABLE `produccion_producto` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `creado_en` datetime DEFAULT NULL,
  `creado_por` varchar(255) DEFAULT NULL,
  `modificado_en` datetime DEFAULT NULL,
  `modificado_por` varchar(255) DEFAULT NULL,
  `costo_servicio` decimal(19,2) DEFAULT NULL,
  `numero_documento` varchar(255) DEFAULT NULL,
  `monto_precio_compra` decimal(19,2) DEFAULT NULL,
  `monto_precio_venta` decimal(19,2) DEFAULT NULL,
  `total` decimal(19,2) DEFAULT NULL,
  `version` bigint(20) DEFAULT NULL,
  `producto_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fkProduccionProductoProductoId` (`producto_id`),
  CONSTRAINT `fkProductoProduccionPrdocuto` FOREIGN KEY (`producto_id`) REFERENCES `producto` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

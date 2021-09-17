CREATE TABLE `tipo_servicio_detalle_tipo_servicio` (
  `tipo_servicio_id` bigint(20) NOT NULL,
  `detalle_tipo_servicio_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_jw40a0obgaafn7dlgff5m24cs` (`detalle_tipo_servicio_id`),
  KEY `FKc0p10l4duvtgcmxgkgy1lhxxp` (`tipo_servicio_id`),
  CONSTRAINT `FKc0p10l4duvtgcmxgkgy1lhxxp` FOREIGN KEY (`tipo_servicio_id`) REFERENCES `tipo_servicio` (`id`),
  CONSTRAINT `FKsa4r2hgpsq15g8elo57tamkf9` FOREIGN KEY (`detalle_tipo_servicio_id`) REFERENCES `detalle_tipo_servicio` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

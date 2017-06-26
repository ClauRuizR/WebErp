CREATE TABLE `grupo_empresa_empresa` (
  `grupo_empresa_id` bigint(20) NOT NULL,
  `empresa_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_empresa_id` (`empresa_id`),
  KEY `FK_grupo_empresa_id` (`grupo_empresa_id`),
  CONSTRAINT `FKEmpresa` FOREIGN KEY (`empresa_id`) REFERENCES `empresa` (`id`),
  CONSTRAINT `FKfgrupoEmpresa` FOREIGN KEY (`grupo_empresa_id`) REFERENCES `grupo_empresa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

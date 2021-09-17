
CREATE TABLE `orden_compra_detalle_orden_compra` (
  `orden_compra_id` bigint(20) NOT NULL,
  `detalle_orden_compra_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_t6hhht2ikr5d7ox5lhm08b4r6` (`detalle_orden_compra_id`),
  KEY `FK9k0o4s8aevdgu4rcsfuyjgmdg` (`orden_compra_id`),
  CONSTRAINT `FK3ovq4gb3htxmviiwcn12vodf6` FOREIGN KEY (`detalle_orden_compra_id`) REFERENCES `detalle_orden_compra` (`id`),
  CONSTRAINT `FK9k0o4s8aevdgu4rcsfuyjgmdg` FOREIGN KEY (`orden_compra_id`) REFERENCES `orden_compra` (`id`)
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

-- Dump completed on 2017-05-30 21:33:05

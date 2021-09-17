
CREATE TABLE `almacen_detalle_almacen` (
  `almacen_id` bigint(20) NOT NULL,
  `detalle_almacen_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_5lks6kc367osv4vgdbylvrmx5` (`detalle_almacen_id`),
  KEY `FKfhvfbtt13uf2ciqd0ssi9gjhx` (`almacen_id`),
  CONSTRAINT `FKfhvfbtt13uf2ciqd0ssi9gjhx` FOREIGN KEY (`almacen_id`) REFERENCES `almacen` (`id`),
  CONSTRAINT `FKj119d2xloddro4hxd72tix7m9` FOREIGN KEY (`detalle_almacen_id`) REFERENCES `detalle_almacen` (`id`)
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

-- Dump completed on 2017-05-30 21:33:03

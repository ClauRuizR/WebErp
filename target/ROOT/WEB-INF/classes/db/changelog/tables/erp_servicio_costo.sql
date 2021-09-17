
CREATE TABLE `servicio_costo` (
  `servicio_id` bigint(20) NOT NULL,
  `costo_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_630lifgwjp2q746ayapyi43ay` (`costo_id`),
  KEY `FKe1mhylmmllp1dj6nbn6hkjwiw` (`servicio_id`),
  CONSTRAINT `FKe1mhylmmllp1dj6nbn6hkjwiw` FOREIGN KEY (`servicio_id`) REFERENCES `servicio` (`id`),
  CONSTRAINT `FKn2hbuc0c1txrfmos7rruo4jhn` FOREIGN KEY (`costo_id`) REFERENCES `costo` (`id`)
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

-- Dump completed on 2017-06-13 12:32:08

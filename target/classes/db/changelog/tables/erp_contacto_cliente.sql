
CREATE TABLE `contacto_cliente` (
  `cliente_id` bigint(20) NOT NULL,
  `contacto_id` bigint(20) NOT NULL,
  KEY `FK6tk21u0dkuyowynikv0p9dnv8` (`contacto_id`),
  KEY `FKandyd0nt2jb8qfv4o1ve0p1wb` (`cliente_id`),
  CONSTRAINT `FK6tk21u0dkuyowynikv0p9dnv8` FOREIGN KEY (`contacto_id`) REFERENCES `contacto` (`id`),
  CONSTRAINT `FKandyd0nt2jb8qfv4o1ve0p1wb` FOREIGN KEY (`cliente_id`) REFERENCES `cliente` (`id`)
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

-- Dump completed on 2017-05-30 21:33:04

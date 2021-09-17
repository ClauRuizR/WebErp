
CREATE TABLE `contacto_proveedor` (
  `proveedor_id` bigint(20) NOT NULL,
  `contacto_id` bigint(20) NOT NULL,
  KEY `FKqvrtb0gud0m40drioqyk07m0p` (`contacto_id`),
  KEY `FKl49m11xyqtngtwl72c5nhsgb7` (`proveedor_id`),
  CONSTRAINT `FKl49m11xyqtngtwl72c5nhsgb7` FOREIGN KEY (`proveedor_id`) REFERENCES `proveedor` (`id`),
  CONSTRAINT `FKqvrtb0gud0m40drioqyk07m0p` FOREIGN KEY (`contacto_id`) REFERENCES `contacto` (`id`)
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

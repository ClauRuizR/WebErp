
CREATE TABLE `factura_detalle_factura` (
  `factura_id` bigint(20) NOT NULL,
  `detalle_factura_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_62a8x63ostsfb7ijjhlspnqid` (`detalle_factura_id`),
  KEY `FKf06rtcoul8u62p6eylagc41gq` (`factura_id`),
  CONSTRAINT `FKduoot5wox044afo008y06uxue` FOREIGN KEY (`detalle_factura_id`) REFERENCES `detalle_factura` (`id`),
  CONSTRAINT `FKf06rtcoul8u62p6eylagc41gq` FOREIGN KEY (`factura_id`) REFERENCES `factura` (`id`)
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

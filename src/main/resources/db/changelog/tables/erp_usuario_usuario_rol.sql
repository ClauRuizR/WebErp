
CREATE TABLE `usuario_usuario_rol` (
  `usuario_id` bigint(20) NOT NULL,
  `usuario_rol_id` bigint(20) NOT NULL,
  UNIQUE KEY `UK_43ofr44x7o4ui8k4vq6s0x0hl` (`usuario_rol_id`),
  KEY `FKcc0khkvf7g0yircoq8w0ayfmf` (`usuario_id`),
  CONSTRAINT `FKcc0khkvf7g0yircoq8w0ayfmf` FOREIGN KEY (`usuario_id`) REFERENCES `usuario` (`id`),
  CONSTRAINT `FKh6wdk8702nqug6oikp4ckonec` FOREIGN KEY (`usuario_rol_id`) REFERENCES `usuario_rol` (`id`)
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

-- Dump completed on 2017-06-19 11:06:12

USE `sist_pesada`;

DROP TABLE IF EXISTS `ata`;

CREATE TABLE `ata` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `CUIT` varchar(45) DEFAULT NULL,
  `ultimo_movimiento` datetime DEFAULT NULL,
  `acumulado` decimal(9,2) DEFAULT NULL,
  `nacionalidad` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

LOCK TABLES `ata` WRITE;
/*!40000 ALTER TABLE `ata` DISABLE KEYS */;
INSERT INTO `ata` VALUES (1,'ASDA','CUITASDA',NULL,NULL,NULL),(2,'ATA1','12CUITATA',NULL,NULL,'ARGENTINO');
/*!40000 ALTER TABLE `ata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bal_ata`
--

DROP TABLE IF EXISTS `bal_ata`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bal_ata` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cuit` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `nationality` varchar(255) DEFAULT NULL,
  `id_company` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmbqypc3j1jfj9y6glngo9t264` (`id_company`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bal_ata`
--

LOCK TABLES `bal_ata` WRITE;
/*!40000 ALTER TABLE `bal_ata` DISABLE KEYS */;
/*!40000 ALTER TABLE `bal_ata` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bal_client`
--

DROP TABLE IF EXISTS `bal_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bal_client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cuit` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `id_company` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjmipvpqwrm7d91jfh12odgtov` (`id_company`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bal_client`
--

LOCK TABLES `bal_client` WRITE;
/*!40000 ALTER TABLE `bal_client` DISABLE KEYS */;
/*!40000 ALTER TABLE `bal_client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bal_company`
--

DROP TABLE IF EXISTS `bal_company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bal_company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cuit` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bal_company`
--

LOCK TABLES `bal_company` WRITE;
/*!40000 ALTER TABLE `bal_company` DISABLE KEYS */;
/*!40000 ALTER TABLE `bal_company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bal_import_export`
--

DROP TABLE IF EXISTS `bal_import_export`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bal_import_export` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `count` bigint(20) DEFAULT NULL,
  `cuit` varchar(255) DEFAULT NULL,
  `last_moviment` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bal_import_export`
--

LOCK TABLES `bal_import_export` WRITE;
/*!40000 ALTER TABLE `bal_import_export` DISABLE KEYS */;
/*!40000 ALTER TABLE `bal_import_export` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bal_indicator`
--

DROP TABLE IF EXISTS `bal_indicator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bal_indicator` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bits_of_data` int(11) DEFAULT NULL,
  `bits_of_stop` varchar(255) DEFAULT NULL,
  `character_control` varchar(255) DEFAULT NULL,
  `flow_of_control` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `paridad` varchar(255) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  `position_character_control` int(11) DEFAULT NULL,
  `position_inicio_dato` int(11) DEFAULT NULL,
  `size_character_control` int(11) DEFAULT NULL,
  `size_data` int(11) DEFAULT NULL,
  `velocity` int(11) DEFAULT NULL,
  `id_company` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK29iwk3clqcrm2n638hvoblx9d` (`id_company`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bal_indicator`
--

LOCK TABLES `bal_indicator` WRITE;
/*!40000 ALTER TABLE `bal_indicator` DISABLE KEYS */;
/*!40000 ALTER TABLE `bal_indicator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bal_operations`
--

DROP TABLE IF EXISTS `bal_operations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bal_operations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `id_company` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq0ga4ca8rl3j7ahlcpiwgyw80` (`id_company`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bal_operations`
--

LOCK TABLES `bal_operations` WRITE;
/*!40000 ALTER TABLE `bal_operations` DISABLE KEYS */;
/*!40000 ALTER TABLE `bal_operations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bal_origin`
--

DROP TABLE IF EXISTS `bal_origin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bal_origin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `id_company` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKluycbdg6dpgotddfg71ol6akc` (`id_company`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bal_origin`
--

LOCK TABLES `bal_origin` WRITE;
/*!40000 ALTER TABLE `bal_origin` DISABLE KEYS */;
/*!40000 ALTER TABLE `bal_origin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bal_patents`
--

DROP TABLE IF EXISTS `bal_patents`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bal_patents` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `patent` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bal_patents`
--

LOCK TABLES `bal_patents` WRITE;
/*!40000 ALTER TABLE `bal_patents` DISABLE KEYS */;
/*!40000 ALTER TABLE `bal_patents` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bal_product`
--

DROP TABLE IF EXISTS `bal_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bal_product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alias` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `id_company` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKpesdgnehvmp9jk45gy3hsgsce` (`id_company`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bal_product`
--

LOCK TABLES `bal_product` WRITE;
/*!40000 ALTER TABLE `bal_product` DISABLE KEYS */;
/*!40000 ALTER TABLE `bal_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bal_profile`
--

DROP TABLE IF EXISTS `bal_profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bal_profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `id_company` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKdihs9xub147tf24ryl4ddtugm` (`id_company`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bal_profile`
--

LOCK TABLES `bal_profile` WRITE;
/*!40000 ALTER TABLE `bal_profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `bal_profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bal_reports`
--

DROP TABLE IF EXISTS `bal_reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bal_reports` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `count` int(11) DEFAULT NULL,
  `id_tara` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bal_reports`
--

LOCK TABLES `bal_reports` WRITE;
/*!40000 ALTER TABLE `bal_reports` DISABLE KEYS */;
/*!40000 ALTER TABLE `bal_reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bal_taras`
--

DROP TABLE IF EXISTS `bal_taras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bal_taras` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `ata` tinyblob,
  `balanza` varchar(255) DEFAULT NULL,
  `cliente` tinyblob,
  `comprobante_nun1` varchar(255) DEFAULT NULL,
  `conductor` varchar(255) DEFAULT NULL,
  `contenedor` varchar(255) DEFAULT NULL,
  `contenedor_num` varchar(255) DEFAULT NULL,
  `destino` varchar(255) DEFAULT NULL,
  `fecha_entrada` datetime DEFAULT NULL,
  `fecha_salida` datetime DEFAULT NULL,
  `imp_exp` tinyblob,
  `manifiesto` varchar(255) DEFAULT NULL,
  `mercaderia` varchar(255) DEFAULT NULL,
  `modalidad` varchar(255) DEFAULT NULL,
  `modo_chasis` varchar(255) DEFAULT NULL,
  `modo_tara` varchar(255) DEFAULT NULL,
  `nacionalidad` varchar(255) DEFAULT NULL,
  `num_doc` varchar(255) DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `observacion_aduana` varchar(255) DEFAULT NULL,
  `patente` tinyblob,
  `patente_aceptado` varchar(255) DEFAULT NULL,
  `peso_entrada` decimal(19,2) DEFAULT NULL,
  `peso_neto` decimal(19,2) DEFAULT NULL,
  `peso_salida` decimal(19,2) DEFAULT NULL,
  `procedencias` tinyblob,
  `producto` tinyblob,
  `tipo_doc` varchar(255) DEFAULT NULL,
  `transaccion` varchar(255) DEFAULT NULL,
  `transporte` tinyblob,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bal_taras`
--

LOCK TABLES `bal_taras` WRITE;
/*!40000 ALTER TABLE `bal_taras` DISABLE KEYS */;
/*!40000 ALTER TABLE `bal_taras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bal_transport`
--

DROP TABLE IF EXISTS `bal_transport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bal_transport` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cuit` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `id_company` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKc71ucq43mi66yilrans9cfmpx` (`id_company`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bal_transport`
--

LOCK TABLES `bal_transport` WRITE;
/*!40000 ALTER TABLE `bal_transport` DISABLE KEYS */;
/*!40000 ALTER TABLE `bal_transport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bal_user`
--

DROP TABLE IF EXISTS `bal_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bal_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_expired` bit(1) NOT NULL,
  `account_locked` bit(1) NOT NULL,
  `created` datetime NOT NULL,
  `enabled` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `password_expired` bit(1) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bal_user`
--

LOCK TABLES `bal_user` WRITE;
/*!40000 ALTER TABLE `bal_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `bal_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bal_user_authority`
--

DROP TABLE IF EXISTS `bal_user_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bal_user_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bal_user_authority`
--

LOCK TABLES `bal_user_authority` WRITE;
/*!40000 ALTER TABLE `bal_user_authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `bal_user_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bal_user_token`
--

DROP TABLE IF EXISTS `bal_user_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bal_user_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_created` datetime NOT NULL,
  `refresh_token` varchar(255) NOT NULL,
  `revoked` bit(1) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bal_user_token`
--

LOCK TABLES `bal_user_token` WRITE;
/*!40000 ALTER TABLE `bal_user_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `bal_user_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cuit` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `id_company` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK7uj0kwhxaa9rqs9agvtp9341h` (`id_company`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `clientes`
--

DROP TABLE IF EXISTS `clientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `clientes` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `CUIT` varchar(45) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `localidad` varchar(255) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  `denominacion` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `clientes`
--

LOCK TABLES `clientes` WRITE;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` VALUES (1,'CLI2','CUITCLI2','col3','loc3','Prov2','Denom3');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `company`
--

DROP TABLE IF EXISTS `company`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cuit` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `company`
--

LOCK TABLES `company` WRITE;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
/*!40000 ALTER TABLE `company` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comunicaciones`
--

DROP TABLE IF EXISTS `comunicaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comunicaciones` (
  `idcomunicaciones` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `idindicadores` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcomunicaciones`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comunicaciones`
--

LOCK TABLES `comunicaciones` WRITE;
/*!40000 ALTER TABLE `comunicaciones` DISABLE KEYS */;
INSERT INTO `comunicaciones` VALUES (1,'INDICADOR 1',1),(2,'INDICADOR 2',2);
/*!40000 ALTER TABLE `comunicaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ejes`
--

DROP TABLE IF EXISTS `ejes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ejes` (
  `idEjes` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nroEje` int(11) DEFAULT NULL,
  `peso_entrada` decimal(9,2) DEFAULT NULL,
  `peso_salida` decimal(9,2) DEFAULT NULL,
  `idTaras` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEjes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ejes`
--

LOCK TABLES `ejes` WRITE;
/*!40000 ALTER TABLE `ejes` DISABLE KEYS */;
/*!40000 ALTER TABLE `ejes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `import_export`
--

DROP TABLE IF EXISTS `import_export`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `import_export` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `count` bigint(20) DEFAULT NULL,
  `cuit` varchar(255) DEFAULT NULL,
  `last_moviment` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `import_export`
--

LOCK TABLES `import_export` WRITE;
/*!40000 ALTER TABLE `import_export` DISABLE KEYS */;
/*!40000 ALTER TABLE `import_export` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `importadores_exportadores`
--

DROP TABLE IF EXISTS `importadores_exportadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `importadores_exportadores` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `CUIT` varchar(45) DEFAULT NULL,
  `ultimo_movimiento` datetime DEFAULT NULL,
  `acumulado` decimal(9,2) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `importadores_exportadores`
--

LOCK TABLES `importadores_exportadores` WRITE;
/*!40000 ALTER TABLE `importadores_exportadores` DISABLE KEYS */;
INSERT INTO `importadores_exportadores` VALUES (1,'IMP1','CUIT22',NULL,NULL);
/*!40000 ALTER TABLE `importadores_exportadores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `indicadores`
--

DROP TABLE IF EXISTS `indicadores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `indicadores` (
  `idindicadores` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(250) DEFAULT NULL,
  `posicion_caracter_control` int(11) DEFAULT NULL,
  `longitud_caracter_control` int(11) DEFAULT NULL,
  `caracter_control` varchar(5) DEFAULT NULL,
  `posicion_inicio_dato` int(11) DEFAULT NULL,
  `longitud_dato` int(11) DEFAULT NULL,
  `puerto` int(11) DEFAULT NULL,
  `velocidad` int(11) DEFAULT NULL,
  `bits_de_datos` int(1) DEFAULT NULL,
  `paridad` varchar(1) DEFAULT NULL,
  `control_de_flujo` varchar(45) DEFAULT NULL,
  `bits_parada` varchar(5) DEFAULT NULL,
  `is_eje` bit(1) DEFAULT NULL,
  PRIMARY KEY (`idindicadores`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `indicadores`
--

LOCK TABLES `indicadores` WRITE;
/*!40000 ALTER TABLE `indicadores` DISABLE KEYS */;
INSERT INTO `indicadores` VALUES (1,'INT1',0,0,'',0,6,2,1200,8,'n','0 - comNone','1',''),(2,'INT2',1,1,'',2,5,1,1200,8,'n','0 - comNone','1','\0');
/*!40000 ALTER TABLE `indicadores` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `indicator`
--

DROP TABLE IF EXISTS `indicator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `indicator` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `bits_of_data` int(11) DEFAULT NULL,
  `bits_of_stop` varchar(255) DEFAULT NULL,
  `character_control` varchar(255) DEFAULT NULL,
  `flow_of_control` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `paridad` varchar(255) DEFAULT NULL,
  `port` int(11) DEFAULT NULL,
  `position_character_control` int(11) DEFAULT NULL,
  `position_inicio_dato` int(11) DEFAULT NULL,
  `size_character_control` int(11) DEFAULT NULL,
  `size_data` int(11) DEFAULT NULL,
  `velocity` int(11) DEFAULT NULL,
  `id_company` bigint(20) DEFAULT NULL,
  `is_eje` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgidq55538yho1tltpdpuiiuwc` (`id_company`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `indicator`
--

LOCK TABLES `indicator` WRITE;
/*!40000 ALTER TABLE `indicator` DISABLE KEYS */;
/*!40000 ALTER TABLE `indicator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operaciones`
--

DROP TABLE IF EXISTS `operaciones`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operaciones` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operaciones`
--

LOCK TABLES `operaciones` WRITE;
/*!40000 ALTER TABLE `operaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `operaciones` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `operations`
--

DROP TABLE IF EXISTS `operations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `operations` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `id_company` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKq2fdwyt7p2n6awm5fjg8rh9dd` (`id_company`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `operations`
--

LOCK TABLES `operations` WRITE;
/*!40000 ALTER TABLE `operations` DISABLE KEYS */;
/*!40000 ALTER TABLE `operations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `origin`
--

DROP TABLE IF EXISTS `origin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `origin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `id_company` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKapnftbhr1089wfnrr2g5jkt84` (`id_company`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `origin`
--

LOCK TABLES `origin` WRITE;
/*!40000 ALTER TABLE `origin` DISABLE KEYS */;
/*!40000 ALTER TABLE `origin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parametros_globales`
--

DROP TABLE IF EXISTS `parametros_globales`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parametros_globales` (
  `id` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  `valueByte` longblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parametros_globales`
--

LOCK TABLES `parametros_globales` WRITE;
/*!40000 ALTER TABLE `parametros_globales` DISABLE KEYS */;
INSERT INTO `parametros_globales` VALUES ('ACTIVAR_DEBUG','true',NULL),('CERTIFICADO','A4',NULL),('CODIGO_ADUANA','1',NULL),('CODIGO_LOG','2',NULL),('EMPRESA_AUTOMATICO','',NULL),('EMPRESA_BACKUP','C:\\nodejs',NULL),('EMPRESA_DENOMINACION_BAL','',NULL),('EMPRESA_DIR','MISIONES 1011',NULL),('EMPRESA_DIR_BAL','Aasdasd',NULL),('EMPRESA_EMAIL','VENTAS@FULLSERVICEBALANZAS.COM.AR',NULL),('EMPRESA_EMAIL_BAL','A',NULL),('EMPRESA_ING_MANUAL','123422',NULL),('EMPRESA_LOC','SAN LORENZO',NULL),('EMPRESA_LOC_BAL','A2121',NULL),('EMPRESA_NOMBRE','BALANZAS FULL SERVICE SRL',NULL),('EMPRESA_NOMBRE_BAL','Prueba nombre2',NULL),('EMPRESA_PROV','SANTA FE',NULL),('EMPRESA_PROV_BAL','D',NULL),('EMPRESA_RESTORE','C:\\flutter\\.cirrus.yml',NULL),('EMPRESA_TEL','0341 153538781 // 03476 15535441',NULL),('EMPRESA_TEL_BAL','C',NULL),('EMPRESA_TICKET','TICKET CON FORMATO ETIQUETADORA',NULL),('EMPRESA_TRANSACCION','4',NULL),('EXPORT_PATH','C:\\SistemaDePesaje\\csv',NULL),('NUM_BALANZAS','2',NULL),('PASS_WINDOWS','12',NULL),('REMITO_PAGE_FORMAT','A4',NULL),('TICKET_ETIQUETADORA','REMITO',NULL),('USER_WINDOWS','12',NULL),('VALIDACION_ENTRADA','',NULL),('VALIDACION_SALIDA','',NULL),('VENCIMIENTO','4',NULL);
/*!40000 ALTER TABLE `parametros_globales` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patentes`
--

DROP TABLE IF EXISTS `patentes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patentes` (
  `codigo` varchar(45) NOT NULL,
  `tara` double NOT NULL,
  `date_update` datetime NOT NULL,
  `dias` int(11) NOT NULL DEFAULT '0',
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patentes`
--

LOCK TABLES `patentes` WRITE;
/*!40000 ALTER TABLE `patentes` DISABLE KEYS */;
INSERT INTO `patentes` VALUES ('1231',0,'2021-06-19 20:57:20',0,NULL),('12312',0,'2021-06-19 20:57:58',0,NULL),('123WSDE',0,'2021-07-20 19:35:22',0,'123WSDE'),('31231',0,'2021-06-19 21:05:23',0,NULL),('313',0,'2021-07-20 19:41:48',0,'313'),('333JD',0,'2021-06-19 21:16:41',0,NULL),('ASD111',0,'2021-07-10 19:38:08',0,NULL),('JDOWW222',1.33,'2021-07-10 19:23:15',2,NULL),('JDOWW22212',0,'2021-07-10 19:37:21',0,NULL);
/*!40000 ALTER TABLE `patentes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `procedencias`
--

DROP TABLE IF EXISTS `procedencias`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `procedencias` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `procedencias`
--

LOCK TABLES `procedencias` WRITE;
/*!40000 ALTER TABLE `procedencias` DISABLE KEYS */;
INSERT INTO `procedencias` VALUES (1,'PROC2');
/*!40000 ALTER TABLE `procedencias` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `product` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `alias` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `id_company` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKfnnww0qeerpruunjakntgnn4p` (`id_company`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `productos`
--

DROP TABLE IF EXISTS `productos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productos` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `ultimo_movimiento` datetime DEFAULT NULL,
  `acumulado` decimal(9,2) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productos`
--

LOCK TABLES `productos` WRITE;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` VALUES (1,'PROD1','2',NULL,NULL),(2,'PROD113','miAlias',NULL,NULL),(3,'PROD222','miAlias',NULL,1.00),(4,'PROD444','miAlias',NULL,1.00),(5,'PROD111','miAlias',NULL,1.00),(6,'PROD111','miAlias',NULL,1.00);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profile`
--

DROP TABLE IF EXISTS `profile`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `id_company` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqe82rubh5ukqst0f0s30hc81g` (`id_company`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profile`
--

LOCK TABLES `profile` WRITE;
/*!40000 ALTER TABLE `profile` DISABLE KEYS */;
/*!40000 ALTER TABLE `profile` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `remito_field`
--

DROP TABLE IF EXISTS `remito_field`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `remito_field` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `dato` varchar(100) NOT NULL,
  `pos_x` varchar(10) DEFAULT NULL,
  `pos_y` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=104 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `remito_field`
--

LOCK TABLES `remito_field` WRITE;
/*!40000 ALTER TABLE `remito_field` DISABLE KEYS */;
INSERT INTO `remito_field` VALUES (89,'Fecha','1.0','1.0'),(90,'Denominación','1.0','2.0'),(91,'Domicilio','1.0','3.0'),(92,'Localidad','1.0','4.0'),(93,'Provincia','1.0','5.0'),(94,'Cuit','1.0','6.0'),(95,'Conductor','1.0','7.0'),(96,'Conductor DNI','1.0','8.0'),(97,'Chasis','1.0','9.0'),(98,'Acoplado','1.0','10.0'),(99,'Transporte','1.0','11.0'),(100,'Producto','0.0','0.0'),(101,'Peso Entrada','1.0','13.0'),(102,'Peso Salida','1.0','14.0'),(103,'Peso Neto','1.0','15.0');
/*!40000 ALTER TABLE `remito_field` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports`
--

DROP TABLE IF EXISTS `reports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reports` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tara_id` int(11) NOT NULL,
  `count` int(11) NOT NULL DEFAULT '1',
  `id_tara` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_reports_tara_idx` (`tara_id`),
  CONSTRAINT `FK_reports_tara` FOREIGN KEY (`tara_id`) REFERENCES `taras` (`idtaras`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports`
--

LOCK TABLES `reports` WRITE;
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
INSERT INTO `reports` VALUES (1,1,2,NULL);
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `taras`
--

DROP TABLE IF EXISTS `taras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `taras` (
  `idtaras` int(11) NOT NULL AUTO_INCREMENT,
  `transaccion` varchar(45) DEFAULT NULL,
  `fecha_entrada` datetime DEFAULT NULL,
  `fecha_salida` datetime DEFAULT NULL,
  `balanza` varchar(45) DEFAULT NULL,
  `id_producto` int(11) DEFAULT NULL,
  `id_cliente` int(11) DEFAULT NULL,
  `id_transporte` int(11) DEFAULT NULL,
  `id_procedencia` int(11) DEFAULT NULL,
  `id_imp_exp` int(11) DEFAULT NULL,
  `modalidad` varchar(45) DEFAULT NULL,
  `comprobante_nun1` varchar(45) DEFAULT NULL,
  `modoTara` varchar(45) DEFAULT NULL,
  `destino` varchar(255) DEFAULT NULL,
  `conductor` varchar(255) DEFAULT NULL,
  `tipo_doc` varchar(3) DEFAULT NULL,
  `num_doc` varchar(45) DEFAULT NULL,
  `patente` varchar(45) DEFAULT NULL,
  `patente_aceptado` varchar(45) DEFAULT NULL,
  `observacion` varchar(255) DEFAULT NULL,
  `observacion_aduana` varchar(255) DEFAULT NULL,
  `contenedor_num` varchar(45) DEFAULT NULL,
  `peso_entrada` decimal(9,3) DEFAULT NULL,
  `peso_salida` decimal(9,3) DEFAULT NULL,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modoChasis` varchar(45) DEFAULT NULL,
  `id_ata` int(11) DEFAULT NULL,
  `contenedor` varchar(45) DEFAULT NULL,
  `manifiesto` varchar(45) DEFAULT NULL,
  `mercaderia` varchar(45) DEFAULT NULL,
  `nacionalidad` varchar(255) DEFAULT NULL,
  `ata` tinyblob,
  `cliente` tinyblob,
  `imp_exp` tinyblob,
  `modo_chasis` varchar(255) DEFAULT NULL,
  `modo_tara` varchar(255) DEFAULT NULL,
  `peso_neto` decimal(19,2) DEFAULT NULL,
  `procedencias` tinyblob,
  `producto` tinyblob,
  `transporte` tinyblob,
  PRIMARY KEY (`idtaras`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `taras`
--

LOCK TABLES `taras` WRITE;
/*!40000 ALTER TABLE `taras` DISABLE KEYS */;
INSERT INTO `taras` VALUES (1,'1','2021-07-12 09:09:53','2021-07-15 21:12:46','ING. MANUAL',1,NULL,NULL,NULL,NULL,'ESTANDAR','','NORMAL',NULL,'1231',NULL,'1321','ASD','1231','',NULL,NULL,22.000,3131.000,'2021-07-12 07:09:54','COMPLETO',NULL,NULL,NULL,'','123123',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(2,'2','2021-07-15 21:13:33','2021-07-15 21:13:40','ING. MANUAL',1,1,1,1,NULL,'ESTANDAR','3311','NORMAL',NULL,'31',NULL,'123','ASDA','333','',NULL,NULL,1231.000,444.000,'2021-07-15 19:13:34','COMPLETO',NULL,NULL,NULL,'','1233',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(3,'3','2021-07-20 19:35:43','2021-07-20 19:41:17','INT1',1,1,1,1,NULL,'ESTANDAR','','NORMAL',NULL,'',NULL,'','123WSDE','','',NULL,NULL,0.000,0.000,'2021-07-20 17:35:43','POR EJE',NULL,NULL,NULL,'','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL),(4,'4','2021-07-20 19:41:59',NULL,'INT1',1,NULL,NULL,NULL,NULL,'ESTANDAR','','NORMAL',NULL,'',NULL,'','313','','',NULL,NULL,12312.000,NULL,'2021-07-20 17:41:59','COMPLETO',NULL,NULL,NULL,'','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `taras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transport`
--

DROP TABLE IF EXISTS `transport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transport` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `cuit` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `id_company` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKm4h5r9of0xf415tby9b8gnan6` (`id_company`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transport`
--

LOCK TABLES `transport` WRITE;
/*!40000 ALTER TABLE `transport` DISABLE KEYS */;
/*!40000 ALTER TABLE `transport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `transportes`
--

DROP TABLE IF EXISTS `transportes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transportes` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `CUIT` varchar(45) DEFAULT NULL,
  `ultimo_movimiento` datetime DEFAULT NULL,
  `acumulado` decimal(9,2) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `transportes`
--

LOCK TABLES `transportes` WRITE;
/*!40000 ALTER TABLE `transportes` DISABLE KEYS */;
INSERT INTO `transportes` VALUES (1,'TRA1','CUITTRA',NULL,NULL);
/*!40000 ALTER TABLE `transportes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `account_expired` bit(1) NOT NULL,
  `account_locked` bit(1) NOT NULL,
  `created` datetime NOT NULL,
  `enabled` bit(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `password_expired` bit(1) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_authority`
--

DROP TABLE IF EXISTS `user_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_authority` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `authority` varchar(255) NOT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_authority`
--

LOCK TABLES `user_authority` WRITE;
/*!40000 ALTER TABLE `user_authority` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_token`
--

DROP TABLE IF EXISTS `user_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `date_created` datetime NOT NULL,
  `refresh_token` varchar(255) NOT NULL,
  `revoked` bit(1) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_token`
--

LOCK TABLES `user_token` WRITE;
/*!40000 ALTER TABLE `user_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `clave` varchar(100) NOT NULL,
  `id_perfil` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'admin','123456',1),(2,'op1','123456',3),(3,'sup2','123456',2);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `versionado`
--

DROP TABLE IF EXISTS `versionado`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `versionado` (
  `idversionado` int(11) NOT NULL AUTO_INCREMENT,
  `numero_sql` int(11) NOT NULL,
  `update_sql` datetime DEFAULT NULL,
  PRIMARY KEY (`idversionado`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `versionado`
--

LOCK TABLES `versionado` WRITE;
/*!40000 ALTER TABLE `versionado` DISABLE KEYS */;
INSERT INTO `versionado` VALUES (1,1,'2021-05-22 18:14:05'),(2,2,'2021-05-22 18:14:05'),(3,3,'2021-05-22 18:14:05'),(5,4,'2021-06-22 22:45:41'),(6,5,'2021-07-05 19:41:55'),(8,6,'2021-07-08 17:08:45'),(9,7,'2021-07-15 20:52:23'),(10,8,'2021-07-15 22:32:32');
/*!40000 ALTER TABLE `versionado` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'sist_pesada'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-20 21:19:09

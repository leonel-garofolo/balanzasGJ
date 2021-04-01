-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.5.62 - MySQL Community Server (GPL)
-- SO del servidor:              Win32
-- HeidiSQL Versión:             11.2.0.6213
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


-- Volcando estructura de base de datos para sist_pesada
CREATE DATABASE IF NOT EXISTS `sist_pesada` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sist_pesada`;

-- Volcando estructura para tabla sist_pesada.ata
CREATE TABLE IF NOT EXISTS `ata` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `CUIT` varchar(45) DEFAULT NULL,
  `ultimo_movimiento` datetime DEFAULT NULL,
  `acumulado` decimal(9,2) DEFAULT NULL,
  `nacionalidad` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.ata: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `ata` DISABLE KEYS */;
INSERT INTO `ata` (`codigo`, `nombre`, `CUIT`, `ultimo_movimiento`, `acumulado`, `nacionalidad`) VALUES
	(3, 'BALANZAS FULL SERVICE', '30715342215', NULL, NULL, 'ARGENTINA');
/*!40000 ALTER TABLE `ata` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.clientes
CREATE TABLE IF NOT EXISTS `clientes` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `CUIT` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.clientes: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` (`codigo`, `nombre`, `CUIT`) VALUES
	(4, 'BFS ARGENTINA', NULL);
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.comunicaciones
CREATE TABLE IF NOT EXISTS `comunicaciones` (
  `idcomunicaciones` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `idindicadores` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcomunicaciones`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.comunicaciones: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `comunicaciones` DISABLE KEYS */;
INSERT INTO `comunicaciones` (`idcomunicaciones`, `nombre`, `idindicadores`) VALUES
	(1, 'INDICADOR 1', 2);
/*!40000 ALTER TABLE `comunicaciones` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.ejes
CREATE TABLE IF NOT EXISTS `ejes` (
  `idEjes` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nroEje` int(11) DEFAULT NULL,
  `peso_entrada` decimal(9,2) DEFAULT NULL,
  `peso_salida` decimal(9,2) DEFAULT NULL,
  `idTaras` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEjes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.ejes: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `ejes` DISABLE KEYS */;
/*!40000 ALTER TABLE `ejes` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.importadores_exportadores
CREATE TABLE IF NOT EXISTS `importadores_exportadores` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `CUIT` varchar(45) DEFAULT NULL,
  `ultimo_movimiento` datetime DEFAULT NULL,
  `acumulado` decimal(9,2) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.importadores_exportadores: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `importadores_exportadores` DISABLE KEYS */;
INSERT INTO `importadores_exportadores` (`codigo`, `nombre`, `CUIT`, `ultimo_movimiento`, `acumulado`) VALUES
	(2, 'BALANZAS FULL SERVICE SRL', '30715342215', NULL, NULL);
/*!40000 ALTER TABLE `importadores_exportadores` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.indicadores
CREATE TABLE IF NOT EXISTS `indicadores` (
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
  PRIMARY KEY (`idindicadores`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.indicadores: ~2 rows (aproximadamente)
/*!40000 ALTER TABLE `indicadores` DISABLE KEYS */;
INSERT INTO `indicadores` (`idindicadores`, `nombre`, `posicion_caracter_control`, `longitud_caracter_control`, `caracter_control`, `posicion_inicio_dato`, `longitud_dato`, `puerto`, `velocidad`, `bits_de_datos`, `paridad`, `control_de_flujo`, `bits_parada`) VALUES
	(1, 'MORETTI', 1, 3, '*0', 4, 5, 3, 2400, 7, 'e', '0 - comNone', '1'),
	(2, 'GSE 350', 1, 1, '02', 3, 7, 3, 9600, 8, 'n', '0 - comNone', '1');
/*!40000 ALTER TABLE `indicadores` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.operaciones
CREATE TABLE IF NOT EXISTS `operaciones` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.operaciones: ~0 rows (aproximadamente)
/*!40000 ALTER TABLE `operaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `operaciones` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.parametros_globales
CREATE TABLE IF NOT EXISTS `parametros_globales` (
  `id` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  `valueByte` longblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.parametros_globales: ~21 rows (aproximadamente)
/*!40000 ALTER TABLE `parametros_globales` DISABLE KEYS */;
INSERT INTO `parametros_globales` (`id`, `value`, `valueByte`) VALUES
	('ACTIVAR_DEBUG', 'false', NULL),
	('CERTIFICADO', 'GSGFHFGHR46', NULL),
	('CODIGO_ADUANA', 'DFDGDF43556', NULL),
	('CODIGO_LOG', '45434DSGF', NULL),
	('EMPRESA_DIR', 'MISIONES 1011', NULL),
	('EMPRESA_DIR_BAL', 'LEFLEFLEFL', NULL),
	('EMPRESA_EMAIL', 'VENTAS@FULLSERVICEBALANZAS.COM.AR', NULL),
	('EMPRESA_EMAIL_BAL', 'AAA@GMAIL.COM', NULL),
	('EMPRESA_LOC', 'SAN LORENZO', NULL),
	('EMPRESA_LOC_BAL', 'KDFKDFKDFK', NULL),
	('EMPRESA_NOMBRE', 'BALANZAS FULL SERVICE SRL', NULL),
	('EMPRESA_NOMBRE_BAL', 'PEPE', NULL),
	('EMPRESA_PROV', 'SANTA FE', NULL),
	('EMPRESA_PROV_BAL', 'LSFLMEGLMG', NULL),
	('EMPRESA_TEL', '0341 153538781 // 03476 15535441', NULL),
	('EMPRESA_TEL_BAL', 'LDFLEMF3445', NULL),
	('EMPRESA_TRANSACCION', '6', NULL),
	('TICKET_ETIQUETADORA', 'false', NULL),
	('VALIDACION_ENTRADA', '', NULL),
	('VALIDACION_SALIDA', '7,8,9,10', NULL),
	('VENCIMIENTO', 'DFGSG545', NULL);
/*!40000 ALTER TABLE `parametros_globales` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.patentes
CREATE TABLE IF NOT EXISTS `patentes` (
  `codigo` varchar(45) NOT NULL,
  `tara` double NOT NULL,
  `date_update` datetime NOT NULL,
  `dias` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.patentes: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `patentes` DISABLE KEYS */;
INSERT INTO `patentes` (`codigo`, `tara`, `date_update`, `dias`) VALUES
	('12121', 0, '2021-03-25 12:38:19', 0),
	('123121', 0, '2021-03-25 12:07:51', 0),
	('AB530ZF', 0, '2021-02-19 17:51:14', 0),
	('AC017IT', 0, '2021-02-17 09:33:30', 0);
/*!40000 ALTER TABLE `patentes` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.procedencias
CREATE TABLE IF NOT EXISTS `procedencias` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.procedencias: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `procedencias` DISABLE KEYS */;
INSERT INTO `procedencias` (`codigo`, `nombre`) VALUES
	(2, 'ROSARIO');
/*!40000 ALTER TABLE `procedencias` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.productos
CREATE TABLE IF NOT EXISTS `productos` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `ultimo_movimiento` datetime DEFAULT NULL,
  `acumulado` decimal(9,2) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.productos: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` (`codigo`, `nombre`, `alias`, `ultimo_movimiento`, `acumulado`) VALUES
	(5, 'SOJA', NULL, NULL, NULL);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.reports
CREATE TABLE IF NOT EXISTS `reports` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tara_id` int(11) NOT NULL,
  `count` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FK_reports_tara_idx` (`tara_id`),
  CONSTRAINT `FK_reports_tara` FOREIGN KEY (`tara_id`) REFERENCES `taras` (`idtaras`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.reports: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `reports` DISABLE KEYS */;
INSERT INTO `reports` (`id`, `tara_id`, `count`) VALUES
	(1, 28, 5),
	(2, 29, 4),
	(3, 30, 2),
	(4, 31, 3),
	(5, 32, 2);
/*!40000 ALTER TABLE `reports` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.taras
CREATE TABLE IF NOT EXISTS `taras` (
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
  PRIMARY KEY (`idtaras`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.taras: ~5 rows (aproximadamente)
/*!40000 ALTER TABLE `taras` DISABLE KEYS */;
INSERT INTO `taras` (`idtaras`, `transaccion`, `fecha_entrada`, `fecha_salida`, `balanza`, `id_producto`, `id_cliente`, `id_transporte`, `id_procedencia`, `id_imp_exp`, `modalidad`, `comprobante_nun1`, `modoTara`, `destino`, `conductor`, `tipo_doc`, `num_doc`, `patente`, `patente_aceptado`, `observacion`, `observacion_aduana`, `contenedor_num`, `peso_entrada`, `peso_salida`, `update_date`, `modoChasis`, `id_ata`, `contenedor`, `manifiesto`, `mercaderia`, `nacionalidad`) VALUES
	(28, '1', '2021-02-17 09:33:47', '2021-02-17 09:33:52', 'ING. MANUAL', 5, 4, NULL, 2, 2, 'ADUANA', 'AFKKDGRHM', 'NORMAL', 'DFEGOJG46563', 'LEONEL ALFONSO', NULL, '3424556342', 'AC017IT', 'AB530ZF', 'OBS', 'OBS 2', '12434543', 15000.000, 45000.000, '2021-02-17 09:33:48', 'COMPLETO', 3, 'ODFOJRGR445', 'NDFNKDGNM3EF', NULL, 'ARGENTINO'),
	(29, '2', '2021-02-17 10:34:49', '2021-02-17 10:34:54', 'ING. MANUAL', 5, 4, NULL, 2, 2, 'ADUANA', 'R45235', 'NORMAL', 'LLSDD3545FLF', 'LEONEL ALFONSO', NULL, '23333222', 'AC017IT', 'AB530ZF', 'LALALALA', 'LALA232323', '1433', 14600.000, 44580.000, '2021-02-17 10:34:49', 'COMPLETO', 3, 'CONTENEDOR 1', 'LEFONEFI', 'EREMKNEG', 'ARGENTINA'),
	(30, '3', '2021-02-19 17:51:30', '2021-02-19 17:51:36', 'ING. MANUAL', 5, 4, 1, 2, NULL, 'ESTANDAR', '', 'NORMAL', NULL, 'LEO', NULL, '11222122', 'AB530ZF', '', '', NULL, NULL, 45000.000, 15000.000, '2021-02-19 17:51:30', 'COMPLETO', NULL, NULL, NULL, NULL, 'ASFASF'),
	(31, '4', '2021-03-25 11:47:35', '2021-03-25 11:49:33', 'ING. MANUAL', 5, 4, 1, 2, 2, 'ADUANA', '21354534', 'NORMAL', 'KAMSDFKN35452', 'LEONEL ALFONSO', NULL, '1234341', 'AC017IT', 'AB530ZF', 'OBS 1', 'OBS2', '123453', 15000.000, 20000.000, '2021-03-25 11:47:36', 'COMPLETO', 3, 'X345452DFD', 'ASFKKN3R23', 'KANDFDFE', 'ARGENTINO'),
	(32, '5', '2021-03-25 12:08:02', '2021-03-25 12:08:32', 'ING. MANUAL', 5, 4, 1, 2, NULL, 'ADUANA', '', 'NORMAL', '', '', NULL, '', '123121', '', '', '', '', 2000.000, 6000.000, '2021-03-25 12:08:02', 'COMPLETO', NULL, '', '', '', ''),
	(33, '6', '2021-03-25 12:38:28', '2021-03-25 14:39:11', 'ING. MANUAL', 5, 4, 1, 2, NULL, 'ESTANDAR', '', 'NORMAL', NULL, '', NULL, '', '12121', '', '', NULL, NULL, 6000.000, 6004.000, '2021-03-25 12:38:28', 'COMPLETO', NULL, NULL, NULL, '', '');
/*!40000 ALTER TABLE `taras` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.transportes
CREATE TABLE IF NOT EXISTS `transportes` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `CUIT` varchar(45) DEFAULT NULL,
  `ultimo_movimiento` datetime DEFAULT NULL,
  `acumulado` decimal(9,2) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.transportes: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `transportes` DISABLE KEYS */;
INSERT INTO `transportes` (`codigo`, `nombre`, `CUIT`, `ultimo_movimiento`, `acumulado`) VALUES
	(1, 'PEPE', NULL, NULL, NULL);
/*!40000 ALTER TABLE `transportes` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.usuarios
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `clave` varchar(100) NOT NULL,
  `id_perfil` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.usuarios: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`id`, `nombre`, `clave`, `id_perfil`) VALUES
	(1, 'admin', '123456', 1);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.versionado
CREATE TABLE IF NOT EXISTS `versionado` (
  `idversionado` int(11) NOT NULL AUTO_INCREMENT,
  `numero_sql` int(11) NOT NULL,
  `update_sql` datetime DEFAULT NULL,
  PRIMARY KEY (`idversionado`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.versionado: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `versionado` DISABLE KEYS */;
INSERT INTO `versionado` (`idversionado`, `numero_sql`, `update_sql`) VALUES
	(1, 1, '2021-02-17 09:29:37'),
	(2, 2, '2021-02-17 09:29:37'),
	(3, 3, '2021-02-17 09:29:38');
/*!40000 ALTER TABLE `versionado` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;

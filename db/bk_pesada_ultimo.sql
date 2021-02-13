-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.7.26 - MySQL Community Server (GPL)
-- SO del servidor:              Win64
-- HeidiSQL Versión:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

SET SQL_SAFE_UPDATES = 0;
-- Volcando estructura de base de datos para sist_pesada
DROP DATABASE IF EXISTS `sist_pesada`;
CREATE DATABASE IF NOT EXISTS `sist_pesada` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sist_pesada`;

-- Volcando estructura para tabla sist_pesada.ata
DROP TABLE IF EXISTS `ata`;
CREATE TABLE IF NOT EXISTS `ata` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `CUIT` varchar(45) DEFAULT NULL,
  `ultimo_movimiento` datetime DEFAULT NULL,
  `acumulado` decimal(9,2) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.ata: ~0 rows (aproximadamente)
DELETE FROM `ata`;
/*!40000 ALTER TABLE `ata` DISABLE KEYS */;
INSERT INTO `ata` (`codigo`, `nombre`, `CUIT`, `ultimo_movimiento`, `acumulado`) VALUES
	(1, 'ATA2', '15191', NULL, NULL),
	(2, 'ATA3', 'asda221', NULL, NULL);
/*!40000 ALTER TABLE `ata` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.clientes
DROP TABLE IF EXISTS `clientes`;
CREATE TABLE IF NOT EXISTS `clientes` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.clientes: ~3 rows (aproximadamente)
DELETE FROM `clientes`;
/*!40000 ALTER TABLE `clientes` DISABLE KEYS */;
INSERT INTO `clientes` (`codigo`, `nombre`) VALUES
	(1, 'CLIENTE1'),
	(2, 'TEST2'),
	(3, 'AAAA');
/*!40000 ALTER TABLE `clientes` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.comunicaciones
DROP TABLE IF EXISTS `comunicaciones`;
CREATE TABLE IF NOT EXISTS `comunicaciones` (
  `idcomunicaciones` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `idindicadores` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcomunicaciones`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.comunicaciones: ~5 rows (aproximadamente)
DELETE FROM `comunicaciones`;
/*!40000 ALTER TABLE `comunicaciones` DISABLE KEYS */;
INSERT INTO `comunicaciones` (`idcomunicaciones`, `nombre`, `idindicadores`) VALUES
	(1, 'INDICADOR #1', 8),
	(11, 'INDICADOR #1', 6),
	(12, 'INDICADOR #1', 7),
	(13, 'INDICADOR #1', 7),
	(14, 'INDICADOR #1', 7);
/*!40000 ALTER TABLE `comunicaciones` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.ejes
DROP TABLE IF EXISTS `ejes`;
CREATE TABLE IF NOT EXISTS `ejes` (
  `idEjes` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `nroEje` int(11) DEFAULT NULL,
  `peso_entrada` decimal(9,2) DEFAULT NULL,
  `peso_salida` decimal(9,2) DEFAULT NULL,
  `idTaras` int(11) DEFAULT NULL,
  PRIMARY KEY (`idEjes`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.ejes: ~0 rows (aproximadamente)
DELETE FROM `ejes`;
/*!40000 ALTER TABLE `ejes` DISABLE KEYS */;
/*!40000 ALTER TABLE `ejes` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.importadores_exportadores
DROP TABLE IF EXISTS `importadores_exportadores`;
CREATE TABLE IF NOT EXISTS `importadores_exportadores` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `CUIT` varchar(45) DEFAULT NULL,
  `ultimo_movimiento` datetime DEFAULT NULL,
  `acumulado` decimal(9,2) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.importadores_exportadores: ~0 rows (aproximadamente)
DELETE FROM `importadores_exportadores`;
/*!40000 ALTER TABLE `importadores_exportadores` DISABLE KEYS */;
INSERT INTO `importadores_exportadores` (`codigo`, `nombre`, `CUIT`, `ultimo_movimiento`, `acumulado`) VALUES
	(1, 'LASDA', '2131', NULL, NULL);
/*!40000 ALTER TABLE `importadores_exportadores` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.indicadores
DROP TABLE IF EXISTS `indicadores`;
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
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.indicadores: ~7 rows (aproximadamente)
DELETE FROM `indicadores`;
/*!40000 ALTER TABLE `indicadores` DISABLE KEYS */;
INSERT INTO `indicadores` (`idindicadores`, `nombre`, `posicion_caracter_control`, `longitud_caracter_control`, `caracter_control`, `posicion_inicio_dato`, `longitud_dato`, `puerto`, `velocidad`, `bits_de_datos`, `paridad`, `control_de_flujo`, `bits_parada`) VALUES
	(1, 'EL05', 8, 1, '13', 1, 7, 1, 9600, 8, 'n', '0 - comNone', '2'),
	(3, 'GSE 350', 2, 1, '02', 3, 7, 1, 1200, 8, 'n', '0 - comNone', '1'),
	(4, 'PESAR 3400', 1, 1, '58', 11, 5, 1, 1200, 8, 'n', '0 - comNone', '1'),
	(5, 'SENSORTRONICS 2002', 1, 1, '02', 3, 7, 1, 1200, 8, 'n', '1 - comXOnXoff', '1'),
	(6, 'KAIO', 2, 1, '+', 3, 9, 1, 9600, 8, 'n', '0 - comNone', '1'),
	(7, 'AA', 1, 1, '', 7, 5, 1, 9600, 8, 'n', '0 - comNone', '1'),
	(8, 'BBB', 1, 1, '1', 5, 9, 1, 9600, 8, 'n', '0 - comNone', '1');
/*!40000 ALTER TABLE `indicadores` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.operaciones
DROP TABLE IF EXISTS `operaciones`;
CREATE TABLE IF NOT EXISTS `operaciones` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.operaciones: ~0 rows (aproximadamente)
DELETE FROM `operaciones`;
/*!40000 ALTER TABLE `operaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `operaciones` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.parametros_globales
DROP TABLE IF EXISTS `parametros_globales`;
CREATE TABLE IF NOT EXISTS `parametros_globales` (
  `id` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  `valueByte` longblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.parametros_globales: ~20 rows (aproximadamente)
DELETE FROM `parametros_globales`;
/*!40000 ALTER TABLE `parametros_globales` DISABLE KEYS */;
INSERT INTO `parametros_globales` (`id`, `value`, `valueByte`) VALUES
	('CERTIFICADO', 'C', NULL),
	('CODIGO_ADUANA', 'A', NULL),
	('CODIGO_LOG', 'B', NULL),
	('EMPRESA_AUTOMATICO', '13:30', NULL),
	('EMPRESA_BACKUP', 'D:\\backupPCWiener', NULL),
	('EMPRESA_DIR', 'COLOMBIA 936', NULL),
	('EMPRESA_DIR_BAL', 'BB', NULL),
	('EMPRESA_ING_MANUAL', '123456', NULL),
	('EMPRESA_LOC', 'ROSARIO', NULL),
	('EMPRESA_LOC_BAL', 'CC', NULL),
	('EMPRESA_NOMBRE', 'CJ BALANZAS', NULL),
	('EMPRESA_NOMBRE_BAL', 'AA', NULL),
	('EMPRESA_PROV', 'SANTA FÉ', NULL),
	('EMPRESA_PROV_BAL', 'DD', NULL),
	('EMPRESA_RESTORE', 'D:\\workspace\\balanzasGJ\\src\\main\\resources\\images\\icono\\icono.jpg', NULL),
	('EMPRESA_TEL', '(341)3553810  \\ 4560954', NULL),
	('EMPRESA_TEL_BAL', 'EE', NULL),
	('EMPRESA_TICKET', 'TICKET', NULL),
	('EMPRESA_TRANSACCION', '71', NULL),
	('VENCIMIENTO', 'AA', NULL);
/*!40000 ALTER TABLE `parametros_globales` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.patentes
DROP TABLE IF EXISTS `patentes`;
CREATE TABLE IF NOT EXISTS `patentes` (
  `codigo` varchar(45) NOT NULL,
  `tara` double NOT NULL,
  `date_update` datetime NOT NULL,
  `dias` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.patentes: ~11 rows (aproximadamente)
DELETE FROM `patentes`;
/*!40000 ALTER TABLE `patentes` DISABLE KEYS */;
INSERT INTO `patentes` (`codigo`, `tara`, `date_update`, `dias`) VALUES
	('AAA111', 0, '2019-12-28 16:49:30', 0),
	('AAA121', 0, '2019-12-20 17:07:30', 0),
	('AAA211', 222, '2019-12-20 20:57:37', 3),
	('AAA221', 5003, '2019-12-20 20:53:57', 2),
	('AAA444', 0, '2019-12-20 16:58:32', 3),
	('AAA888', 3000, '2019-12-20 20:58:18', 2),
	('JDZ444', 0, '2019-12-24 09:10:22', 0),
	('JDZ777', 0, '2019-12-03 08:23:38', 0),
	('JDZ778', 0, '2019-11-26 21:16:23', 0),
	('JDZ779', 0, '2019-12-03 08:46:48', 0),
	('OOO11', 0, '2019-12-20 16:47:58', 0),
	('OOO111', 0, '2019-12-20 16:48:16', 0);
/*!40000 ALTER TABLE `patentes` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.procedencias
DROP TABLE IF EXISTS `procedencias`;
CREATE TABLE IF NOT EXISTS `procedencias` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.procedencias: ~0 rows (aproximadamente)
DELETE FROM `procedencias`;
/*!40000 ALTER TABLE `procedencias` DISABLE KEYS */;
INSERT INTO `procedencias` (`codigo`, `nombre`) VALUES
	(1, 'PROD');
/*!40000 ALTER TABLE `procedencias` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.productos
DROP TABLE IF EXISTS `productos`;
CREATE TABLE IF NOT EXISTS `productos` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `alias` varchar(255) DEFAULT NULL,
  `ultimo_movimiento` datetime DEFAULT NULL,
  `acumulado` decimal(9,2) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.productos: ~4 rows (aproximadamente)
DELETE FROM `productos`;
/*!40000 ALTER TABLE `productos` DISABLE KEYS */;
INSERT INTO `productos` (`codigo`, `nombre`, `alias`, `ultimo_movimiento`, `acumulado`) VALUES
	(1, 'PROD1', NULL, NULL, NULL),
	(2, 'PROD3', '16516', NULL, NULL),
	(3, 'PROD2', NULL, NULL, NULL),
	(4, 'AAA', 'BBB', NULL, NULL);
/*!40000 ALTER TABLE `productos` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.taras
DROP TABLE IF EXISTS `taras`;
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
  `contenedor_num` varchar(45) DEFAULT NULL,
  `peso_entrada` decimal(9,3) DEFAULT NULL,
  `peso_salida` decimal(9,3) DEFAULT NULL,
  `update_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modoChasis` varchar(45) DEFAULT NULL,
  `id_ata` int(11) DEFAULT NULL,
  `contenedor` varchar(45) DEFAULT NULL,
  `manifiesto` varchar(45) DEFAULT NULL,
  `mercaderia` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idtaras`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.taras: ~26 rows (aproximadamente)
DELETE FROM `taras`;
/*!40000 ALTER TABLE `taras` DISABLE KEYS */;
INSERT INTO `taras` (`idtaras`, `transaccion`, `fecha_entrada`, `fecha_salida`, `balanza`, `id_producto`, `id_cliente`, `id_transporte`, `id_procedencia`, `id_imp_exp`, `modalidad`, `comprobante_nun1`, `modoTara`, `destino`, `conductor`, `tipo_doc`, `num_doc`, `patente`, `patente_aceptado`, `observacion`, `contenedor_num`, `peso_entrada`, `peso_salida`, `update_date`, `modoChasis`, `id_ata`, `contenedor`, `manifiesto`, `mercaderia`) VALUES
	(1, '45', '2019-11-26 21:16:00', '2019-11-26 21:19:31', 'ING. MANUAL', 1, 1, 1, 1, NULL, 'ESTANDAR', '', 'NORMAL', NULL, '', NULL, '', 'JDZ778', NULL, '', NULL, 1000.000, 2000.000, '2019-11-26 21:16:52', 'COMPLETO', NULL, NULL, NULL, NULL),
	(2, '46', '2019-12-03 08:20:00', '2019-12-24 09:21:44', 'KAIO', 1, 1, 1, 1, NULL, 'ESTANDAR', '', 'NORMAL', NULL, '', NULL, '', 'JDZ778', NULL, '', NULL, 1000.000, 300.000, '2019-12-03 08:21:10', 'COMPLETO', NULL, NULL, NULL, NULL),
	(3, '47', '2019-12-03 08:29:00', '2019-12-24 09:10:01', 'KAIO', 1, 1, 1, 1, NULL, 'ADUANA', '', 'NORMAL', NULL, '', NULL, '', 'JDZ777', NULL, '', NULL, 1000.000, 2000.000, '2019-12-03 08:30:46', 'COMPLETO', NULL, NULL, NULL, NULL),
	(4, '48', '2019-12-03 08:46:00', '2019-12-03 08:47:53', 'ING. MANUAL', 1, 2, 1, 1, 1, 'ADUANA', '', 'NORMAL', NULL, 'ASDASD', NULL, '', 'JDZ779', NULL, '', NULL, 1000.000, 2000.000, '2019-12-03 08:47:31', 'COMPLETO', NULL, NULL, NULL, NULL),
	(5, '49', '2019-12-20 17:07:57', '2019-12-20 19:42:23', 'KAIO', 1, 1, 1, 1, NULL, 'ESTANDAR', '', 'CON TARA', NULL, '', NULL, '', 'AAA221', NULL, '', NULL, 2079.000, -1.000, '2019-12-20 17:07:58', 'COMPLETO', NULL, NULL, NULL, NULL),
	(6, '48', '2019-12-24 09:10:36', '2019-12-24 09:10:43', 'KAIO', 1, 1, 1, 1, 1, 'ADUANA', '', 'NORMAL', NULL, 'LEO', NULL, '', 'JDZ444', NULL, '', NULL, 2000.000, 3000.000, '2019-12-24 09:10:36', 'COMPLETO', NULL, NULL, NULL, NULL),
	(7, '51', '2019-12-24 09:27:32', '2019-12-24 09:27:58', 'KAIO', 1, 1, 1, 1, NULL, 'ESTANDAR', '', 'NORMAL', NULL, '', NULL, '', 'JDZ444', NULL, '', NULL, 200.000, 300.000, '2019-12-24 09:27:32', 'COMPLETO', NULL, NULL, NULL, NULL),
	(8, '52', '2019-12-24 09:27:44', '2019-12-24 09:30:22', 'KAIO', 1, 1, 1, 1, NULL, 'ESTANDAR', '', 'NORMAL', NULL, '', NULL, '', 'JDZ778', NULL, '', NULL, 0.000, 300.000, '2019-12-24 09:27:45', 'COMPLETO', NULL, NULL, NULL, NULL),
	(9, '54', '2019-12-24 09:30:02', '2019-12-24 09:32:22', 'KAIO', 1, 1, 1, 1, NULL, 'ESTANDAR', '', 'NORMAL', NULL, '', NULL, '', 'JDZ444', NULL, '', NULL, 5000.000, 6000.000, '2019-12-24 09:30:03', 'COMPLETO', NULL, NULL, NULL, NULL),
	(10, '54', '2019-12-24 09:31:14', '2019-12-24 10:35:59', 'KAIO', 1, 1, 1, 1, NULL, 'ESTANDAR', '', 'NORMAL', NULL, '', NULL, '', 'JDZ778', NULL, '', NULL, 5000.000, 5004.000, '2019-12-24 09:31:14', 'COMPLETO', NULL, NULL, NULL, NULL),
	(11, '55', '2019-12-24 09:33:05', '2019-12-24 09:33:44', 'KAIO', 1, 1, 2, 1, NULL, 'ESTANDAR', '', 'NORMAL', NULL, '', NULL, '', 'AAA221', NULL, '', NULL, 6000.000, 7000.000, '2019-12-24 09:33:05', 'COMPLETO', NULL, NULL, NULL, NULL),
	(12, '56', '2019-12-24 09:33:31', '2019-12-24 09:34:31', 'KAIO', 1, 1, 1, 1, NULL, 'ESTANDAR', '', 'NORMAL', NULL, '', NULL, '', 'JDZ777', NULL, '', NULL, 400.000, 8000.000, '2019-12-24 09:33:31', 'COMPLETO', NULL, NULL, NULL, NULL),
	(13, '57', '2019-12-24 09:34:16', '2019-12-24 10:28:14', 'KAIO', 2, 1, 1, 1, NULL, 'ESTANDAR', '', 'NORMAL', NULL, '', NULL, '', 'JDZ444', NULL, '', NULL, 222.000, 223.000, '2019-12-24 09:34:16', 'COMPLETO', NULL, NULL, NULL, NULL),
	(14, '58', '2019-12-24 09:36:08', '2019-12-24 10:26:49', 'KAIO', 1, 1, 1, 1, 1, 'ADUANA', '', 'NORMAL', NULL, '', NULL, '', 'JDZ777', NULL, '', NULL, 3000.000, 3001.000, '2019-12-24 09:36:08', 'COMPLETO', NULL, NULL, NULL, NULL),
	(15, '59', '2019-12-24 10:02:00', '2019-12-24 10:02:06', 'KAIO', 1, 1, 1, 1, NULL, 'ESTANDAR', '', 'NORMAL', NULL, '', NULL, '', 'AAA221', NULL, '', NULL, 5000.000, 6000.000, '2019-12-24 10:02:00', 'COMPLETO', NULL, NULL, NULL, NULL),
	(16, '60', '2019-12-24 10:07:13', '2019-12-24 10:26:21', 'KAIO', 1, 1, 1, 1, NULL, 'ESTANDAR', '', 'NORMAL', NULL, '', NULL, '', 'AAA221', NULL, '', NULL, 3000.000, 4001.000, '2019-12-24 10:07:13', 'COMPLETO', NULL, NULL, NULL, NULL),
	(17, '61', '2019-12-24 10:37:22', '2019-12-24 10:37:35', 'KAIO', 1, 1, 1, 1, NULL, 'ESTANDAR', '', 'NORMAL', NULL, '', NULL, '', 'JDZ778', NULL, '', NULL, 500.560, 6000.850, '2019-12-24 10:37:23', 'COMPLETO', NULL, NULL, NULL, NULL),
	(18, '62', '2019-12-24 11:09:23', '2019-12-24 11:34:52', 'KAIO', 1, 1, 1, 1, NULL, 'ESTANDAR', '', 'NORMAL', NULL, '', NULL, '', 'JDZ778', NULL, '', NULL, 3000.000, 5006.000, '2019-12-24 11:09:24', 'COMPLETO', NULL, NULL, NULL, NULL),
	(19, '63', '2019-12-24 11:09:55', '2019-12-24 11:30:03', 'KAIO', 1, 1, 1, 1, NULL, 'ESTANDAR', '', 'NORMAL', NULL, '', NULL, '', 'AAA221', NULL, '', NULL, 3000.000, 4568.000, '2019-12-24 11:09:55', 'COMPLETO', NULL, NULL, NULL, NULL),
	(20, '64', '2019-12-24 11:11:37', '2019-12-24 11:28:06', 'KAIO', 1, 1, 1, 1, NULL, 'ESTANDAR', '', 'NORMAL', NULL, '', NULL, '', 'JDZ777', NULL, '', NULL, 5000.000, 6000.000, '2019-12-24 11:11:37', 'COMPLETO', NULL, NULL, NULL, NULL),
	(21, '65', '2019-12-24 11:30:28', '2019-12-24 11:34:35', 'KAIO', 1, 1, 1, 1, NULL, 'ESTANDAR', '', 'NORMAL', NULL, '', NULL, '', 'AAA221', NULL, '', NULL, 5000.000, 6000.000, '2019-12-24 11:30:28', 'COMPLETO', NULL, NULL, NULL, NULL),
	(22, '66', '2019-12-27 21:51:27', '2019-12-28 14:30:05', 'BBB', 1, 1, 1, 1, 1, 'ADUANA', '', 'NORMAL', NULL, 'LEONEL', NULL, '31631073', 'JDZ778', NULL, '', NULL, 3000.000, 4000.000, '2019-12-27 21:51:27', 'COMPLETO', NULL, NULL, NULL, NULL),
	(23, '67', '2019-12-28 12:41:41', '2019-12-28 14:29:58', 'BBB', 1, 1, 1, 1, NULL, 'ESTANDAR', '', 'NORMAL', NULL, '', NULL, '', 'JDZ777', NULL, '', NULL, 5000.000, 6000.000, '2019-12-28 12:41:41', 'COMPLETO', NULL, NULL, NULL, NULL),
	(24, '68', '2019-12-28 16:35:39', '2019-12-28 16:36:59', 'BBB', 1, 1, 1, 1, 1, 'ADUANA', '', 'NORMAL', NULL, '', NULL, '', 'JDZ778', NULL, '', NULL, 2000.000, 3000.000, '2019-12-28 16:35:40', 'COMPLETO', NULL, NULL, NULL, NULL),
	(25, '69', '2019-12-28 16:46:18', NULL, 'BBB', 1, 1, 2, 1, 1, 'ADUANA', '', 'NORMAL', NULL, '', NULL, '', 'JDZ777', NULL, '', NULL, 5000.000, NULL, '2019-12-28 16:46:18', 'COMPLETO', 1, 'AAAAA', 'CCCC', NULL),
	(26, '70', '2019-12-28 16:49:44', '2019-12-28 17:29:33', 'BBB', 1, 1, 1, 1, 1, 'ADUANA', '', 'NORMAL', NULL, 'LEONEL GAROFOLO', NULL, '31631073', 'AAA111', NULL, '', NULL, 2500.000, 300.000, '2019-12-28 16:49:45', 'COMPLETO', 1, 'AAAAA', 'CCCC', NULL),
	(27, '71', '2019-12-28 17:30:19', NULL, 'BBB', 1, 1, 2, 1, 1, 'ADUANA', 'SS', 'NORMAL', 'CCCCA1', 'LEONEL GAROFOLO', NULL, '31631073', 'AAA111', NULL, 'SS', NULL, 2211.000, NULL, '2019-12-28 17:30:20', 'COMPLETO', 1, 'ASDQQ', 'QDQ', '2EDDA');
/*!40000 ALTER TABLE `taras` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.transportes
DROP TABLE IF EXISTS `transportes`;
CREATE TABLE IF NOT EXISTS `transportes` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  `CUIT` varchar(45) DEFAULT NULL,
  `ultimo_movimiento` datetime DEFAULT NULL,
  `acumulado` decimal(9,2) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.transportes: ~2 rows (aproximadamente)
DELETE FROM `transportes`;
/*!40000 ALTER TABLE `transportes` DISABLE KEYS */;
INSERT INTO `transportes` (`codigo`, `nombre`, `CUIT`, `ultimo_movimiento`, `acumulado`) VALUES
	(1, 'TRANS1', '1315615', NULL, NULL),
	(2, 'ASDASD', NULL, NULL, NULL);
/*!40000 ALTER TABLE `transportes` ENABLE KEYS */;

-- Volcando estructura para tabla sist_pesada.usuarios
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `clave` varchar(100) NOT NULL,
  `id_perfil` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Volcando datos para la tabla sist_pesada.usuarios: ~4 rows (aproximadamente)
DELETE FROM `usuarios`;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` (`id`, `nombre`, `clave`, `id_perfil`) VALUES
	(1, 'admin', '123456', 1),
	(4, 'supervisor1', '123456', 2),
	(5, 'operador1', '123456', 3),
	(6, 'operador2', '123456', 3);
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

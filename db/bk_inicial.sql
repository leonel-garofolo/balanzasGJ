-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         10.1.38-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             10.2.0.5599
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para sist_pesada
DROP DATABASE IF EXISTS `sist_pesada`;
CREATE DATABASE IF NOT EXISTS `sist_pesada` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `sist_pesada`;

-- Volcando estructura para tabla sist_pesada.clientes
DROP TABLE IF EXISTS `clientes`;
CREATE TABLE IF NOT EXISTS `clientes` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla sist_pesada.comunicaciones
DROP TABLE IF EXISTS `comunicaciones`;
CREATE TABLE IF NOT EXISTS `comunicaciones` (
  `idcomunicaciones` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `idindicadores` int(11) DEFAULT NULL,
  PRIMARY KEY (`idcomunicaciones`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.

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

-- La exportación de datos fue deseleccionada.

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

-- La exportación de datos fue deseleccionada.

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
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla sist_pesada.operaciones
DROP TABLE IF EXISTS `operaciones`;
CREATE TABLE IF NOT EXISTS `operaciones` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla sist_pesada.parametros_globales
DROP TABLE IF EXISTS `parametros_globales`;
CREATE TABLE IF NOT EXISTS `parametros_globales` (
  `id` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  `valueByte` longblob,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla sist_pesada.patentes
DROP TABLE IF EXISTS `patentes`;
CREATE TABLE IF NOT EXISTS `patentes` (
  `codigo` varchar(45) NOT NULL,
  `tara` double NOT NULL,
  `date_update` datetime NOT NULL,
  `dias` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla sist_pesada.procedencias
DROP TABLE IF EXISTS `procedencias`;
CREATE TABLE IF NOT EXISTS `procedencias` (
  `codigo` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`codigo`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.

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

-- La exportación de datos fue deseleccionada.

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
  PRIMARY KEY (`idtaras`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.

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

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla sist_pesada.usuarios
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE IF NOT EXISTS `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `clave` varchar(100) NOT NULL,
  `id_perfil` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;

INSERT INTO `usuarios` (`id`, `nombre`, `clave`, `id_perfil`) VALUES (1, 'admin', '123456', 1);

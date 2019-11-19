/*0.0.2*/
alter table parametros_goblales add valueByte LONGBLOB default null;

/*1.0.3*/
CREATE TABLE importadores_exportadores (
  codigo INT(11) NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(255) NULL,
  CUIT VARCHAR(45) NULL,
  ultimo_movimiento DATETIME NULL,
  acumulado DECIMAL(9,2) NULL,
  PRIMARY KEY (codigo));
  

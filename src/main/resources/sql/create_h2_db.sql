/*
   H2 test database schema creation 
 */

/* Create table for entity AjustesDeStocks */
CREATE TABLE ajustes_de_stocks (
codigo IDENTITY AUTO_INCREMENT NOT NULL,
nombre VARCHAR ,
existencia INTEGER ,
PRIMARY KEY(codigo)
);

/* Create table for entity Clientes */
CREATE TABLE clientes (
codigo IDENTITY AUTO_INCREMENT NOT NULL,
nombre VARCHAR ,
PRIMARY KEY(codigo)
);

/* Create table for entity Operaciones */
CREATE TABLE operaciones (
codigo IDENTITY AUTO_INCREMENT NOT NULL,
nombre VARCHAR ,
PRIMARY KEY(codigo)
);

/* Create table for entity Procedencias */
CREATE TABLE procedencias (
codigo IDENTITY AUTO_INCREMENT NOT NULL,
nombre VARCHAR ,
PRIMARY KEY(codigo)
);

/* Create table for entity Productos */
CREATE TABLE productos (
codigo IDENTITY AUTO_INCREMENT NOT NULL,
nombre VARCHAR ,
existencia INTEGER ,
PRIMARY KEY(codigo)
);

/* Create table for entity Transportes */
CREATE TABLE transportes (
codigo IDENTITY AUTO_INCREMENT NOT NULL,
nombre VARCHAR ,
PRIMARY KEY(codigo)
);


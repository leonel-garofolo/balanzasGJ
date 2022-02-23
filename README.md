#Sistema de Balanzas


##PreRequerimientos

Instalar las siguientes herramientas:
1. Eclipse
2. Gestor de librerias, [Maven](https://maven.apache.org/)
3. [Git](https://git-scm.com/downloads) y para gestionar GIT [Source Tree](https://www.sourcetreeapp.com/)
4. Instalar base de datos Mysql o instalar [Docker Desktop](https://www.docker.com/products/docker-desktop) y ejecutar en la raiz del proyecto **docker-compose up**
5. Herramienta de Gestion de base de datos [DBeaver](https://dbeaver.io/)
6. [iReport](https://community.jaspersoft.com/project/ireport-designer), para ver los reportes (*.jrxml) 
    o mejor instala el plugin del eclipse que va mejor, o por lo menos lo tenes todo integrado en el IDE
7. Web para transferir las versiones [WebTransfer](https://wetransfer.com/)
8. [InnoSetup](https://jrsoftware.org/isinfo.php), levantas el script que esta en el proyecto en /innoSetup/sistemaDePesaje.iss


##Preparación de entorno

1.Ejecutar comando docker en el path del proyecto   

```
docker-compose up
```

2.Configurar el path de JDK del fichero **pom.xml** variable properties.jdk con el path local de la pc   
3.Ejecutar en la clase [App.java](./src/main/java/com/balanzasgj/app/App.java) para levantar la aplicación.



## Estructura del proyecto


#### Paquetes (src/main/java)

**conn.serial** 

Clases encargada de realizar la comunicacion con el puerto para recibir la informacion de los indicadores

**db**

Utilidades basicas para establecer la conexion a la base de datos

**informes**

Generadores de reportes que se generan dinamicamente. No tienen relacion con los ficheros .jrxml

**model**

Objetos de dominio del negocio de la aplicación. Solo contiene getters y setters

**persistence**

Contiene los objetos DAOs (Data Access Object) encargadas de transformar los objetos de las vistas (views) con los tipos de datos correspondientes para la base de datos (mysql)

**quartz**

Gestiona la configuración de cronos para ejecuciones automaticas de backup de la base de datos.

**service**

Interfaces e implementaciones para realizar las transformaciones y procedimientos necesarios para la ejecución de los DAOs

**utils**

Utilidades como parsers, visualización de informes, exportación de pdf, etc

**view**

Controladores de los formularios encargados de la logica de cada una de las pantallas. Cada controlador esta asociado a un fichero (fxml, parte estatica de la pantalla)

#### Recursos (src/main/resource)
En esta carpeta contiene todos los recursos necesarios para que la aplicaciones funcione. Lo mas importante es jdbc.properties que tiene la información a conexion a base de datos. Ademas de las carpetas descriptas a continuación:


**fxml**

Ficheros que contienen la estructura estatica de cada pantalla. Estan construidas con JavaFx.

**images**

Imagenes utilizadas en el proyecto

**jrxml**

Contiene los reportes que fueron realizados con JasperReport (IDE iReport o plugin de JasperReport para eclipse)

**styles**

main.css, funciona como estilo css pero con la nomenclatura de estilos de JavaFx


##Simulación de comunicación

Para la simulación se utilizan dos programas, Virtual serial port para simular dos puertos COM, y Comm para enviar un pesaje a travez del puerto serial simulado. Estas dos aplicaciones estan dentro de /tools/Comm.zip

##Generar versión

1. Incrementar numero de version en archivos [pom.xml](./pom.xml) y fichero de [sistemaPesaje.iss](innoSetup/sistemaDePesaje.iss)
2. Generar paquete ejecutando el comando maven

```
mvn compile package install
```

3. Copiar archivo generado balanzasGJ-v<version>.jar en la carpeta de inno setup C:\innoSetup 


##Librerias
- [Quartz](http://www.quartz-scheduler.org/)
- [JasperReport](https://community.jaspersoft.com/project/jasperreports-library)


##Recursos
- [Iconos Utilizados](https://www.flaticon.es/categorias)




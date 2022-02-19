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
Ejecutar comando docker en el path del proyecto

```
docker-compose up
```

Configurar el path de JDK del fichero **pom.xml** variable properties.jdk con el path local de la pc.



##Estructura del proyecto



##Generar versión
1. Incrementar numero de version en archivos [pom.xml](./pom.xml) y fichero de innoSetup/sistemaDePesaje.iss
1. Generar paquete ejecutando el comando maven: **mvn compile package install**
2. Copiar archivo generado balanzasGJ-v<version>.jar en la carpeta de inno setup D:\innoSetup 


##Recursos
- [Iconos Utilizados](https://www.flaticon.es/categorias)



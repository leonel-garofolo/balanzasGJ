set path=%path%;C:\SistemaDePesaje\mysql\bin
mysql -uroot -p1234 -e "CREATE DATABASE IF NOT EXISTS sist_pesada;"
mysql -uroot -p1234 sist_pesada < C:\SistemaDePesaje\mysql\bk_inicial.sql
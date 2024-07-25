CREATE USER 'Charlie'@'localhost' IDENTIFIED BY '0823';

CREATE DATABASE testdb;
GRANT ALL PRIVILEGES ON cliente_persona_db.* TO 'Charlie'@'localhost';

CREATE DATABASE cliente_persona_db;
GRANT ALL PRIVILEGES ON cliente_persona_db.* TO 'Charlie'@'localhost';

CREATE DATABASE cuenta_movimientos_db;
GRANT ALL PRIVILEGES ON cuenta_movimientos_db.* TO 'Charlie'@'localhost';

FLUSH PRIVILEGES;

#Contenido de cada tabla se crea al correr el backend
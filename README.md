# neoris_ejtecnico
Ejercicio técnico complejidad semisenior

# Instrucciones de ejecución.
1. Crear las bases de datos mediante el script Creacion_DB.sql.
2. Iniciar Docker con el servicio de Rabbit para permitir la comunicación:
  cmd
  docker login
  docker pull rabbitmq:3-management
  docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
  docker ps
3. Ejecutar ambos proyectos

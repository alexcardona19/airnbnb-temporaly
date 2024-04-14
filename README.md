# airnbnb-temporaly
Este readme te proporcionara un paso a paso de como ejecutar en un entorno local el proyecto
<h1 align="center">Sistema de alquileres temporales</h1>
## Requisitos Previos

Asegúrate de tener instalado lo siguiente en tu máquina:

- Java JDK 17
- gradle
- PostgreSQL

# Base de datos 
Es importante que se ejecuten estas queries antes de lanzar el proyecto para poder que se ejecute de forma exitosa
- CREATE USER prueba WITH ENCRYPTED PASSWORD 'pruebapass';

- GRANT ALL PRIVILEGES ON DATABASE postgres TO prueba;

## Paso 1: Clonar el Repositorio

Clona el repositorio de la aplicación desde GitHub:
git clone <url_del_repositorio>

##Paso 2 : Ejcutar Proyecto
Abre el repositorio, da click derecho sobre AirbnbApplication, luego clickea sobre Run

## Paso 3: Ejecuta 2 Queries más
Luego de compilar el proyecto por primera vez , se debe ejecuutar estas 2 queries antes de empezar a probar el CRUD
- GRANT ALL PRIVILEGES ON TABLE properties TO prueba;
- GRANT USAGE, SELECT ON SEQUENCE properties_id_seq TO prueba;





  



# employee-vaccination-inventory
Technical Challenge 

Actualmente se encuentra en la rama Master todo el proyecto. Es necesario en primer lugar iniciar con el Script (ScriptDB.sql) de creación de base de datos que está en el directorio base.

Se podría realizar de una manera más limpia, ejecutando la primera vez el proyecto en el fichero src/main/resourser/application.properties cambiar a "create" la propiedad "spring.jpa.hibernate.ddl-auto", y posteriormente cambiarla por "update" (Hay como automatizarlo por cuestión tiempo no se realizó).
Lo que sí es necesario es ingresar los valores seteados en BD como el usuario administrador y las vacunas.
Este Script al final posee un fichero de inserción del primer Administrador para poder usar y generar un JWT como cabecera para poder implementar la seguridad OAUTH 2.0 Se desarrolló una simulación de login, con ayuda de las cabeceras de Autorización para que los datos viajen encriptados como en un entorno real.

Además el proyecto posee una arquitectura limpia hexagonal, abstrayendo en mini aplicaciones cada una de las funcionalidades del proyecto.

Se puede consultar información de los endpoint realizados en ../swagger-ui/index.html

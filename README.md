# employee-vaccination-inventory
Technical Challenge 

Actualmente se encuentra en la rama Master todo el proyecto.
Es necesario en primer lugar iniciar con el Script (ScriptDB.sql) de creación de base de datos que está en el directorio base.

Este Script al final posee un fichero de inserción del primer Administrador para poder usar y generar un JWT como cabecera para poder implementar la seguridad OAUTH 2.0

Además el proyecto posee una arquitectura limpia hexagonal, abstrayendo en mini aplicaciones cada una de las funcionalidades del proyecto.

Es deseable realizar un logueo con cabeceras encriptadas, por cuestión de tiempo se ha simulado de forma directa, enviando tanto usuario como contraseña en plano como json.

Se puede consultar información de los endpoint realizados en ../swagger-ui/index.html

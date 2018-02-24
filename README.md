# Ejemplo JSF-JAAS

Proyecto de ejemplo para practicar y aprender a usar JSF con JAAS.

El proyecto contiene pruebas para ver el funcionamiento dentro del marco de trabajo del TFG de Ingeniería del Software. Está destinado a ser una aplicación cliente con una conexión a negocio mediante JAX-WS y JAX-RS.

## Entorno

 - **Java** 8
 - **JDK** 1.8.0_152
 - **JSF** 2.2
 - **Mojarra** 2.2.1
 - **PrimeFaces** 6.1
 - **JSTL** 1.2
 - **Apache Tomcat** 8.5.23
 - **Maven** 3.3.9
 - **IntelliJ IDEA** 2017.3
 
 
 ## Funcionamiento
 
 Tiene un pantalla de inicio con dos enlaes a recursos (user.xhtml y admin.xhtml) y 3 roles (hardcodeados para mayor comodidad) (el user, el admin y un superusuario que puede acceder a ambos), si al intentar accede a un recurso no se está logueado te fuerza el login, de ser correcto te permite acceder y hacer logout, si no da error.

# TFG_cliente

Proyecto con el frontend de mi TFG, usa como freamework principal JSF y consume los servicios expuestos por el backend con **JAX-WS** y **JAX-RS** (con la implementación **CXF**) usando servicios **SOAP** y **REST**.

Está montado con **Maven** y se usa **Jenkins** para hacer pruebas iniciales con la Integración Continua y **JUnit** para las pruebas unitarias. 


El proyecto forma parte de mi TFG [Trabajo de Final de Grado](https://hunzagit.github.io/Portfolio-Online/#TFG) de Ingeniería del Software.


## Entorno

 - **Java** 8
 - **JDK** 1.8.0_152
 - **JSF** 2.2
 - **PrimeFaces** 6.1
 - **JSTL** 1.2
 - **Apache Tomcat** 8.5.23
 - **Maven** 3.3.9
 - **Hibernate** 4.2.21
 - **MySQL** 5.0.11
 - **Jenkins** 2.92
 - **IntelliJ IDEA** 2018.1
 

## Branches

La rama **Development** contiene el código que se está desarrollando y que no ha pasado satisfactoriamente todas las pruebas.
La rama **master** contiene el codigo que ha pasado satisfactoriamente las pruebas.

 
## Dependencias

Esta aplicacion depende de la [aplicación backend](https://github.com/hunzaGit/TFG_server) que expone los servicios del negocio con **WSDL** y **REST**;


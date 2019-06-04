# TFG_cliente

Proyecto con el frontend del TFG "Patrones de seguridad software en el contexto de la arquitectura multicapa para la plataforma J2EE", usa como framework principal JSF y consume los servicios expuestos por el backend con **JAX-WS** y **JAX-RS** (con la implementación **CXF**) usando servicios **SOAP** y **REST**.


## Entorno

 - **Java** 8
 - **JDK** 1.8.0_112
 - **JSF** 2.2
 - **PrimeFaces** 6.1
 - **JSTL** 1.2
 - **Apache Tomcat** 9.0
 - **Maven** 3.3.9
 - **Hibernate** 4.2.21
 - **MySQL** 5.0.11
 - **Eclipse** Photon
 

 
## Dependencias

Esta aplicacion depende de la [aplicación backend](https://github.com/sergiomgm/TFG_servidor)que expone los servicios del negocio con **WSDL** y **REST** y de los Security Service Token,disponibles en los repositorios [https://github.com/eduarrom/TFG_STS](https://github.com/eduarrom/TFG_STS) y [https://github.com/eduarrom/TFG_STS2](https://github.com/eduarrom/TFG_STS2).

## Despliegue del proyecto

### Base de datos 

La conexión a la base de datos debe estar configurada de la siguiente manera:

* Usuario: root
* Contraseña: 1234

#### Esquemas necesarios en la BBDD


* TFG_BBDD_SERVER: en este esquema estarán las tablas de las 3 entidades de la aplicación. No es necesario crear ninguna tabla porque JPA las genera.

* TFG_BBDD_TOMCAT: en este esquema se almacenan los usuarios de Tomcat y sus roles para el uso de HTTPS. Las tablas necesarias son:

```sql
CREATE TABLE user_roles
(
  user_name varchar(15) not null,
  role_name varchar(15) not null,
  primary key (user_name, role_name)
)

/* Rol que permite escribir en el servicio web REST (Departamento) */
INSERT INTO TFG_BBDD_TOMCAT.user_roles (user_name, role_name) VALUES ('user', 'write'); 

/* Usuario que permite escribir en el servicio web SOAP (Empleado y proyecto) */
INSERT INTO TFG_BBDD_TOMCAT.user_roles (user_name, role_name) VALUES ('usuario', 'escritura');

CREATE TABLE users
(
  user_name varchar(15) not null
    primary key,
  user_pass varchar(15) not null
)

INSERT INTO TFG_BBDD_TOMCAT.users (user_name, user_pass) VALUES ('user', 'pass');
INSERT INTO TFG_BBDD_TOMCAT.users (user_name, user_pass) VALUES ('usuario', 'contra');
```

* TFG_BBDD_CLIENTE: en este esquema se almacena los usuarios que serán autenticados mediante JAAS. La tabla necesaria es:

```sql
CREATE TABLE usuario
(
  id       int auto_increment
    primary key,
  nombre   text not null,
  email    text not null,
  password text not null,
  rol      int  not null, /* (0: Usuario, 1: Admin, 2: Superuser */
  version  int  null
)

/* Insertar este usuario por comodidad, es el valor que sale por defecto en el formulario
La contraseña MTIzNA== es el resultado de encriptar 1234.
*/
INSERT INTO tfg_bbdd_cliente.usuario (id, nombre, email, password, rol, version) VALUES (1, 'Sergio', 'administrador@gmail.com', 'MTIzNA==', 1, 1);
```

#### Tomcat

Es necesario añadir al server.xml el siguiente Realm:

```xml
<Realm className="org.apache.catalina.realm.JDBCRealm" connectionURL="jdbc:mysql://localhost/TFG_BBDD_TOMCAT?user=root&amp;password=1234" driverName="com.mysql.jdbc.Driver" userRoleTable="user_roles" userTable="users" roleNameCol="role_name" userCredCol="user_pass" userNameCol="user_name"
/>
```
 También hay que añadir a la carpeta $CATALINA_BASE/lib los .jar del driver jdbc. Se pueden descargar [aquí](https://dev.mysql.com/downloads/connector/j/5.1.html).

##### Certificados

Para poder ejecutar la aplicación es necesario desplegar el .war de este proyecto y los .war de los tres proyectos indicados en las dependencias. Para desplegarlo se importan los cuatro proyectos a Eclipse, en el que debe estar instalado Tomcat 9.0. Hay que modificar la *Run configuration* de Tomcat para que tenga acceso a los certificados de servidor y cliente y al fichero _jaas.config_. Se debe añadir lo siguiente:

```
-Djava.security.auth.login.config="/ruta_del_proyecto/jaas.config" -Djavax.net.ssl.trustStore="/ruta_al_almacen/server.keystore" -Djavax.net.ssl.trustStorePassword=password -Djavax.net.ssl.keyStore="/ruta_al_almacen/server.keystore" -Djavax.net.ssl.keyStorePassword=password

-Dcom.sun.management.jmxremote.port=8008 
-Dcom.sun.management.jmxremote.authenticate=false 
-Dcom.sun.management.jmxremote.ssl=false

```

Los certificados de cliente y servidor están disponibles en [Google Drive](https://drive.google.com/drive/folders/1AGJq8t8YJWo6Fdttz-CLgvgYx5L1IPdR?usp=sharing).


### Ejecución

Una vez modificada la *run configuration* y desplegadas las cuatro aplicaciones se puede arrancar Tomcat y accediendo a la URL https://localhost:8443/TFG_cliente se puede acceder a la aplicación.

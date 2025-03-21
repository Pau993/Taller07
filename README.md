# Proyecto: Twitter Clone con Spring Boot y AWS

## Integrantes

- Paula Natalia Paez Vega
- Miguel Camilo Tellez Avila
- John Sebastian Sosa

## Descripción del Proyecto

Este proyecto es un clon básico de Twitter que permite a los usuarios crear publicaciones de hasta 140 caracteres y visualizarlas en un stream público. Se implementa utilizando Spring Boot para el backend y JavaScript para el frontend. El frontend se despliega en Amazon S3 para garantizar su disponibilidad en internet.

---

Para clonar el repositorio y ejecutarlo localmente:

```sh
git clone https://github.com/Pau993/Taller07.git
cd Taller07
git checkout main
mvn clean package
java -jar target/twitter-0.0.1-SNAPSHOT.jar
```

---

## 2. Arquitectura del Proyecto

### 2.1. Arquitectura General

El proyecto sigue un diseño monolítico en Spring Boot con un frontend estático en AWS S3. La aplicación se divide en:

- **Backend (Spring Boot)**: Maneja la lógica de negocio y proporciona una API REST.
- **Frontend (JavaScript)**: Consume la API para mostrar y crear posts.
- **AWS S3**: Aloja el frontend y lo hace accesible públicamente.

### 2.2. Modelo de Datos

- `User`: Representa a un usuario del sistema.
- `Post`: Representa una publicación de hasta 140 caracteres.
- `Stream`: Almacena todos los posts creados.

### 2.3. Diagrama de arquitectura

![image](https://github.com/user-attachments/assets/0e53c26e-5d3e-4ce0-8d0c-1abab00ba260)

El diagrama representa un flujo de interacción entre un usuario y un servicio web que involucra un controlador de Twitter, recursos en servidores y AWS Server Migration Service. A continuación, se detalla el flujo:

* Usuario: Un usuario inicia la interacción desde su navegador web.
* Navegador: El usuario accede a la URL http://localhost:8080, lo que sugiere que está probando el servicio en un entorno local.
* TwitterController: Es un componente del backend que recibe la solicitud del usuario y gestiona la lógica de negocio. Es probable que sea un controlador en una aplicación basada en Spring Boot o similar.
* Recursos: Este componente representa servidores o bases de datos donde se almacenan datos que pueden ser consultados por el TwitterController.
AWS Server Migration Service: Se encarga de gestionar la migración de servidores y recursos hacia la nube de AWS.
* Comunicación HTTP: Finalmente, la información es enviada mediante HTTP a otro servicio o sistema, posiblemente relacionado con Twitter o un servicio externo.

Diagrama de entidades:

```
+--------+       +------+       +---------+
|  User  | <---> | Post | <---> | Stream  |
+--------+       +------+       +---------+
```

### 2.3. Clases Principales

- **`TwitterApplication`**: Clase principal que inicia la aplicación Spring Boot.
- **`TwitterController`**: Controlador que maneja las solicitudes HTTP para crear y obtener posts.
- **`Post`**: Representa un post individual.
- **`User`**: Representa a un usuario de la plataforma.
- **`Stream`**: Contiene la lógica para gestionar el flujo de publicaciones.

---

## 3. Pruebas del Proyecto

### 3.1. Pruebas Manuales

1. Levantar el backend con `java -jar target/twitter-0.0.1-SNAPSHOT.jar`
2. Probar la API con Postman o cURL:
   ```sh
   curl -X POST "http://localhost:8080/twitter/createPost" -H "Content-Type: application/json" -d '{"username":"usuario1","text":"Hola Twitter!"}'
   ```
3. Verificar los posts:
   ```sh
   curl -X GET "http://localhost:8080/twitter/getPosts"
   ```

---

https://github.com/user-attachments/assets/2e734913-0225-4fd7-81df-5fb38f434bac

## 4. Video de la Aplicación Funcionando

https://github.com/user-attachments/assets/13e93789-e0a4-4643-8767-023684cfc3ba

---
## 5. Seguridad con AWS Cognito
1. Crear un usuario en AWS Cognito.
2. Configurar el dominio y redirigir la URL de autenticación.
3. Configurar los permisos de acceso a los endpoints protegidos.
4. Enviar el token desde el frontend en las solicitudes HTTP mediante el header `Authorization: Bearer <token>`.

---
## 6. Despliegue en AWS

### 6.1. Despliegue del Frontend en AWS S3
1. Crear un bucket S3 y habilitar alojamiento web.
2. Subir los archivos del frontend al bucket.
3. Acceder a la URL pública del bucket.

### 6.2. Despliegue del Backend en AWS Lambda
1. Crear una función Lambda para el backend.
2. Subir el archivo `JAR` generado con `mvn clean package`.
3. Configurar el entorno de ejecución en Lambda (Java 17).

https://github.com/user-attachments/assets/992d84cc-572f-4bc9-89dc-49909474def1

---

## 7. Separación en Microservicios
1. Crear tres microservicios independientes:
   - **UserService**: Para gestionar usuarios.
   - **PostService**: Para crear y obtener posts.
   - **StreamService**: Para gestionar el flujo de publicaciones.
Los Microservicios creados se generaron dentro de la carpeta Lambda, para una mejor organización y resdimiento.
2. Utilizar Spring Cloud y AWS Lambda para desplegar cada microservicio.

* UserService:

https://github.com/user-attachments/assets/5ae5914f-c451-4994-b924-abba1a9fc448

* PostService

https://github.com/user-attachments/assets/24a1f6c4-5c5a-44d8-b4c4-cd4a3b17ee55

* StreamService

https://github.com/user-attachments/assets/ff07d82e-6469-44d2-8bc4-4f197328db2c

---

## 8. Pruebas Finales
1. Verificar que la autenticación funcione correctamente.
2. Probar que las publicaciones solo se creen si el token es válido.
3. Asegurar que la aplicación se despliegue y funcione correctamente desde AWS.

---

## 9. Conclusión
Este proyecto implementa un clon básico de Twitter con autenticación segura mediante AWS Cognito y un backend modular desplegado en AWS Lambda. La arquitectura permite escalabilidad y facilita la evolución futura de la aplicación.



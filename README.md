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

---

## 7. Separación en Microservicios
1. Crear tres microservicios independientes:
   - **UserService**: Para gestionar usuarios.
   - **PostService**: Para crear y obtener posts.
   - **StreamService**: Para gestionar el flujo de publicaciones.
2. Utilizar Spring Cloud y AWS Lambda para desplegar cada microservicio.

---

## 8. Pruebas Finales
1. Verificar que la autenticación funcione correctamente.
2. Probar que las publicaciones solo se creen si el token es válido.
3. Asegurar que la aplicación se despliegue y funcione correctamente desde AWS.

---

## 9. Conclusión
Este proyecto implementa un clon básico de Twitter con autenticación segura mediante AWS Cognito y un backend modular desplegado en AWS Lambda. La arquitectura permite escalabilidad y facilita la evolución futura de la aplicación.



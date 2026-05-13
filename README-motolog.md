# 🏍️ Motolog - Gestión Técnica de Motocicletas

**Motolog** es una aplicación web integral diseñada para el seguimiento y mantenimiento preventivo de motocicletas. Permite a los usuarios gestionar su garaje virtual y llevar un registro detallado de cada intervención técnica realizada en sus vehículos.

Desarrollado como Proyecto Final para el Ciclo de Grado Superior en **Desarrollo de Aplicaciones Multiplataforma (2º DAM)**.

## 🚀 Características Principales

* **Gestión de Usuarios:** Registro e inicio de sesión seguro con cifrado de contraseñas.
* **Garaje Virtual:** Alta, baja y consulta de múltiples motocicletas por usuario.
* **Historial de Mantenimiento (CRUD):** Registro completo de servicios (aceite, neumáticos, revisiones) con kilometraje y descripción.
* **Interfaz Dinámica:** Experiencia de usuario fluida mediante comunicación asíncrona (Fetch API).
* **Diseño Responsive:** Adaptado para su uso en dispositivos móviles en entorno de taller.

## 🛠️ Stack Tecnológico

### Backend
* **Java 17/21**
* **Spring Boot 3.2**
* **Spring Security** (Autenticación y cifrado BCrypt)
* **Spring Data JPA** (Persistencia)
* **PostgreSQL** (Base de datos relacional)

### Frontend
* **HTML5 & CSS3** (Diseño moderno y responsive)
* **JavaScript Vanilla** (Lógica de cliente asíncrona)
* **JSON** (Formato de intercambio de datos)

## 📋 Requisitos Previos

Antes de comenzar, asegúrate de tener instalado:
* Java JDK 17 o superior.
* Maven 3.x.
* PostgreSQL 14 o superior.
* Un IDE (IntelliJ IDEA recomendado).

## 🔧 Configuración e Instalación

1.  **Clonar el repositorio:**
    ```bash
    git clone https://github.com/tu-usuario/motolog.git
    cd motolog
    ```

2.  **Configurar la Base de Datos:**
    Crea una base de datos en PostgreSQL llamada `motolog`.
    ```sql
    CREATE DATABASE motolog;
    ```

3.  **Configurar application.properties:**
    Edita `src/main/resources/application.properties` con tus credenciales:
    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/motolog
    spring.datasource.username=tu_usuario
    spring.datasource.password=tu_contraseña
    spring.jpa.hibernate.ddl-auto=update
    ```

4.  **Compilar y Ejecutar:**
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

La aplicación estará disponible en: `http://localhost:8080`

## 📱 Acceso desde Dispositivos Móviles

Para acceder desde un dispositivo en la misma red local:
1. Obtén tu IP local (ej. `192.168.1.XX`).
2. Asegúrate de que el puerto 8080 esté abierto en el Firewall.
3. Accede desde el móvil a: `http://192.168.1.XX:8080/login.html`

## 📄 Licencia

Este proyecto se distribuye bajo la licencia MIT. Consulte el archivo `LICENSE` para más detalles.

---
Desarrollado por [Carlos Prieto Sabell] - 2026

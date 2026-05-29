# DWI_BACKEND
Desarrollo de Backend para Sistema Clínico - Arquitectura RESTful

# Sistema de Gestión de Citas - Centro de Salud Poquian
Actualmente, los centros de salud en Poquian gestionan sus procesos de forma física y manual, lo que genera largas colas, malestar en los pacientes y una saturación del sistema de atención. La falta de un flujo digital impide que el paciente tenga visibilidad de horarios o especialidades disponibles.

## 🎯 Objetivo del Proyecto
Este proyecto propone el desarrollo de un sistema web integral que permite digitalizar el registro de pacientes y la reserva de citas, brindando una gestión más eficiente, ordenada y accesible de los servicios de salud.

## 🚀 Tecnologías utilizadas
**Backend (API REST):**
- Java
- Spring Boot 3 (Spring Web, Spring Data JPA)
- Spring Security con JSON Web Tokens (JWT)
- PostgreSQL (Desplegado en Render)

**Frontend (Cliente):**
- Angular (Desplegado en Vercel)

**Herramientas de Desarrollo:**
- Thunder Client / Postman para pruebas de API
- DBeaver para gestión de base de datos
- Visual Studio Code

## 🏗️ Estructura y Arquitectura del Proyecto

El sistema está diseñado bajo una arquitectura multicapa (N-Tier) y separa claramente el cliente frontend del servidor backend:

- **Models (Entidades):** Representación de las tablas de la base de datos (Usuario, Paciente, Medico, Servicio, Sede, Cita, etc.).
- **Repositories:** Interfaces de Spring Data JPA con consultas JPQL personalizadas.
- **DTOs (Data Transfer Objects):** Separación estricta de los datos de entrada (Request) y salida (Response) para proteger las entidades.
- **Services:** Lógica de negocio transaccional y mapeo de datos.
- **Controllers:** Exposición de la API REST para ser consumida por la aplicación en Angular.
- **Security:** Filtros JWT, encriptación de contraseñas con BCrypt y control de accesos basado en roles (ADMIN, PACIENTE).

## ⚙️ Ejecución del Proyecto en Local

1. Clonar el repositorio.
2. Abrir el proyecto en Visual Studio Code.
3. Asegurarse de que las credenciales de la base de datos en el `application.properties` apunten a la base de datos correcta.
4. El servidor correrá en: `http://localhost:8081`

---

## 📡 Endpoints Principales

El sistema maneja un control de acceso estricto. Las rutas se dividen en Públicas, de Paciente y de Administración.

### 🟢 1. Autenticación (Público - Sin Token)

| Método | Endpoint | Descripción |
|-------|---------|------------|
| POST | `/api/auth/registrar`| Crea un Usuario y su Perfil de Paciente. Devuelve JWT. |
| POST | `/api/auth/iniciarSesion`| Valida credenciales. Devuelve JWT, Rol y Usuario ID. |

### 🟢 2. Catálogos (Público - Sin Token)
*(Utilizados por el Front-End para llenar las listas de selección al agendar citas)*

| Método | Endpoint | Descripción |
|-------|---------|------------|
| GET | `/api/sedes/activas` | Lista las sedes operativas. |
| GET | `/api/servicios/activos` | Lista las especialidades médicas disponibles. |
| GET | `/api/medicos/filtrar` | Trae doctores filtrados por sedeId y servicioId. |
| GET | `/api/horarios/medico/{id}`| Trae los días y horas de atención de un doctor. |

### 🔴 3. Gestión Administrativa (Privado - Requiere Token Rol: ADMIN)

| Módulo | Métodos Soportados | Endpoint Base | Descripción |
|-------|---------|------------|------------|
| **Sedes** | GET, POST, PUT, DELETE | `/api/sedes` | Gestión completa de sedes físicas. |
| **Servicios**| GET, POST, PUT, DELETE | `/api/servicios` | Gestión de especialidades y precios. |
| **Médicos** | GET, POST, PUT, DELETE | `/api/medicos` | Alta/Baja de personal médico. |
| **Horarios** | POST, DELETE | `/api/horarios` | Asignación de bloques de atención. |
| **Pacientes**| GET, GET(id) | `/api/pacientes` | Visualización de la base de pacientes. |
| **Pagos** | GET | `/api/pagos` | Historial financiero y cuadre de caja. |

### 🟡 4. Operaciones de Pacientes (Privado - Requiere Token JWT)

| Método | Endpoint | Descripción |
|-------|---------|------------|
| PUT | `/api/pacientes/{id}` | Actualización de perfil (Teléfono, Correo). |
| POST | `/api/citas` | Creación de una reserva médica. |
| GET | `/api/citas` | Historial de citas del paciente logueado. |
| POST | `/api/pagos` | Procesamiento del pago de una cita. |

---

## 📝 Ejemplos de Peticiones (JSON)

### Registrar un Paciente (`/api/auth/registrar`)
```json
{
  "dni": "77778888",
  "password": "miPasswordSeguro123",
  "nombre": "Jorge",
  "apellido": "Chicana",
  "correo": "jorge.chicana@mail.com",
  "telefono": "987654321"
}
```

### Iniciar Sesión (`/api/auth/iniciarSesion`)
```json
{
  "dni": "77778888",
  "password": "miPasswordSeguro123"
}
```

### Crear Cita (Paciente - `/api/citas`)
```json
{
  "pacienteId": 1,
  "servicioId": 1,
  "medicoId": 1,
  "sedeId": 1,
  "fechaHora": "2026-06-20T14:30:00"
}
```

---

## 📊 Evidencias

Se realizaron pruebas exhaustivas de integración mediante Thunder Client, verificando:
- Autenticación y control de acceso (403 Forbidden para accesos no autorizados).
- Registro correcto de datos relacionales usando validaciones JPA y `@Valid`.
- Persistencia y consistencia de la información en la base de datos en Render.

## 📌 Notas Importantes
- El sistema cuenta con **Soft Delete** (Borrado Lógico) cambiando el campo `estado` a `false` para preservar el historial clínico y financiero.
- Las contraseñas están fuertemente encriptadas en la base de datos usando **BCrypt**.
- Se aplica el uso estricto de DTOs para evitar la sobreexposición de datos de las entidades (Entity Exposure Vulnerability).
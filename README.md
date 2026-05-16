# DWI_BACKEND
Desarrollo_BACKEND de un problema

# Sistema de Gestión de Citas - Centro de Salud Poquian
Actualmente, los centros de salud en Poquian gestionan sus procesos de forma física y manual, lo que genera largas colas, malestar en los pacientes y una saturación del sistema de atención. La falta de un flujo digital impide que el paciente tenga visibilidad de horarios o especialidades disponibles.

## Objetivo del Proyecto
Este proyecto propone el desarrollo de un sistema web utilizando Spring Boot que permite digitalizar el registro de pacientes y la reserva de citas, brindando una gestión más eficiente, ordenada y accesible de los servicios de salud.


## Tecnologías utilizadas
- Java
- Spring Boot
- Thunder Client / Postman
- Visual Studio Code

## Estructura del Proyecto

El sistema está organizado bajo arquitectura por capas:

- *Models:* Contiene las clases Cita, Servicio, paciente y login
- *Services:* Contiene la lógica del sistema
- *Controllers:* Maneja las peticiones HTTP (API REST)
- *resources:* Configuración del proyecto

## Ejecución del Proyecto

1. Clonar el repositorio
2. Abrir el proyecto en Visual Studio Code
3. El servidor correrá en: http://localhost:8081

## Endpoints

### 🔹 Registro

| Método | Endpoint | Descripción |
|-------|---------|------------|
| POST | /api/auth/register| Crear registro |

### 🔹 Login

| Método | Endpoint | Descripción |
|-------|---------|------------|
| POST | /api/auth/login | Crear login |

### 🔹 Paciente

| Método | Endpoint | Descripción |
|-------|---------|------------|
| GET | /api/pacientes | Listar paciente |
| GET | /api/pacientes/{id} | Obtener paciente por id |
| POST | /api/pacientes | Crear paciente |


### 🔹 Servicios

| Método | Endpoint | Descripción |
|-------|---------|------------|
| GET | /api/servicios | Listar servicios |
| GET | /api/servicios/{id} | Obtener servicio por id |
| POST | /api/servicios | Crear servicio |
| PUT | /api/servicios/{id} | Actualizar servicio |
| DELETE | /api/servicios/{id} | Eliminar servicio |

### 🔹 Citas

| Método | Endpoint | Descripción |
|-------|---------|------------|
| GET | /api/citas | Listar citas |
| GET | /api/citas/{id} | Obtener cita por id |
| POST | /api/citas | Crear cita |
| PUT | /api/citas/{id} | Actualizar cita |
| DELETE | /api/citas/{id} | Eliminar cita |



## Ejemplos de uso

### Registrar un Paciente

{
  "dni": "77778888",
  "password": "miPasswordSeguro123",
  "nombre": "Jorge",
  "apellido": "Chicana",
  "correo": "jorge.chicana@mail.com",
  "telefono": "987654321"
}

### Logearse

{
  "dni": "77778888",
  "password": "miPasswordSeguro123"
}

### Crear Servicio
```json
{
  "especialidad": "Pediatria",
  "descripcion": "Atencion infantes",
  "precio": 50.00
}

### Crear Cita

{
 
  "pacienteId": 1,
  "servicioId": 1,
  "fechaHora": "2026-05-20T14:30:00"
}

## Evidencias

Se realizaron pruebas mediante Thunder Client, verificando:

-Creación de servicios
-Registro de citas
-Consulta de información mediante endpoints

## Notas
- El sistema trabaja con almacenamiento en memoria
- Los datos se pierden al reiniciar la aplicación

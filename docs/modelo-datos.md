# Objetivo
Taller virtual para gestionar el mantenimiento de tu motocicleta. 
Este documento define el modelo de datos inicial.

# Entidades

### Usuario
Un usuario puede tener varias motos.
- id
- nombre
- email
- password
- fecha_alta
### Moto
Una moto pertenece a un único usuario.
Una moto puede tener varios mantenimientos.
- id
- marca
- modelo
- cilindrada
- año
- km_actuales
- usuario_id

### Mantenimiento
Un mantenimiento pertenece a una única moto.
- id
- tipo (Enum con "aceite", "frenos", "cadena", etc)
- fecha
- kilómetros
- observaciones
- moto_id

# Reglas
- Un usuario solo puede acceder a sus propias motos.
- No puede existir un mantenimiento sin una moto.
- Los kilómetros de un mantenimiento no pueden ser inferiores a los de la moto.
- El email de usuario debe ser único.

# Operaciones CRUD

### Usuario
- Crear usuario
- Consultar perfil
- Actualizar datos
- Eliminar usuario

### Moto
- Crear moto
- Consultar motos del usuario
- Actualizar moto
- Eliminar moto

### Mantenimiento
- Crear mantenimiento
- Consultar historial
- Actualizar mantenimiento
- Eliminar mantenimiento
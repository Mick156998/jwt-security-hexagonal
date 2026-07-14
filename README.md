# JWT Security Hexagonal - Proyecto Spring Boot

## Estado del Proyecto: ✅ PRIMERA PARTE COMPLETADA

Este es un proyecto educativo que implementa **Spring Security con JWT** siguiendo la **Arquitectura Hexagonal** (Puertos y Adaptadores) usando **Java 21** y **Spring Boot 4.1.0**.

## ✅ Lo que está implementado

### Fase 1: Infraestructura Base
- [x] **Pom.xml** actualizado con Spring Boot 4.1.0, JJWT 0.13.0, Lombok 1.18.46
- [x] **Estructura de carpetas** siguiendo arquitectura hexagonal
- [x] **application.yml** configurado con H2 database, JWT y logging

### Domain Layer (Capa de Dominio)
- [x] **Value Objects**: Email, Password
- [x] **Models**: User, Role, Permission, Token
- [x] **Exceptions**: UserAlreadyExistsException, InvalidRoleException
- [x] **Ports (Input)**: RegisterUserPort
- [x] **Ports (Output)**: UserRepositoryPort, TokenRepositoryPort, PasswordEncoderPort, TokenGeneratorPort

### Application Layer (Capa de Aplicación)
- [x] **DTOs**: RegisterCommand, TokenResult
- [x] **Use Cases**: RegisterUserUseCase (implementa RegisterUserPort)

### Infrastructure Layer (Capa de Infraestructura)
- [x] **REST DTOs**: RegisterRequestDto, TokenResponseDto
- [x] **REST Mapper**: AuthMapper
- [x] **REST Controller**: AuthController (/api/v1/auth/register)
- [x] **Exception Handler**: GlobalExceptionHandler
- [x] **JPA Entities**: UserJpaEntity, TokenJpaEntity
- [x] **JPA Repositories**: UserJpaRepository, TokenJpaRepository
- [x] **Persistence Mapper**: UserPersistenceMapper
- [x] **Adapters**: 
  - UserRepositoryAdapter (implementa UserRepositoryPort)
  - TokenRepositoryAdapter (implementa TokenRepositoryPort)
  - BcryptPasswordAdapter (implementa PasswordEncoderPort)
  - JwtTokenAdapter (implementa TokenGeneratorPort)
- [x] **Configuration**: BeanConfig, SecurityConfig

### Testing
- [x] **Unit Tests**: EmailTest, PasswordTest, RoleTest, RegisterUserUseCaseTest
- [x] **Test Data**: CSV files con datos válidos e inválidos
- [x] **Test Configuration**: application-test.yml

## 📊 Estadísticas del Proyecto

| Categoría | Cantidad |
|-----------|----------|
| Archivos Java (main) | 33 |
| Archivos Java (test) | 4 |
| Archivos de Configuración | 2 (YAML) |
| Archivos de Datos de Prueba | 3 (CSV) |
| **Total** | **42** |

## 🚀 Cómo ejecutar el proyecto

### Compilación
```bash
cd jwt-security-hexagonal
mvn clean compile
```

### Ejecutar la aplicación
```bash
mvn spring-boot:run
```

La aplicación estará disponible en: `http://localhost:9100`

### Ejecutar tests
```bash
# Todos los tests
mvn test

# Tests específicos
mvn test -Dtest=EmailTest
mvn test -Dtest=PasswordTest
mvn test -Dtest=RoleTest
mvn test -Dtest=RegisterUserUseCaseTest
```

## 📡 Endpoints Disponibles

### Registro de Usuario
```bash
POST /api/v1/auth/register
Content-Type: application/json

{
  "name": "Juan Pérez",
  "email": "juan@example.com",
  "password": "password123",
  "role": "CUSTOMER"
}
```

**Respuesta (201 Created):**
```json
{
  "access_token": "...",
  "refresh_token": "...",
  "token_type": "Bearer",
  "expires_in": 3600
}
```

## 📚 Arquitectura

El proyecto sigue el patrón **Hexagonal**:

```
Domain Layer
├── Models (User, Email, Password, Role, Token)
├── Exceptions
└── Ports (Input/Output)

Application Layer
├── DTOs
└── Use Cases

Infrastructure Layer
├── REST (Controllers, DTOs, Mappers)
├── Persistence (Entities, Repositories, Adapters)
├── Security (JWT, Password Encoder)
└── Configuration
```

## 🔐 Características de Seguridad

- ✅ **Contraseñas hasheadas** con BCrypt
- ✅ **JWT Tokens** con JJWT 0.13.0
- ✅ **Roles y Permisos** basados en RBAC
- ✅ **Validación de entrada** con Jakarta Validation
- ✅ **Exception handling** centralizado
- ✅ **H2 Console** habilitada para desarrollo (/h2-console)

## 📝 Próximos Pasos (Ejercicios Prácticos)

El documento instructivo incluye ejercicios adicionales:

1. **Flujo de Login** - Implementar POST /api/v1/auth/login
2. **Refresh Token** - Implementar POST /api/v1/auth/refresh
3. **Validación JWT** - Implementar JwtAuthenticationFilter
4. **Monitoreo** - Integración con Prometheus y Logstash

## 📄 Archivos de Configuración

- **pom.xml**: Dependencias Maven
- **application.yml**: Configuración de Spring (puerto, DB, JWT)
- **application-test.yml**: Configuración para tests
- **copilot-instructions.md**: Guía de arquitectura para IA

## 🛠️ Tecnologías

- Java 21
- Spring Boot 4.1.0
- Spring Security
- JWT (JJWT 0.13.0)
- Lombok 1.18.46
- JUnit 5 + AssertJ
- Mockito
- H2 Database
- Maven

## 📖 Convenciones de Código

- **Paquetes**: Separados por capa (domain, application, infrastructure)
- **Nombres**: English, descriptivos y siguiendo Java conventions
- **Testing**: AAA Pattern (Arrange, Act, Assert)
- **Builders**: Usados en modelos para construcción de objetos
- **Records**: Usados en DTOs
- **Excepciones**: De dominio para lógica de negocio

## ✨ Notas

- Este es un proyecto **educativo** para talleres y capacitación
- Incluye comentarios y estructura clara para facilitar aprendizaje
- Base lista para extensión con Login, Refresh Token, Autorización, etc.

---

**Estado**: Primera Parte del Taller Completada ✅

**Próximo Paso**: Revisar el documento INSTRUCTIVO TALLER.docx para ejercicios prácticos adicionales.

## Flujo de Trabajo
- **Miriam**: crea y levanta el nuevo proyecto.
- **Long**: analiza el proyecto existente e identifica las herramientas y componentes necesarios para su integración. Colabora con Miriam en la incorporación de dichas herramientas al nuevo proyecto.
- **Miguel**: gestiona el repositorio central y realiza las pruebas de los endpoints implementados.
Se validan las integraciones y se documentan los resultados para el cierre de cada iteración. 
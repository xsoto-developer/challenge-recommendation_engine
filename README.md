# El recomendador secreto

**Nivel:** Intermedio

## Descripción
Implementar un sistema básico de recomendaciones basado en compras frecuentes.

## Objetivo
Implementa una solución en Java que cumpla con la lógica descrita. Usa la plantilla en `src/com/walmarttech/Main.java` para comenzar.

## Cómo empezar
1. Clona este repositorio.
2. Dirígete a la carpeta `challenges/week-09-recommendation-engine`.
3. Abre `Main.java` y escribe tu solución.
4. ¡Comparte tu solución con la comunidad!
---
# Reto 09 - El Recomendador Secreto

## Introducción
El **Reto 09 - El Recomendador Secreto** implementa un sistema de recomendaciones de productos basado en la frecuencia de compras dentro de la misma categoría, desarrollado para Walmart-Tech-Mexico. El proyecto incluye dos soluciones dentro de un único repositorio:
- **Solución Simple**: Una implementación ligera en `Main.java` para verificación rápida de la lógica de recomendación, sin dependencias externas.
- **Solución Expandida**: Un microservicio Spring Boot con endpoints RESTful, documentación Swagger y pruebas robustas, diseñado para un entorno empresarial.

Ambas soluciones cumplen con los requisitos del reto: recomendar productos de la misma categoría que el producto de entrada, ordenados por frecuencia de co-compra, excluyendo el producto de entrada y retornando `null` si la categoría no existe. La solución expandida evita datos hardcodeados del servicio, colocándolos únicamente en las pruebas.

## Objetivo
El objetivo es proporcionar un sistema de recomendaciones eficiente y escalable que:
- Implemente la lógica de recomendación en un formato simple para verificación rápida (`Main.java`).
- Ofrezca un microservicio RESTful con endpoints para gestionar productos, categorías, usuarios, compras y recomendaciones, con documentación Swagger.
- Garantice una cobertura de pruebas >80% usando JaCoCo.
- Mantenga datos hardcodeados solo en pruebas, reflejando los ejemplos originales.
- Se aplica principios SOLID y patrones de diseño para un código mantenible y extensible.

## Cambios Realizados
- **Eliminación de Datos Hardcodeados (Solución Expandida)**: En la solución expandida, se removieron los datos iniciales (`productos` y `historial`) de `RecommendationService`, inicializando listas vacías. Los datos de ejemplo originales se hardcodean solo en pruebas (`RecommendationServiceTest.java` y `RecommendationControllerTest.java`).
- **Nuevos Endpoints**: Se agregaron endpoints RESTful para agregar productos, categorías, usuarios y compras, mejorando la flexibilidad del microservicio.
- **Solución Simple**: Reescrita en `Main.java` para consolidar la lógica de recomendación en un solo archivo, usando `record` y `Stream API` para mayor claridad y eficiencia.
- **Pruebas**: Se actualizaron para reflejar datos de ejemplo en tests, asegurando cobertura >80% y validando casos como recomendaciones válidas, productos inexistentes y categorías sin co-compras.

## Arquitectura y Patrones de Diseño

### Estructura del Proyecto
El proyecto se organiza en un único repositorio, con archivos claramente separados para las soluciones simple y expandida:
```
reto-09-recommendation-engine/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── walmarttech/
│   │   │           ├── Main.java                          # Solución Simple: Lógica de recomendación
│   │   │           └── recommendationService/
│   │   │               ├── RecommendationApplication.java  # Solución Expandida: Entrada del microservicio
│   │   │               ├── config/
│   │   │               │   └── SwaggerConfig.java         # Configuración de Swagger
│   │   │               ├── controller/
│   │   │               │   └── RecommendationController.java # Endpoints RESTful
│   │   │               ├── dto/
│   │   │               │   ├── AddCategoriaRequest.java   # DTO para agregar categoría
│   │   │               │   ├── AddCompraRequest.java      # DTO para agregar compra
│   │   │               │   ├── AddProductoRequest.java    # DTO para agregar producto
│   │   │               │   ├── AddUsuarioRequest.java     # DTO para agregar usuario
│   │   │               │   ├── RecomendacionRequest.java  # DTO para solicitud de recomendación
│   │   │               │   └── DatosResponse.java         # DTO para respuesta de datos
│   │   │               ├── model/
│   │   │               │   └── Producto.java              # Modelo de producto
│   │   │               └── service/
│   │   │                   ├── impl/
│   │   │                   │   └── RecomendadorPorFrecuencia.java # Implementación de recomendador
│   │   │                   ├── Recomendador.java           # Interfaz de recomendador
│   │   │                   └── RecommendationService.java  # Lógica de negocio
│   │   └── resources/
│   │       ├── application.yml                            # Configuración del microservicio
│   │       └── logging.properties                        # Configuración de JUL
│   └── test/
│       └── java/
│           └── com/
│               └── walmarttech/
│                   ├── RecomendadorTest.java               # Pruebas para solución simple
│                   └── recommendationService/
│                       ├── controller/
│                       │   └── RecommendationControllerTest.java # Pruebas de integración
│                       └── service/
│                           └── RecommendationServiceTest.java # Pruebas unitarias
└── pom.xml                                                # Dependencias y configuración de build
```

- **Solución Simple**: Abarca `Main.java` (lógica principal y `record Producto`) y `RecomendadorTest.java` (pruebas).
- **Solución Expandida**: Incluye el paquete `com.walmarttech.recommendationService` (archivos de modelo, DTOs, servicios, controladores, configuración) y archivos de recursos (`application.yml`, `logging.properties`).

### Patrones de Diseño
- **Strategy** (Solución Expandida):
    - La interfaz `Recomendador` define el contrato para algoritmos de recomendación.
    - `RecomendadorPorFrecuencia` implementa la lógica basada en frecuencia de co-compras, permitiendo futuras estrategias (filtrado colaborativo) sin modificar código existente.
- **Arquitectura por Capas** (Solución Expandida):
    - Separación de responsabilidades en capas: `controller` (manejo de solicitudes HTTP), `service` (lógica de negocio), `model` (entidades), `dto` (transferencia de datos) y `config` (configuración).
- **Singleton Implícito**: Spring Boot gestiona instancias únicas de servicios y controladores mediante inyección de dependencias.

### Principios SOLID
- **Responsabilidad Única (SRP)**:
    - Simple: `Main.recomendarProductos` se enfoca únicamente en generar recomendaciones.
    - Expandida: Cada clase tiene una única responsabilidad (`RecommendationController` para HTTP, `RecommendationService` para lógica de negocio).
- **Abierto/Cerrado (OCP)**:
    - Simple: La lógica en `Main.java` es extensible mediante refactorización a una interfaz si se permite.
    - Expandida: La interfaz `Recomendador` permite agregar nuevas estrategias de recomendación.
- **Inversión de Dependencias (DIP)**:
    - Expandida: `RecommendationController` depende de la abstracción `RecommendationService`, y este de la interfaz `Recomendador`.
- **Sustitución de Liskov (LSP)** e **Interfaz Segregada (ISP)**:
    - Aplicados implícitamente mediante interfaces enfocadas (`Recomendador`) y DTOs específicos.

## Dependencias Clave
- **`spring-boot-starter-web`**: Proporciona el framework para crear la API RESTful en la solución expandida.
- **`springdoc-openapi-starter-webmvc-ui`**: Genera documentación Swagger y UI para explorar la API.
- **`spring-boot-starter-test`** y **`junit-jupiter`**: Habilitan pruebas unitarias e integración, con `mockito-core` para mocks.
- **Solución Simple**: Solo usa `junit-jupiter` (5.9.2) y `mockito-core` (5.2.0) para pruebas, sin dependencias de runtime.

## Endpoints RESTful
La solución expandida ofrece los siguientes endpoints:

| Método | Endpoint               | Descripción                              | Ejemplo de Cuerpo de Solicitud                        |
|--------|------------------------|------------------------------------------|------------------------------------------------------|
| POST   | `/api/recomendar`     | Devuelve productos recomendados          | `{"productoEntrada": "Camiseta"}`                    |
| GET    | `/api/datos`          | Obtiene productos e historial de compras | Ninguno                                              |
| POST   | `/api/productos`      | Agrega un producto                      | `{"nombre": "Camiseta", "categoria": "Ropa"}`        |
| POST   | `/api/categorias`     | Agrega una categoría                    | `{"nombre": "Ropa"}`                                 |
| POST   | `/api/usuarios`       | Agrega un usuario                       | `{"nombre": "Usuario1"}`                             |
| POST   | `/api/compras`        | Agrega una compra                       | `{"usuario": "Usuario1", "productos": ["Camiseta"]}` |

## Pruebas
- **Solución Simple** (`RecomendadorTest.java`):
    - Casos de prueba:
        - Recomendaciones válidas (e.g., `Camiseta` → `["Pantalón", "Calcetines"]`).
        - Producto inexistente (retorna `null`).
        - Sin recomendaciones (arreglo vacío).
        - Categoría sin co-compras (arreglo vacío).
    - Cobertura: >80%, verificada con JaCoCo.
- **Solución Expandida**:
    - **Pruebas Unitarias** (`RecommendationServiceTest.java`):
        - Hardcodean datos de ejemplo originales (productos: Camiseta, Pantalón, etc.; compras: Usuario1, etc.) usando métodos del servicio (`addProducto`, `addCompra`).
        - Validan lógica de recomendación y recuperación de datos.
    - **Pruebas de Integración** (`RecommendationControllerTest.java`):
        - Usan MockMvc para probar endpoints, poblando datos vía solicitudes POST.
        - Cubren `POST /api/recomendar` y `GET /api/datos`.
    - Cobertura: >80%, verificada en `target/site/jacoco/index.html`.

Los datos de ejemplo originales están hardcodeados solo en las pruebas, asegurando que el servicio sea dinámico y flexible.

## Configuración de Swagger
La solución expandida utiliza `springdoc-openapi-starter-webmvc-ui` para generar documentación automática de la API.

### Desarrollo
- **Acceso a Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **Documentación JSON**: `http://localhost:8080/api-docs`
- **Configuración**: Definida en `SwaggerConfig.java`, habilitando OpenAPI 3.0 para explorar y probar todos los endpoints.
- **Uso**: Permite interactuar con la API, inspeccionar esquemas de solicitud/respuesta y probar endpoints directamente desde el navegador.

## Instrucciones para Probar
1. **Clonar Repositorio**:
   ```bash
   git clone https://github.com/xsoto-developer/challenge-recommendation_engine.git
   ```

2. **Solución Simple**:
   ```bash
   mvn clean install
   mvn test jacoco:report
   java -cp target/challenge-recommendation-engine-1.0-SNAPSHOT.jar com.walmarttech.Main
   ```
    - Verificar cobertura: `target/site/jacoco/index.html`
    - Ejecutar `Main.java` para pruebas locales de la lógica de recomendación.

3. **Solución Expandida**:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```
    - Acceder a Swagger: `http://localhost:8080/swagger-ui.html`
    - Probar endpoints con curl o Postman:
      ```bash
      curl -X POST http://localhost:8080/api/productos -H "Content-Type: application/json" -d '{"nombre":"Camiseta","categoria":"Ropa"}'
      curl -X POST http://localhost:8080/api/usuarios -H "Content-Type: application/json" -d '{"nombre":"Usuario1"}'
      curl -X POST http://localhost:8080/api/compras -H "Content-Type: application/json" -d '{"usuario":"Usuario1","productos":["Camiseta"]}'
      curl -X POST http://localhost:8080/api/recomendar -H "Content-Type: application/json" -d '{"productoEntrada":"Camiseta"}'
      curl http://localhost:8080/api/datos
      ```
    - Ejecutar pruebas: `mvn test jacoco:report`

## Conclusión
El proyecto entrega una solución robusta y estructurada para el Reto 09, con:
- Una **solución simple** en `Main.java` para verificación rápida de la lógica de recomendación, ideal para pruebas locales sin dependencias.
- Una **solución expandida** que ofrece un microservicio Spring Boot escalable, con endpoints RESTful, documentación Swagger y pruebas completas.
- Datos hardcodeados limitados a pruebas, asegurando flexibilidad en el servicio.
- Aplicación de patrones de diseño (Strategy, arquitectura por capas) y principios SOLID para un código mantenible y extensible.
- Cobertura de pruebas >80%, verificada con JaCoCo, garantizando calidad.


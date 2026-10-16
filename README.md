# 🚀 Java Selenium Cucumber E2E Automation Framework

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![Selenium](https://img.shields.io/badge/Selenium-4.18-43B02A?style=for-the-badge&logo=selenium&logoColor=white)](https://www.selenium.dev/)
[![Cucumber](https://img.shields.io/badge/Cucumber-7.15-23D96C?style=for-the-badge&logo=cucumber&logoColor=white)](https://cucumber.io/)
[![TestNG](https://img.shields.io/badge/TestNG-7.9-FF6C37?style=for-the-badge)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)
[![CI/CD Pipeline](https://img.shields.io/badge/CI%2FCD-GitHub%20Actions-2088FF?style=for-the-badge&logo=githubactions&logoColor=white)](https://github.com/features/actions)
[![Allure Reports](https://img.shields.io/badge/Reports-Allure-FF7A00?style=for-the-badge)](https://allurereport.org/)

Framework de automatización de pruebas End-to-End (E2E) robusto, escalable y mantenible construido con **Java 17**, **Selenium 4**, **Cucumber (BDD)** y **TestNG**. Diseñado bajo el patrón de arquitectura **Page Object Model (POM)** sobre la plataforma e-commerce [SauceDemo](https://www.saucedemo.com/), con integración continua automatizada y reportes interactivos.


---

## 🏗️ Arquitectura & Patrones de Diseño

El framework implementa una arquitectura desacoplada para garantizar máxima mantenibilidad y reutilización de código:

```text
src
├── test
│   ├── java
│   │   └── com
│   │       └── crowdar
│   │           ├── pages       <-- Page Object Model (Encapsulamiento de locadores y acciones)
│   │           ├── steps       <-- Step Definitions (Mapeo de pasos Gherkin y Aserciones)
│   │           ├── utils       <-- Lógica auxiliar (PropertyReader)
│   │           └── TestRunner  <-- Orquestador de ejecución TestNG + Cucumber
│   └── resources
│       ├── config.properties   <-- Configuración dinámica de entorno
│       └── features            <-- Escenarios de prueba BDD (Gherkin)
testng.xml                      <-- Suite runner y parametrización de ejecuciones
```
---

## 🌟 Key Features
- **Diseño BDD con Gherkin:** Escenarios de prueba expresados en lenguaje natural, permitiendo colaboración fluida entre perfiles técnicos y de negocio.
- **Page Object Model (POM):** Abstracción total de elementos de la interfaz para reducir duplicación y facilitar la escalabilidad.
- **Data-Driven Testing:** Pruebas parametrizadas para validar múltiples combinaciones de datos (casos positivos y negativos de autenticación).
- **Paridad de Entornos (Local vs CI/CD):** Resolución de desafíos de sincronización en componentes SPA de React mediante inyección de eventos sintéticos, asegurando ejecución estable en Linux Headless (GitHub Actions).
- **Reportes Ejecutivos Allure:** Generación y despliegue automático de métricas y evidencias visuales a través de **GitHub Pages**.

---

## 🧪 Cobertura de Pruebas

| Módulo | Tipo de Test | Criterios de Aceptación / Escenario |
|---|---|---|
| **Autenticación** | `@Smoke` | Inicio de sesión exitoso con credenciales estándar válidas. |
| **Autenticación** | `@Negative` | Validación de bloqueo de cuenta (`locked_out_user`) y credenciales incorrectas. |
| **Flujo Checkout** | `@E2E` | Agregar producto al carro, navegación, llenado dinámico del formulario de envío y confirmación de orden. |

---

## ⚙️ Prerrequisitos

- **Java JDK 17** o superior.
- **Apache Maven 3.8+**.
- Navegador Google Chrome instalado localmente.

---

## 🚀 Instrucciones de Ejecución

### 1. Clonar el repositorio
```bash
git clone https://github.com/BJValdiviezo/java-selenium-cucumber-e2e-framework.git
cd java-selenium-cucumber-e2e-framework
```

### 2. Ejecución Local con Interfaz Gráfica
```bash
mvn test
```

### 3. Ejecución Local Simulando CI/CD (Modo Headless)
```bash
mvn test -Dbrowser=chrome -Dheadless=true
```

---

## 🔄 Integración Continua (CI/CD) & Reportes

El repositorio cuenta con una canalización automatizada en `.github/workflows/` que:
1. Configura el entorno de ejecución Java en un runner Linux.
2. Ejecuta la regresión completa en modo headless.
3. Genera el reporte ejecutivo y actualiza automáticamente la documentación visual en **Allure Report (GitHub Pages)**.

---

## 👤 Autor

**Brian Jesús Valdiviezo**
- **Rol:** QA Automation Engineer
- **LinkedIn:** [linkedin.com/in/brianjvaldiviezo](https://www.linkedin.com/in/brianjvaldiviezo/)
- **Email:** bjvaldiviezo@gmail.com
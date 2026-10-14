# 🚀 Java Selenium Cucumber E2E Automation Framework

[![Java](https://img.shields.io/badge/Java-17-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com/)
[![Selenium](https://img.shields.io/badge/Selenium-4.18-43B02A?style=for-the-badge&logo=selenium&logoColor=white)](https://www.selenium.dev/)
[![Cucumber](https://img.shields.io/badge/Cucumber-7.15-23D96C?style=for-the-badge&logo=cucumber&logoColor=white)](https://cucumber.io/)
[![TestNG](https://img.shields.io/badge/TestNG-7.9-FF6C37?style=for-the-badge)](https://testng.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9-C71A36?style=for-the-badge&logo=apachemaven&logoColor=white)](https://maven.apache.org/)

Framework de automatización de pruebas End-to-End (E2E) robusto, escalable y mantenible construido con **Java 17**, **Selenium 4**, **Cucumber (BDD)** y **TestNG**. Diseñado bajo el patrón de arquitectura **Page Object Model (POM)** sobre la plataforma e-commerce [SauceDemo](https://www.saucedemo.com/).

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

### 🌟 Key Features
- **Diseño BDD con Gherkin:** Escenarios de prueba expresados en lenguaje natural, legibles para perfiles técnicos y funcionales.
- **Page Object Model (POM):** Abstracción total de elementos de la interfaz de usuario para reducir la duplicación de código y facilitar el mantenimiento.
- **Data-Driven Testing:** Pruebas parametrizadas utilizando `Scenario Outline` y `Examples` para validar múltiples combinaciones de datos (casos positivos y negativos).
- **Configuración Dinámica & Cross-Browser:** Lectura centralizada de propiedades vía `config.properties` e inyección de navegador por consola (`-Dbrowser=chrome|firefox|edge`).
- **Gestión Automatizada de Evidencias:** Captura e inyección automática de screenshots (`.png`) en los reportes HTML/JSON únicamente ante fallos mediante `Hooks.java`.

---

## 🧪 Cobertura de Pruebas

| Feature | Tipo | Cobertura / Criterios de Aceptación |
|---|---|---|
| **Autenticación** | `@Smoke` | Inicio de sesión exitoso con credenciales válidas. |
| **Autenticación** | `@Negative` | Validación de mensajes de error parametrizados (`locked_out_user`, `invalid_user`). |
| **Flujo Checkout** | `@E2E` | Selección de productos, navegación al carrito, carga de datos de envío y confirmación de compra. |

---

## ⚙️ Prerrequisitos

- **Java JDK 17** o superior.
- **Apache Maven 3.8+**.
- Navegador Google Chrome, Microsoft Edge o Mozilla Firefox instalado.

---

## 🚀 Instrucciones de Ejecución

### 1. Clonar el repositorio
```bash
git clone https://github.com/BJValdiviezo/java-selenium-cucumber-e2e-framework.git
cd java-selenium-cucumber-e2e-framework
```

### 2. Ejecutar la suite completa por defecto (Chrome)
```bash
mvn test
```

### 3. Ejecución Cross-Browser vía Consola
Podés seleccionar el navegador en tiempo de ejecución sin modificar el código fuente:

```bash
# Ejecutar en Firefox
mvn test -Dbrowser=firefox

# Ejecutar en Edge
mvn test -Dbrowser=edge
```

### 4. Ejecución vía Suite XML (TestNG)
```bash
mvn test -DsuiteXmlFile=testng.xml
```

---

## 📊 Reportes de Pruebas

Al finalizar la ejecución, los reportes detallados se generan automáticamente en:
* **Reporte HTML:** `target/cucumber-reports/cucumber-pretty.html`
* **Reporte JSON:** `target/cucumber-reports/CucumberTestReport.json`

---

## 👤 Autor

**Brian Jesús Valdiviezo**
* **LinkedIn:** [linkedin.com/in/brianjvaldiviezo](https://www.linkedin.com/in/brianjvaldiviezo/)
* **Email:** bjvaldiviezo@gmail.com
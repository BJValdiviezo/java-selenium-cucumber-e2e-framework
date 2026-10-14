Feature: Flujo de compra de productos

  Background:
    Given que el usuario navega a la pagina de inicio
    When ingresa las credenciales de prueba

  @Smoke @E2E
  Scenario: Agregar un producto al carrito y completar la compra con exito
    When agrega el producto "Sauce Labs Backpack" al carrito
    And navega al carrito de compras
    And procede al checkout ingresando "Brian", "Valdiviezo" y "1406"
    Then confirma la orden y verifica el mensaje de agradecimiento "Thank you for your order!"
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

  @Regression
  Scenario: Intentar realizar un checkout con el carrito vacio
    Given que el usuario navega a la pagina de inicio
    When ingresa las credenciales de prueba
    And navega al carrito de compras
    And intenta proceder al checkout sin productos
    Then se verifica que se muestra un mensaje de error en el checkout "Cart is-pack empty" o el boton esta deshabilitado

  @Regression
  Scenario Outline: Ordenar productos por precio de menor a mayor y verificar
    Given que el usuario navega a la pagina de inicio
    When ingresa las credenciales de prueba
    When selecciona el filtro de ordenamiento por precio "<criterio>"
    Then se verifica que los productos estan ordenados correctamente por precio

    Examples:
      | criterio |
      | Price (low to high) |
      | Price (high to low) |

  @Regression
  Scenario: Agregar un producto y removerlo desde el carrito
    Given que el usuario navega a la pagina de inicio
    When ingresa las credenciales de prueba
    And agrega el producto "Sauce Labs Backpack" al carrito
    And navega al carrito de compras
    And remueve el producto del carrito
    Then se verifica que el carrito queda vacio y sin productos listados
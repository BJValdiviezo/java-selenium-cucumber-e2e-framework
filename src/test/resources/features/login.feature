Feature: Autenticacion de usuario en la plataforma

  @Smoke
  Scenario: Iniciar sesion con credenciales validas
    Given que el usuario navega a la pagina de inicio
    When ingresa las credenciales de prueba
    Then se verifica el ingreso correcto al sistema

  @Negative
  Scenario Outline: Intentar iniciar sesion con credenciales invalidas
    Given que el usuario navega a la pagina de inicio
    When ingresa el usuario "<usuario>" y la contrasena "<password>"
    Then se verifica que se muestra el mensaje de error "<mensaje_error>"

    Examples:
      | usuario         | password     | mensaje_error                                                             |
      | locked_out_user | secret_sauce | Epic sadface: Sorry, this user has been locked out.                       |
      | invalid_user    | wrong_pass   | Epic sadface: Username and password do not match any user in this service |
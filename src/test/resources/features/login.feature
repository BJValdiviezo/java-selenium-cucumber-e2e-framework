Feature: Autenticacion de usuario en la plataforma

  Scenario: Iniciar sesion con credenciales validas
    Given que el usuario navega a la pagina de inicio
    When ingresa las credenciales de prueba
    Then se verifica el ingreso correcto al sistema
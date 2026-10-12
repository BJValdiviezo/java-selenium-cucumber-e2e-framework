package com.crowdar.steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebDriver;

public class LoginSteps {
    @Given("que el usuario navega a la pagina de inicio")
    public void queElUsuarioNavegaALaPaginaDeInicio() {
        WebDriver driver = Hooks.getDriver();
        driver.get("https://www.saucedemo.com/");
    }

    @When("ingresa las credenciales de prueba")
    public void ingresaLasCredencialesDePrueba() {
        // En el siguiente paso vincularemos el Page Object Model
        System.out.println("Ingresando credenciales de prueba...");
    }

    @Then("se verifica el ingreso correcto al sistema")
    public void seVerificaElIngresoCorrectoAlSistema() {
        WebDriver driver = Hooks.getDriver();
        System.out.println("URL actual: " + driver.getCurrentUrl());
    }
}

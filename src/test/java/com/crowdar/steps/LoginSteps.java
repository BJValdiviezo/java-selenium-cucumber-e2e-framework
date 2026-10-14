package com.crowdar.steps;

import com.crowdar.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginSteps {

    private LoginPage loginPage = new LoginPage(Hooks.getDriver());

    @Given("que el usuario navega a la pagina de inicio")
    public void queElUsuarioNavegaALaPaginaDeInicio() {
        loginPage.navigateToLogin();
    }

    @When("ingresa las credenciales de prueba")
    public void ingresaLasCredencialesDePrueba() {
        loginPage.login("standard_user", "secret_sauce");
    }

    @Then("se verifica el ingreso correcto al sistema")
    public void seVerificaElIngresoCorrectoAlSistema() {
        String expectedTitle = "Products";
        String actualTitle = loginPage.getPageTitle();
        Assert.assertEquals(actualTitle, expectedTitle, "El login no fue exitoso o el título no coincide.");
    }

    @When("ingresa el usuario {string} y la contrasena {string}")
    public void ingresaElUsuarioYLaContrasena(String user, String pass) {
        loginPage.login(user, pass);
    }

    @Then("se verifica que se muestra el mensaje de error {string}")
    public void seVerificaQueSeMuestraElMensajeDeError(String expectedError) {
        String actualError = loginPage.getErrorMessage();
        Assert.assertEquals(actualError, expectedError, "El mensaje de error no coincide.");
    }
}

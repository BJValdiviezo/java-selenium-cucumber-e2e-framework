package com.crowdar.steps;

import com.crowdar.pages.CheckoutPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CheckoutSteps {

    private CheckoutPage checkoutPage = new CheckoutPage(Hooks.getDriver());

    @When("agrega el producto {string} al carrito")
    public void agregaElProductoAlCarrito(String productName) {
        checkoutPage.addProductToCart(productName);
    }

    @And("navega al carrito de compras")
    public void navegaAlCarritoDeCompras() {
        checkoutPage.goToCart();
    }

    @And("procede al checkout ingresando {string}, {string} y {string}")
    public void procedeAlCheckoutIngresandoY(String firstName, String lastName, String postalCode) {
        checkoutPage.clickCheckout();
        checkoutPage.enterShippingInformation(firstName, lastName, postalCode);
    }

    @Then("confirma la orden y verifica el mensaje de agradecimiento {string}")
    public void confirmaLaOrdenYVerificaElMensajeDeAgradecimiento(String expectedMessage) {
        checkoutPage.finishCheckout();
        Assert.assertEquals(checkoutPage.getConfirmationMessage(), expectedMessage);
    }
}
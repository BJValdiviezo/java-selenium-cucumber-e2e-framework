package com.crowdar.steps;

import com.crowdar.pages.CheckoutPage;
import com.crowdar.pages.InventoryPage;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class CheckoutSteps {

    private InventoryPage inventoryPage = new InventoryPage(Hooks.getDriver());
    private CheckoutPage checkoutPage = new CheckoutPage(Hooks.getDriver());

    @When("agrega el producto {string} al carrito")
    public void agregaElProductoAlCarrito(String productName) {
        inventoryPage.addProductToCart(productName);
    }

    @And("navega al carrito de compras")
    public void navegaAlCarritoDeCompras() {
        inventoryPage.goToCart();
        checkoutPage.clickCheckout();
    }

    @And("procede al checkout ingresando {string}, {string} y {string}")
    public void procedeAlCheckoutIngresandoY(String firstName, String lastName, String postalCode) {
        checkoutPage.enterShippingInformation(firstName, lastName, postalCode);
        checkoutPage.finishCheckout();
    }

    @Then("confirma la orden y verifica el mensaje de agradecimiento {string}")
    public void confirmaLaOrdenYVerificaElMensajeDeAgradecimiento(String expectedMessage) {
        String actualMessage = checkoutPage.getConfirmationMessage();
        Assert.assertEquals(actualMessage, expectedMessage, "El mensaje de confirmación no coincide.");
    }
}
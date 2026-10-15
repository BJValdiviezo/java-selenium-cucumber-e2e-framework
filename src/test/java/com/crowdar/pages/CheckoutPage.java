package com.crowdar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CheckoutPage {

    private WebDriver driver;
    private WebDriverWait wait;

    private By checkoutButton = By.id("checkout");
    private By firstNameInput = By.id("first-name");
    private By lastNameInput = By.id("last-name");
    private By postalCodeInput = By.id("postal-code");
    private By continueButton = By.id("continue");
    private By finishButton = By.id("finish");
    private By completeHeader = By.className("complete-header");

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public void enterShippingInformation(String firstName, String lastName, String postalCode) {
        // Aseguramos click en checkout si estamos en el carrito
        if (driver.getCurrentUrl().contains("cart.html")) {
            clickCheckout();
        }

        WebElement firstNameEl = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        firstNameEl.clear();
        firstNameEl.sendKeys(firstName);

        WebElement lastNameEl = driver.findElement(lastNameInput);
        lastNameEl.clear();
        lastNameEl.sendKeys(lastName);

        WebElement postalCodeEl = driver.findElement(postalCodeInput);
        postalCodeEl.clear();
        postalCodeEl.sendKeys(postalCode);

        // Intento de click estándar
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueBtn.click();

        // Si headless traba el evento click, forzamos el submit nativo del formulario contenedor
        if (driver.getCurrentUrl().contains("checkout-step-one.html")) {
            continueBtn.submit();
        }

        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
    }

    public void finishCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    public String getConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader)).getText();
    }
}
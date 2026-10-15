package com.crowdar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
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
        // Inicializamos una espera explícita de 10 segundos
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
    }

    public void enterShippingInformation(String firstName, String lastName, String postalCode) {
        // Garantiza que el campo #first-name esté visible en el DOM antes de escribir
        wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput)).sendKeys(firstName);
        driver.findElement(lastNameInput).sendKeys(lastName);
        driver.findElement(postalCodeInput).sendKeys(postalCode);
        wait.until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public void finishCheckout() {
        wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
    }

    public String getConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader)).getText();
    }
}
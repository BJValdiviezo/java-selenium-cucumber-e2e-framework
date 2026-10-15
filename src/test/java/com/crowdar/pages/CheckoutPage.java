package com.crowdar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

        // Intentar clic y como alternativa presionar ENTER para enviar el formulario en headless
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueBtn.click();

        // Si por latencia en headless no cambió inmediatamente, enviamos ENTER desde el input
        if (driver.getCurrentUrl().contains("checkout-step-one.html")) {
            postalCodeEl.sendKeys(Keys.ENTER);
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
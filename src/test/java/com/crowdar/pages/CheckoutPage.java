package com.crowdar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

        // Esperar que los campos sean visibles
        WebElement firstNameEl = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        WebElement lastNameEl = driver.findElement(lastNameInput);
        WebElement postalCodeEl = driver.findElement(postalCodeInput);

        // Cargar datos
        firstNameEl.clear();
        firstNameEl.sendKeys(firstName);

        lastNameEl.clear();
        lastNameEl.sendKeys(lastName);

        postalCodeEl.clear();
        postalCodeEl.sendKeys(postalCode);

        // Forzar eventos JS para asegurar la validacion en React/Vue de Saucedemo
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
            "arguments[1].dispatchEvent(new Event('input', { bubbles: true }));" +
            "arguments[2].dispatchEvent(new Event('input', { bubbles: true }));",
            firstNameEl, lastNameEl, postalCodeEl
        );

        // Clic directo sobre el submit button
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueBtn.click();

        // Fallback: Si sigue en step-one despues de medio segundo, enviar submit del form por JS
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        if (driver.getCurrentUrl().contains("checkout-step-one.html")) {
            js.executeScript("arguments[0].closest('form').submit();", continueBtn);
        }

        // Confirmar transicion exitosa
        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
    }

    public void finishCheckout() {
        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
        WebElement finishBtn = wait.until(ExpectedConditions.elementToBeClickable(finishButton));
        finishBtn.click();
    }

    public String getConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader)).getText();
    }
}
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
        WebElement checkoutBtn = wait.until(ExpectedConditions.presenceOfElementLocated(checkoutButton));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", checkoutBtn);
    }

    public void enterShippingInformation(String firstName, String lastName, String postalCode) {
        // Si no estamos en checkout-step-one, nos aseguramos de navegar desde el carrito o via URL
        if (!driver.getCurrentUrl().contains("checkout-step-one.html")) {
            if (driver.getCurrentUrl().contains("cart.html")) {
                clickCheckout();
            } else {
                driver.get("https://www.saucedemo.com/checkout-step-one.html");
            }
        }

        // Llenar datos de envio
        WebElement firstNameEl = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        firstNameEl.clear();
        firstNameEl.sendKeys(firstName);

        WebElement lastNameEl = driver.findElement(lastNameInput);
        lastNameEl.clear();
        lastNameEl.sendKeys(lastName);

        WebElement postalCodeEl = driver.findElement(postalCodeInput);
        postalCodeEl.clear();
        postalCodeEl.sendKeys(postalCode);

        // Forzar eventos JS para asegurar la validacion del formulario
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
            "arguments[1].dispatchEvent(new Event('input', { bubbles: true }));" +
            "arguments[2].dispatchEvent(new Event('input', { bubbles: true }));",
            firstNameEl, lastNameEl, postalCodeEl
        );

        // Click en continue y fallback de submit
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        js.executeScript("arguments[0].click();", continueBtn);

        // Confirmar navegacion a la vista dos
        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
    }

    public void finishCheckout() {
        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
        WebElement finishBtn = wait.until(ExpectedConditions.presenceOfElementLocated(finishButton));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", finishBtn);
    }

    public String getConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader)).getText();
    }
}
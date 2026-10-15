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
        if (driver.getCurrentUrl().contains("cart.html")) {
            clickCheckout();
        }

        // Esperar que la vista de checkout-step-one esté cargada
        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));

        WebElement firstNameEl = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        WebElement lastNameEl = driver.findElement(lastNameInput);
        WebElement postalCodeEl = driver.findElement(postalCodeInput);

        // Infección limpia enviando los datos por atributos de valor e interacción Selenium
        firstNameEl.clear();
        firstNameEl.sendKeys(firstName);

        lastNameEl.clear();
        lastNameEl.sendKeys(lastName);

        postalCodeEl.clear();
        postalCodeEl.sendKeys(postalCode);

        // Click asistido por JS para garantizar la activación del event listener de Continue
        WebElement continueBtn = wait.until(ExpectedConditions.presenceOfElementLocated(continueButton));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", continueBtn);

        // Verificación paso a paso para confirmar salida del paso 1
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
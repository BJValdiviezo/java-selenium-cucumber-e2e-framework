package com.crowdar.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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
        WebElement checkoutBtn = wait.until(ExpectedConditions.presenceOfElementLocated(checkoutButton));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", checkoutBtn);
    }

    public void enterShippingInformation(String firstName, String lastName, String postalCode) {
        if (!driver.getCurrentUrl().contains("checkout-step-one.html")) {
            if (driver.getCurrentUrl().contains("cart.html")) {
                clickCheckout();
            } else {
                driver.get("https://www.saucedemo.com/checkout-step-one.html");
            }
        }

        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));

        WebElement firstNameEl = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        firstNameEl.clear();
        firstNameEl.sendKeys(firstName);

        WebElement lastNameEl = driver.findElement(lastNameInput);
        lastNameEl.clear();
        lastNameEl.sendKeys(lastName);

        WebElement postalCodeEl = driver.findElement(postalCodeInput);
        postalCodeEl.clear();
        postalCodeEl.sendKeys(postalCode);
        postalCodeEl.sendKeys(Keys.TAB); // Desasocia el foco para activar la validacion en la app

        WebElement continueBtn = wait.until(ExpectedConditions.presenceOfElementLocated(continueButton));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", continueBtn);

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
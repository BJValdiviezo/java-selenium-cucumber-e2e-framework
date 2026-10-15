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
        if (!driver.getCurrentUrl().contains("checkout-step-one.html")) {
            if (driver.getCurrentUrl().contains("cart.html")) {
                clickCheckout();
            } else {
                driver.get("https://www.saucedemo.com/checkout-step-one.html");
            }
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

        // Envío robusto mediante JavaScript en headless
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", continueBtn);

        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
    }

    public void finishCheckout() {
        WebElement finishBtn = wait.until(ExpectedConditions.elementToBeClickable(finishButton));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", finishBtn);
    }

    public String getConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader)).getText();
    }
}
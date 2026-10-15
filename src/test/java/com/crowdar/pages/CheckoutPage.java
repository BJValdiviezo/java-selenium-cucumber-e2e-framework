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

    private By cartIcon = By.className("shopping_cart_link");
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

    public void addProductToCart(String productName) {
        String buttonId = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        wait.until(ExpectedConditions.elementToBeClickable(By.id(buttonId))).click();
    }

    public void goToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(cartIcon)).click();
        wait.until(ExpectedConditions.urlContains("cart.html"));
    }

    public void clickCheckout() {
        if (!driver.getCurrentUrl().contains("checkout-step-one.html")) {
            WebElement checkoutBtn = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", checkoutBtn);
        }
    }

    public void enterShippingInformation(String firstName, String lastName, String postalCode) {
        wait.until(ExpectedConditions.urlContains("checkout-step-one.html"));

        WebElement firstNameEl = wait.until(ExpectedConditions.visibilityOfElementLocated(firstNameInput));
        WebElement lastNameEl = driver.findElement(lastNameInput);
        WebElement postalCodeEl = driver.findElement(postalCodeInput);

        setReactInputValue(firstNameEl, firstName);
        setReactInputValue(lastNameEl, lastName);
        setReactInputValue(postalCodeEl, postalCode);

        // Intentar click estándar y fallback con ENTER en el último input
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(continueButton));
        continueBtn.click();

        if (driver.getCurrentUrl().contains("checkout-step-one.html")) {
            postalCodeEl.sendKeys(Keys.ENTER);
        }

        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
    }

    private void setReactInputValue(WebElement element, String value) {
        element.click();
        element.clear();
        element.sendKeys(value);

        // Sincronización explícita con el Virtual DOM de React para ejecuciones rápidas en CI
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
            "var nativeSetter = Object.getOwnPropertyDescriptor(window.HTMLInputElement.prototype, 'value').set;" +
            "nativeSetter.call(arguments[0], arguments[1]);" +
            "arguments[0].dispatchEvent(new Event('input', { bubbles: true }));" +
            "arguments[0].dispatchEvent(new Event('change', { bubbles: true }));",
            element, value
        );
    }

    public void finishCheckout() {
        wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
        WebElement finishBtn = wait.until(ExpectedConditions.elementToBeClickable(finishButton));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", finishBtn);
    }

    public String getConfirmationMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(completeHeader)).getText();
    }
}